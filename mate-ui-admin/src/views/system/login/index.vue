<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<div class="right-panel-search">
					<el-input v-model="search.realName" placeholder="姓名" clearable></el-input>
					<el-date-picker v-model="search.queryTime" type="datetimerange" value-format="yyyy-MM-dd HH:mm:ss" range-separator="至"
						start-placeholder="开始日期" end-placeholder="结束日期" :shortcuts="shortcuts"></el-date-picker>
					<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
				</div>
			</div>
			<div class="right-panel">

			</div>
		</el-header>
		<el-main class="nopadding">
			<m-table ref="tableRef" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" stripe>
				<el-table-column label="#" type="index" width="50"></el-table-column>
				<el-table-column label="用户名" prop="username" width="120"></el-table-column>
				<el-table-column label="姓名" prop="realName" width="120"></el-table-column>
				<el-table-column label="登录行为" prop="businessTypeLabel" width="150"></el-table-column>
				<el-table-column label="状态" prop="status" width="90">
					<template #default="scope">
						<div v-if="scope.row.status == 0">
							<el-tag type="success">成功</el-tag>
						</div>
						<div v-else-if="scope.row.status == 1">
							<el-tag type="danger">失败</el-tag>
						</div>
					</template>

				</el-table-column>
				<el-table-column label="登录 IP" prop="ip" width="120"></el-table-column>
				<el-table-column label="登录地址" prop="address" width="120"></el-table-column>
				<el-table-column label="操作系统" prop="os" min-width="150"></el-table-column>
				<el-table-column label="浏览器" prop="browser" min-width="150"></el-table-column>
				<el-table-column label="时间" prop="createTime" min-width="150"></el-table-column>
			</m-table>
		</el-main>
	</el-container>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useLoginLogPage } from '@/api/system/login'
import MTable from '@/components/MTable/index.vue'
import store from '@/store'

const search = reactive({
	realName: null,
	queryTime: null,
})
const apiObj = ref(useLoginLogPage)
const selection = ref([])
const tableRef = ref()

//删除
const tableDel = async (row: any) => {
	var res: any = await useLoginLogPage(row.token);
	if (res.code == 0) {
		tableRef.value.refresh()
		ElMessage.success("删除成功")
	} else {
		ElMessageBox.alert(res.message, "提示", { type: 'error' })
	}
}

//表格选择后回调事件
const selectionChange = (data: any) => {
	selection.value = data;
}

//搜索
const upsearch = () => {
	console.log(search)
	tableRef.value.upData({ ...search })
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
