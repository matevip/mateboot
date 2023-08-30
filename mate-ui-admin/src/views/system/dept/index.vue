<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<el-button type="primary" icon="el-icon-plus" @click="add"></el-button>
				<el-button type="danger" plain icon="el-icon-delete" :disabled="selection.length==0" @click="batchDel"></el-button>
			</div>
			<div class="right-panel">
				<div class="right-panel-search">
					<el-input v-model="search.keyword" placeholder="部门名称" clearable></el-input>
					<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
				</div>
			</div>
		</el-header>
		<el-main class="nopadding">
			<m-table ref="tableRef" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" hidePagination>
				<el-table-column type="selection" width="50"></el-table-column>
				<el-table-column label="机构名称" prop="name" width="250"></el-table-column>
				<el-table-column label="排序" prop="sort" width="150"></el-table-column>
				<el-table-column label="创建时间" prop="createTime" width="180"></el-table-column>
				<el-table-column label="操作" fixed="right" align="right" width="170">
					<template #default="scope">
						<el-button-group>
							<el-button text type="primary" size="small" icon="el-icon-view"  @click="tableShow(scope.row)"/>
							<el-button text type="primary" size="small" icon="el-icon-edit" @click="tableEdit(scope.row)"/>
							<el-popconfirm title="确定删除吗？" @confirm="tableDel(scope.row)">
								<template #reference>
									<el-button text type="danger" size="small" icon="el-icon-delete"/>
								</template>
							</el-popconfirm>
						</el-button-group>
					</template>
				</el-table-column>

			</m-table>
		</el-main>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialogRef" @success="handleSaveSuccess" @closed="dialog.save=false"></save-dialog>

</template>

<script lang="ts" setup name="dept">
import { ref, reactive, nextTick } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import saveDialog from './save.vue'
import MTable from '@/components/MTable/index.vue'
import { useDeptList, useDeptDel } from '@/api/system/dept'

const dialog = reactive({
	save: false
})
const selection = ref([])
const search = reactive({
	keyword: null
})
//@Todo
const apiObj = useDeptList
const saveDialogRef = ref()
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
//删除
const tableDel = async (row: any) => {
	var reqData = { ids: [row.id] }
	var res = await useDeptDel(reqData) as any;
	if (res.code == 200) {
		tableRef.value.refresh()
		ElMessage.success(res.msg)
	} else {
		ElMessageBox.alert(res.msg, "提示", { type: 'error' })
	}
}
//批量删除
const batchDel =() => {
	ElMessageBox.confirm(`确定删除选中的 ${selection.value.length} 项吗？如果删除项中含有子集将会被一并删除`, '提示', {
		type: 'warning'
	}).then(async () => {
		var reqData = {
			ids: selection.value.map((item: any) => item.id)
		}
		var res = await useDeptDel(reqData) as any;
		if (res.code === 200) {
			ElMessage.success(res.msg)
		} else {
			ElMessage.warning(res.msg)
		}
		tableRef.value.refresh()
		
	}).catch(() => {

	})
}
//表格选择后回调事件
const selectionChange = (data: any) => {
	selection.value = data;
}
//搜索
const upsearch = () => {

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
