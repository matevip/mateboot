<template>
	<el-container>
		<el-aside width="220px">
			<el-tree ref="categoryRef" class="menu" node-key="label" :data="category" :default-expanded-keys="['系统日志']"
				current-node-key="系统日志" :highlight-current="true" :expand-on-click-node="false">
			</el-tree>
		</el-aside>
		<el-container>
			<el-main class="nopadding">
				<el-container>
					<el-header>
						<div class="left-panel">
							<div class="right-panel-search">
								<el-date-picker v-model="search.queryTime" type="datetimerange"
									value-format="YYYY-MM-DD HH:mm:ss" range-separator="至" start-placeholder="开始日期"
									end-placeholder="结束日期" :shortcuts="shortcuts"></el-date-picker>
								<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
							</div>
						</div>
						<div class="right-panel">

						</div>
					</el-header>
					<el-header style="height:150px;">
						<m-echarts height="100%" :option="logsChartOption"></m-echarts>
					</el-header>
					<el-main class="nopadding">
						<m-table ref="tableRef" :apiObj="apiObj" stripe highlightCurrentRow @row-click="rowClick">
							<el-table-column label="操作类型" prop="businessType" min-width="90"></el-table-column>
							<el-table-column label="日志名" prop="name" min-width="130"></el-table-column>
							<el-table-column label="请求接口" prop="reqUri" min-width="180"></el-table-column>
							<el-table-column label="请求方法" prop="reqMethod" min-width="90"></el-table-column>
							<el-table-column label="执行时长" prop="duration" min-width="100">
								<template #default="scope">
									<el-tag v-if="scope.row.duration > 100" type="danger">{{ scope.row.duration
									}}ms</el-tag>
									<el-tag v-else type="success">{{ scope.row.duration }}ms</el-tag>
								</template>
							</el-table-column>
							<el-table-column label="姓名" prop="realName" min-width="150"></el-table-column>
							<el-table-column label="客户端IP" prop="ip" min-width="150"></el-table-column>
							<el-table-column label="地址" prop="address" min-width="130"></el-table-column>
							<el-table-column label="状态" prop="status" min-width="90">
								<template #default="scope">
									<div v-if="scope.row.status == 0">
										<el-tag type="success">成功</el-tag>
									</div>
									<div v-else-if="scope.row.status == 1">
										<el-tag type="danger">失败</el-tag>
									</div>
								</template>
							</el-table-column>
							<el-table-column label="日志时间" prop="createTime" min-width="170"></el-table-column>
						</m-table>
					</el-main>
				</el-container>
			</el-main>
		</el-container>
	</el-container>

	<el-drawer v-model="infoDrawer" title="日志详情" :size="600" destroy-on-close>
		<info ref="infoRef"></info>
	</el-drawer>
</template>

<script setup lang="ts">
import { ref, reactive, toRefs, nextTick } from 'vue'
import info from './info.vue'
import MEcharts from '@/components/MEcharts/index.vue'
import MTable from '@/components/MTable/index.vue'
import { useOperateLogPage } from '@/api/system/log'

const data = reactive({
	logsChartOption: {
		color: ['#3265f5', '#e6a23c', '#f56c6c'],
		grid: {
			top: '0px',
			left: '10px',
			right: '10px',
			bottom: '0px'
		},
		tooltip: {
			trigger: 'axis'
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: ['2021-07-01', '2021-07-02', '2021-07-03', '2021-07-04', '2021-07-05', '2021-07-06', '2021-07-07', '2021-07-08', '2021-07-09', '2021-07-10', '2021-07-11', '2021-07-12', '2021-07-13', '2021-07-14', '2021-07-15']
		},
		yAxis: {
			show: false,
			type: 'value'
		},
		series: [{
			data: [120, 200, 150, 80, 70, 110, 130, 120, 200, 150, 80, 70, 110, 130, 70, 110],
			type: 'bar',
			stack: 'log',
			barWidth: '15px'
		},
		{
			data: [15, 26, 7, 12, 13, 9, 21, 15, 26, 7, 12, 13, 9, 21, 12, 3],
			type: 'bar',
			stack: 'log',
			barWidth: '15px'
		},
		{
			data: [0, 0, 0, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
			type: 'bar',
			stack: 'log',
			barWidth: '15px'
		}]
	},

	search: {
		queryTime: ""
	},
})

const { logsChartOption, search } = toRefs(data)
const infoDrawer = ref(false)
const apiObj = ref(useOperateLogPage)
const date = ref([])
const infoRef = ref()
const tableRef = ref()
const categoryRef = ref()
const category = ref([
	{
		label: '系统日志',
		children: [
			{ label: 'debug' },
			{ label: 'info' },
			{ label: 'warn' },
			{ label: 'error' },
			{ label: 'fatal' }
		]
	},
	{
		label: '应用日志',
		children: [
			{ label: 'selfHelp' },
			{ label: 'WechatApp' }
		]
	}
])

const upsearch = () => {
	tableRef.value.upData(search.value)
}

const rowClick = (row: any) => {
	infoDrawer.value = true
	nextTick(() => {
		infoRef.value.setData(row)
	})
}

const shortcuts = [
	{
		text: '今日',
		value: [new Date().setHours(0, 0, 0, 0), new Date()],
	},
	{
		text: '今年',
		value: () => {
			const end = new Date()
			const start = new Date(new Date().getFullYear(), 0)
			return [start, end]
		},
	},
	{
		text: '近半年',
		value: () => {
			const end = new Date()
			const start = new Date()
			start.setMonth(start.getMonth() - 6)
			return [start, end]
		},
	},
]
</script>


<style></style>
