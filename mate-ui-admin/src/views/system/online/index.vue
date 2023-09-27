<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<div class="right-panel-search">
					<el-input v-model="search.name" placeholder="姓名" clearable></el-input>
					<el-date-picker v-model="search.date" type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
					<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
				</div>
			</div>
			<div class="right-panel">

			</div>
		</el-header>
		<el-main class="nopadding">
			<m-table ref="tableRef" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" stripe>
				<el-table-column type="selection" width="50"></el-table-column>
				<el-table-column label="#" type="index" width="50"></el-table-column>
				<el-table-column label="姓名" prop="realName" width="150"></el-table-column>
				<el-table-column label="登录 IP" prop="ip" width="200"></el-table-column>
				<el-table-column label="登录地址" prop="address" width="180"></el-table-column>
				<el-table-column label="浏览器" prop="browser" min-width="150"></el-table-column>
				<el-table-column label="登录时间" prop="loginTime" min-width="150"></el-table-column>
				<el-table-column label="操作" fixed="right" align="center" width="170">
					<template #default="scope">
						<el-button-group>
							<el-popconfirm title="确定强退该用户吗？" @confirm="tableDel(scope.row)">
								<template #reference>
									<el-button text type="danger" icon="el-icon-delete" size="small"
										:disabled="token == scope.row.token">强退</el-button>
								</template>
							</el-popconfirm>
						</el-button-group>
					</template>
				</el-table-column>
			</m-table>
		</el-main>
	</el-container>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useOnlineUserPage, useKickOut } from '@/api/system/online'
import MTable from '@/components/MTable/index.vue'
import store from '@/store'

const search = reactive({
	name: null,
	date: null,
})
const apiObj = ref(useOnlineUserPage)
const selection = ref([])
const tableRef = ref()
const token = store.userStore.token


//删除
const tableDel = async (row: any) => {
	var res: any = await useKickOut(row.token);
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
	tableRef.value.upData(search)
}
</script>

<style></style>
