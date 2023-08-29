<template>
	<el-container>
		<el-aside width="200px" v-loading="showGrouploading">
			<el-container>
				<el-header>
					<el-input placeholder="输入关键字进行过滤" v-model="groupFilterText" clearable></el-input>
				</el-header>
				<el-main class="nopadding">
					<el-tree ref="groupRef" class="menu" node-key="id" :data="group.list" :props="group.props" :current-node-key="''"
						:highlight-current="true" :expand-on-click-node="false" :filter-node-method="groupFilterNode" default-expand-all
						@node-click="groupClick"></el-tree>
				</el-main>
			</el-container>
		</el-aside>
		<el-container>
			<el-header>
				<div class="left-panel">
					<el-button type="primary" icon="el-icon-plus" @click="add"></el-button>
					<el-button type="danger" plain icon="el-icon-delete" :disabled="selection.length == 0"
						@click="batchDel"></el-button>
					<el-button type="primary" plain :disabled="selection.length == 0">分配角色</el-button>
					<el-button type="primary" plain :disabled="selection.length == 0">密码重置</el-button>
				</div>
				<div class="right-panel">
					<div class="right-panel-search">
						<el-input v-model="search.name" placeholder="登录账号 / 姓名" clearable></el-input>
						<el-button type="primary" icon="el-icon-search" @click="upsearch"></el-button>
					</div>
				</div>
			</el-header>
			<el-main class="nopadding">
				<m-table ref="tableRef" :apiObj="apiObj" @selection-change="selectionChange" stripe remoteSort
					remoteFilter>
					<m-table-column type="selection" width="50"></m-table-column>
					<m-table-column label="ID" prop="id" width="100" sortable='custom'></m-table-column>
					<m-table-column label="头像" width="80" column-key="filterAvatar"
						:filters="[{ text: '已上传', value: '1' }, { text: '未上传', value: '0' }]">
						<template #default="scope">
							<el-avatar :src="scope.row.avatar" size="small"></el-avatar>
						</template>
					</m-table-column>
					<m-table-column label="登录账号" prop="username" width="150" sortable='custom'
						column-key="filterUserName" :filters="[{ text: '系统账号', value: '1' }, { text: '普通账号', value: '0' }]">
					</m-table-column>
					<m-table-column label="姓名" prop="realName" width="150" sortable='custom'></m-table-column>
					<m-table-column label="手机号码" prop="mobile" width="200" sortable='custom'></m-table-column>
					<m-table-column label="所属部门" prop="deptName" width="200" sortable='custom'></m-table-column>
					<m-table-column label="加入时间" prop="createTime" width="170" sortable='custom'></m-table-column>
					<m-table-column label="操作" fixed="right" align="right" width="160">
						<template #default="scope">
							<el-button-group>
								<el-button text type="primary" size="small" @click="tableShow(scope.row)">查看</el-button>
								<el-button text type="primary" size="small" @click="tableEdit(scope.row)">编辑</el-button>
								<el-popconfirm title="确定删除吗？" @confirm="tableDel(scope.row, scope.$index)">
									<template #reference>
										<el-button text type="primary" size="small">删除</el-button>
									</template>
								</el-popconfirm>
							</el-button-group>
						</template>
					</m-table-column>
				</m-table>
			</el-main>
		</el-container>
	</el-container>

	<save-dialog v-if="dialog.save" ref="saveDialogRef" @success="handleSuccess" @closed="dialog.save = false">
	</save-dialog>

</template>

<script lang="ts" setup name="user">
import { ref, reactive, nextTick, watch, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import saveDialog from './save.vue'
import MTable from '@/components/MTable/index.vue'
import MTableColumn from '@/components/MTable/column.js'
import { userPageApi, userDelApi } from '@/api/system/user'
import { useDeptList } from '@/api/system/dept'

const dialog = reactive({
	save: false
})
const showGrouploading = ref(false)
const groupFilterText = ref('')
const group = reactive({
	list: [],
	props: {
		label: (data: any) => {
			return data.name
		}
	}
})
const apiObj = userPageApi
const selection = ref([])
const search = reactive({
	name: null
})
const saveDialogRef = ref()
const tableRef = ref()
const groupRef = ref()

onMounted(() => {
	getGroup()
})

watch(groupFilterText, (val) => {
	groupRef.value.filter(val)
})

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
	console.log(row, 'row');
	
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
const tableDel = async (row: any, index: any) => {
	var reqData = { id: row.id }
	var res: any = await userDelApi(reqData);
	if (res.code == 200) {
		//这里选择刷新整个表格 OR 插入/编辑现有表格数据
		tableRef.value.tableData.splice(index, 1);
		ElMessage.success("删除成功")
	} else {
		ElMessageBox.alert(res.message, "提示", { type: 'error' })
	}
}
//批量删除
const batchDel = () => {
	ElMessageBox.confirm(`确定删除选中的 ${selection.value.length} 项吗？如果删除项中含有子集将会被一并删除`, '提示', {
		type: 'warning'
	}).then(async () => {
		var reqData = {
			ids: selection.value.map((item: any) => item.id)
		}
		var res = await userDelApi(reqData) as any;
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
//加载树数据
const getGroup = async () => {
	showGrouploading.value = true;
	var res = await useDeptList();
	showGrouploading.value = false;
	var allNode = { id: '', name: '所有' }
	res.data.unshift(allNode);
	Object.assign(group.list, res.data)
}
//树过滤
const groupFilterNode = (value: any, data: any) => {
	if (!value) return true;
	return data.label.indexOf(value) !== -1;
}
//树点击事件
const groupClick = (data: any) => {
	var params = {
		deptId: data.id
	}
	tableRef.value.reload(params)
}

//搜索
const upsearch = () => {
	tableRef.value.upData(search)
}
//本地更新数据
const handleSuccess = (data: any, mode: any) => {
	// if (mode == 'add') {
	// 	data.id = new Date().getTime()
	// 	tableRef.value.tableData.unshift(data)
	// } else if (mode == 'edit') {
	// 	tableRef.value.tableData.filter((item: any) => item.id === data.id).forEach((item: any) => {
	// 		Object.assign(item, data)
	// 	})
	// }
	if (mode == 'add') {
		tableRef.value.refresh()
	} else if (mode == 'edit') {
		tableRef.value.refresh()
	}
}
</script>

<style>
</style>
