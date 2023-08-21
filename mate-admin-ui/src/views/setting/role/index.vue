<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<el-button type="primary" icon="el-icon-plus" @click="add"></el-button>
				<el-button type="danger" plain icon="el-icon-delete" :disabled="selection.length==0" @click="batchDel"></el-button>
				<el-button type="primary" plain :disabled="selection.length!=1" @click="permission">权限设置</el-button>
			</div>
			<div class="right-panel">
				<div class="right-panel-search">
					<el-input v-model="search.keyword" placeholder="角色名称" clearable></el-input>
					<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
				</div>
			</div>
		</el-header>
		<el-main class="nopadding">
			<m-table ref="tableRef" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" stripe>
				<el-table-column type="selection" width="50"></el-table-column>
				<el-table-column label="#" type="index" width="50"></el-table-column>
				<el-table-column label="角色名称" prop="name" width="150"></el-table-column>
				<el-table-column label="别名" prop="code" width="200"></el-table-column>
				<!-- <el-table-column label="排序" prop="sort" width="80"></el-table-column> -->
				<el-table-column label="状态" prop="status" width="80">
					<template #default="scope">
						<el-switch v-model="scope.row.status" @change="changeSwitch($event, scope.row)" :loading="scope.row.$switch_status" active-value="1" inactive-value="0"></el-switch>
					</template>
				</el-table-column>
				<el-table-column label="创建时间" prop="createTime" width="180"></el-table-column>
				<el-table-column label="备注" prop="remark" min-width="150"></el-table-column>
				<el-table-column label="操作" fixed="right" align="center" width="170">
					<template #default="scope">
						<el-button-group>
							<el-button text type="primary" size="small" icon="el-icon-view" @click="tableShow(scope.row, scope.$index)"/>
							<el-button text type="primary" size="small" icon="el-icon-edit" @click="tableEdit(scope.row, scope.$index)"/>
							<el-popconfirm title="确定删除吗？" @confirm="tableDel(scope.row, scope.$index)">
								<template #reference>
									<el-button text type="danger" icon="el-icon-delete" size="small"/>
								</template>
							</el-popconfirm>
						</el-button-group>
					</template>
				</el-table-column>
			</m-table>
		</el-main>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialogRef" @success="handleSaveSuccess" @closed="dialog.save=false"></save-dialog>

	<permission-dialog v-if="dialog.permission" ref="permissionDialogRef" @closed="dialog.permission=false"></permission-dialog>

</template>

<script lang="ts" setup>
import { ref, reactive, nextTick, getCurrentInstance } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import saveDialog from './save.vue'
import permissionDialog from './permission.vue'
import { useRolePage } from '@/api/system/role'
import MTable from '@/components/MTable/index.vue'

const dialog = reactive({
	save: false,
	permission: false
})
const search = reactive({
	keyword: null
})
const apiObj = ref(useRolePage)
const selection = ref([])
const saveDialogRef = ref()
const permissionDialogRef = ref()
const tableRef = ref()

//添加
const add = () => {
	dialog.save = true
	nextTick(() => {
		saveDialogRef.value.open()
	})
}

//编辑
const tableEdit = (row: any) => {
	dialog.save = true
	nextTick(() => {
		saveDialogRef.value.open('edit')
		saveDialogRef.value.setData(row)
	})
}
//查看
const tableShow = (row: any) => {
	dialog.save = true
	nextTick(() => {
		saveDialogRef.value.open('show')
		saveDialogRef.value.setData(row)
	})
}
//权限设置
const permission = () => {
	dialog.permission = true
	nextTick(() => {
		permissionDialogRef.value.open()
	})
}
//删除
const tableDel = async (row: any) => {
	var reqData = { id: row.id }
	var res:any = await useRolePage(reqData);
	if (res.code == 200) {
		tableRef.value.refresh()
		ElMessage.success("删除成功")
	} else {
		ElMessageBox.alert(res.message, "提示", { type: 'error' })
	}
}
//批量删除
const batchDel = async () => {
	ElMessageBox.confirm(`确定删除选中的 ${selection.value.length} 项吗？如果删除项中含有子集将会被一并删除`, '提示', {
		type: 'warning'
	}).then(() => {
		//@ts-ignore
		const { proxy } = getCurrentInstance();
		const loading = proxy.$loading;
		tableRef.value.refresh()
		loading.close();
		ElMessage.success("操作成功")
	}).catch(() => {

	})
}
//表格选择后回调事件
const selectionChange = (data: any) => {
	selection.value = data;
}
//表格内开关
const changeSwitch = (val: any, row: any) => {
	row.status = row.status == '1' ? '0' : '1'
	row.$switch_status = true;
	setTimeout(() => {
		delete row.$switch_status;
		row.status = val;
		ElMessage.success("操作成功")
	}, 500)
}
//搜索
const upsearch = () => {
	tableRef.value.upData(search)
}
//根据ID获取树结构
const filterTree = (id: any) => {
	var target = null;
	function filter(tree: any) {
		tree.forEach((item: any) => {
			if (item.id == id) {
				target = item
			}
			if (item.children) {
				filter(item.children)
			}
		})
	}
	filter(tableRef.value.tableData)
	return target
}
//本地更新数据
const handleSaveSuccess = (data: any, mode: any) => {
	if (mode == 'add') {
		tableRef.value.refresh()
	} else if (mode == 'edit') {
		tableRef.value.refresh()
	}
}
</script>

<style>
</style>
