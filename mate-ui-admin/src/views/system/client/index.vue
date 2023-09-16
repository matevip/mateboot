<template>
	<el-container>
		<el-header>
			<div class="left-panel">
				<el-button type="primary" icon="el-icon-plus" @click="add"></el-button>
				<el-button type="danger" plain icon="el-icon-delete" :disabled="selection.length==0" @click="batchDel"></el-button>
			</div>
		</el-header>
		<el-main class="nopadding">
			<m-table ref="tableRef" :apiObj="apiObj" row-key="id" @selection-change="selectionChange" stripe>
				<el-table-column type="selection" width="50"></el-table-column>
				<el-table-column label="客户端ID" prop="clientId" width="150"></el-table-column>
				<el-table-column label="标识" prop="clientKey" width="100"></el-table-column>		
				<el-table-column label="秘钥" prop="clientSecret" show-overflow-tooltip width="150"></el-table-column>
				<el-table-column label="设备类型" prop="deviceType" show-overflow-tooltip width="150"></el-table-column>
				<el-table-column label="状态" width="90">
					<template #default>
						<el-icon style="color: #67C23A;"><el-icon-circle-check-filled /></el-icon>
					</template>
				</el-table-column>
				<el-table-column label="活跃超时" prop="activeTimeout" width="250"></el-table-column>
				<el-table-column label="固定超时" prop="timeout" width="250"></el-table-column>
				<el-table-column label="操作" fixed="right" align="right" width="120">
					<template #default="scope">
						<el-button-group>
							<el-button text type="primary" size="small" @click="tableEdit(scope.row)">编辑</el-button>
							<el-popconfirm title="确定删除吗？" @confirm="tableDel(scope.row)">
								<template #reference>
									<el-button text type="primary" size="small">删除</el-button>
								</template>
							</el-popconfirm>
						</el-button-group>
					</template>
				</el-table-column>
			</m-table>
		</el-main>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialogRef" @success="handleSuccess" @closed="dialog.save=false"></save-dialog>

</template>

<script setup lang="ts">

import { ref, reactive, nextTick } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import saveDialog from './save.vue'
import MTable from '@/components/MTable/index.vue'
import { useClientPage, useClientDel } from '@/api/system/client'

const dialog = reactive({
	save: false,
})
const search = reactive({
	name: null
})
const apiObj = ref(useClientPage)
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

//删除
const tableDel = async (row: any) => {
	var reqData = { id: row.id }
	var res: any = await useClientDel(row.id);
	if (res.code == 0) {
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
		// const { proxy } = getCurrentInstance();
		// const loading = proxy.$loading;
		tableRef.value.refresh()
		// loading.close();
		ElMessage.warning("待开发")
	}).catch(() => {

	})
}
//表格选择后回调事件
const selectionChange = (data: any) => {
	selection.value = data;
}

//搜索
const upsearch = () => {
	tableRef.value.upData(search)
}

//本地更新数据
const handleSuccess = (data: any, mode: any) => {
	if (mode == 'add') {
		tableRef.value.refresh()
	} else if (mode == 'edit') {
		tableRef.value.refresh()
	}
}
</script>
<!-- 
<script>
	import saveDialog from './save'

	export default {
		name: "client",
		components: {
			saveDialog
		},
		data(){
			return {
				dialog: {
					save: false
				},
				apiObj: this.$API.system.app.list,
				selection: []
			}
		},
		methods: {
			//增加
			add(){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open()
				})
			},
			//编辑
			table_edit(row){
				this.dialog.save = true
				this.$nextTick(() => {
					this.$refs.saveDialog.open('edit').setData(row)
				})
			},
			//删除
			async table_del(row, index){
				var reqData = {id: row.id}
				var res = await this.$API.user.del.post(reqData);
				if(res.code == 200){
					//这里选择刷新整个表格 OR 插入/编辑现有表格数据
					this.$refs.table.tableData.splice(index, 1);
					this.$message.success("删除成功")
				}else{
					this.$alert(res.message, "提示", {type: 'error'})
				}
			},
			//批量删除
			async batch_del(){
				this.$confirm(`确定删除选中的 ${this.selection.length} 项吗？`, '提示', {
					type: 'warning'
				}).then(() => {
					const loading = this.$loading();
					this.selection.forEach(item => {
						this.$refs.table.tableData.forEach((itemI, indexI) => {
							if (item.id === itemI.id) {
								this.$refs.table.tableData.splice(indexI, 1)
							}
						})
					})
					loading.close();
					this.$message.success("操作成功")
				}).catch(() => {

				})
			},
			//表格选择后回调事件
			selectionChange(selection){
				this.selection = selection;
			},
			//本地更新数据
			handleSuccess(data, mode){
				if(mode=='add'){
					data.id = new Date().getTime()
					this.$refs.table.tableData.unshift(data)
				}else if(mode=='edit'){
					this.$refs.table.tableData.filter(item => item.id===data.id ).forEach(item => {
						Object.assign(item, data)
					})
				}
			}
		}
	} -->
<!-- </script> -->

<style>
</style>
