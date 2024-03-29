<template>
	<el-container>
		<el-aside width="300px" v-loading="showDicloading">
			<el-container>
				<el-header>
					<el-input placeholder="输入关键字进行过滤" v-model="dicFilterText" clearable></el-input>
				</el-header>
				<el-main class="nopadding">
					<el-tree ref="dictTreeRef" class="menu" node-key="id" :data="dicList" :props="dicProps"
						:highlight-current="true" :expand-on-click-node="false" :filter-node-method="dicFilterNode"
						@node-click="dicClick">
						<template #default="{ node, data }">
							<span class="custom-tree-node">
								<span class="label">{{ data.dictName }}</span>
								<span class="code">{{ data.dictCode }}</span>
								<span class="status">
									<el-tag v-if="data.status === '1'" type="success">正常</el-tag>
									<el-tag v-else type="info">禁用</el-tag>
								</span>
								<span class="do">
									<el-button-group>
										<el-button icon="el-icon-edit" size="small"
											@click.stop="dictEdit(data)"></el-button>
										<el-popconfirm title="确定删除吗,字典项会一并删除？" @confirm="dictDel(node, data)">
											<template #reference>
												<el-button icon="el-icon-delete" size="small" />
											</template>
										</el-popconfirm>
									</el-button-group>
								</span>
							</span>
						</template>
					</el-tree>
				</el-main>
				<el-footer style="height:51px;">
					<el-button type="primary" size="small" icon="el-icon-plus" style="width: 100%;"
						@click="addDict">字典分类</el-button>
				</el-footer>
			</el-container>
		</el-aside>
		<el-container class="is-vertical">
			<el-header>
				<div class="left-panel">
					<el-button type="primary" icon="el-icon-plus" @click="addInfo"></el-button>
					<el-button type="danger" plain icon="el-icon-delete" :disabled="selection.length == 0"
						@click="batch_del"></el-button>
					<el-button type="primary" plain disabled>已选：[ {{ checkedDcitName }} ]</el-button>
				</div>
			</el-header>
			<el-main class="nopadding">
				<m-table ref="tableRef" :apiObj="listApi" row-key="id" :params="listApiParams"
					@selection-change="selectionChange" stripe :paginationLayout="'prev, pager, next'">
					<el-table-column type="selection" width="50"></el-table-column>
					<el-table-column label="名称" prop="dictName" width="150"></el-table-column>
					<el-table-column label="键值" prop="dictValue" width="150"></el-table-column>
					<el-table-column label="是否有效" prop="status" width="100">
						<template #default="scope">
							<el-tag v-if="scope.row.status === '1'" type="success">正常</el-tag>
							<el-tag v-else type="info">禁用</el-tag>
						</template>
					</el-table-column>
					<el-table-column label="排序" prop="sort" width="150"></el-table-column>
					<el-table-column label="创建时间" prop="createTime" width="150"></el-table-column>
					<el-table-column label="操作" fixed="right" align="right" width="120">
						<template #default="scope">
							<el-button-group>
								<el-button text type="primary" size="small"
									@click="tableEdit(scope.row, scope.$index)">编辑</el-button>
								<el-popconfirm title="确定删除吗？" @confirm="tableDel(scope.row, scope.$index)">
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
	</el-container>

	<dic-dialog v-if="dialog.dic" ref="dicDialogRef" @success="handleDicSuccess" @closed="dialog.dic = false"></dic-dialog>

	<list-dialog v-if="dialog.list" ref="listDialogRef" @success="handleListSuccess"
		@closed="dialog.list = false"></list-dialog>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, watch, nextTick } from 'vue'
import dicDialog from './dict.vue'
import listDialog from './list.vue'
import Sortable from 'sortablejs'
import MTable from '@/components/MTable/index.vue'
import { ElMessage, ElMessageBox } from 'element-plus'

import { useDictList, useDictItemList, useDictDel, useDictItemDel } from '@/api/system/dict'


const dictTreeRef = ref()
const dicDialogRef = ref()
const listDialogRef = ref()
const tableRef = ref()

const dialog: any = ref({
	dic: false,
	info: false
})
const showDicloading = ref(true)
const dicList = ref([])
const dicFilterText = ref('')
let dicProps = ref({
	label: 'name'
})
const listApi = ref()
const listApiParams = ref({})
const selection = ref([])
const isListSaveing = ref(false)
const listDialogVisible = ref(false)
const checkedDcitName = ref('')


watch(dicFilterText, (val) => {
	dictTreeRef.value.filter(val);
})
onMounted(() => {
	getDict()
})

//加载树数据
const getDict = async () => {
	var res = await useDictList();
	showDicloading.value = false;
	dicList.value = res.data;
	//获取第一个节点,设置选中 & 加载明细列表
	var firstNode: any = dicList.value[0];
	if (firstNode) {
		nextTick(() => {
			dictTreeRef.value.setCurrentKey(firstNode.id)
		})
		listApiParams.value = {
			dictId: firstNode.id
		}
		checkedDcitName.value = firstNode.dictName
		listApi.value = useDictItemList;
	}
}
//树过滤
const dicFilterNode = (value: any, data: any) => {
	if (!value) return true;
	var targetText = data.name + data.code;
	return targetText.indexOf(value) !== -1;
}
//树增加
const addDict = () => {
	dialog.value.dic = true
	nextTick(() => {
		dicDialogRef.value.open()
	})
}
//编辑树
const dictEdit = (data: any) => {
	dialog.value.dic = true
	nextTick(() => {
		dicDialogRef.value.open('edit')
		dicDialogRef.value.setData(data)
	})
}
//树点击事件
const dicClick = (data: any) => {
	tableRef.value.reload({
		dictId: data.id,
	})
	checkedDcitName.value = data.dictName
}
//删除树
const dictDel = async (node: any, data: any) => {
	showDicloading.value = true;
	var res: any = await useDictDel(data.id);
	if (res.code === 0) {
		var dicCurrentKey = dictTreeRef.value.getCurrentKey();
		dictTreeRef.value.remove(data.id)
		if (dicCurrentKey == data.id) {
			var firstNode: any = dicList.value[0];
			if (firstNode) {
				dictTreeRef.value.setCurrentKey(firstNode.id);
				tableRef.value.upData({
					dictId: firstNode.id
				})
			} else {
				listApi.value = null;
				tableRef.value.tableData = []
				checkedDcitName.value = ''
			}
		}
		showDicloading.value = false;
		ElMessage.success(res.msg)
	} else {
		ElMessageBox.alert(res.msg, "提示", { type: 'error' })
		showDicloading.value = false;
	}
}
//添加明细
const addInfo = () => {
	dialog.value.list = true
	nextTick(() => {
		var dicCurrentKey = dictTreeRef.value.getCurrentKey();
		var code = null
		if (dicList.value.length > 0) {
			var t: any = dicList.value.find((d: any) => d.id == dicCurrentKey)
			code = t.dictCode
		}
		const data = {
			dictId: dicCurrentKey,
			dictCode: code
		}
		listDialogRef.value.open()
		listDialogRef.value.setData(data)
	})
}
//编辑明细
const tableEdit = (row: any) => {
	dialog.value.list = true
	nextTick(() => {
		var dicCurrentKey = dictTreeRef.value.getCurrentKey();
		row.dic = dicCurrentKey

		var t: any = dicList.value.find((d: any) => d.id == dicCurrentKey)
		row.dictCode = t.dictCode
		listDialogRef.value.open('edit')
		listDialogRef.value.setData(row)
	})
}
//删除明细
const tableDel = async (row: any, index: any) => {
	var res: any = await useDictItemDel(row.id);
	if (res.code === 0) {
		tableRef.value.tableData.splice(index, 1);
		ElMessage.success(res.msg)
	} else {
		ElMessageBox.alert(res.msg, "提示", { type: 'error' })
	}
}
//批量删除
const batch_del = async () => {
	ElMessageBox.confirm(`确定删除选中的 ${selection.value.length} 项吗？`, '提示', {
		type: 'warning'
	}).then(() => {
		selection.value.forEach((item: any) => {
			tableRef.value.tableData.forEach((itemI: any, indexI: any) => {
				if (item.id === itemI.id) {
					var res = useDictItemDel(itemI.id);
					tableRef.value.tableData.splice(indexI, 1)
				}
			})
		})
		ElMessage.success("操作成功")
	}).catch(() => {

	})
}
//提交明细
const saveList = () => {
	listDialogRef.value.submit(async (formData: any) => {
		isListSaveing.value = true;
		var res = await this.$API.demo.post.post(formData);
		isListSaveing.value = false;
		if (res.code == 200) {
			//这里选择刷新整个表格 OR 插入/编辑现有表格数据
			listDialogVisible.value = false;
			ElMessage.success("操作成功")
		} else {
			ElMessageBox.alert(res.message, "提示", { type: 'error' })
		}
	})
}
//表格选择后回调事件
const selectionChange = (data: any) => {
	selection.value = data;
	console.log('selection', selection.value)
}
//本地更新数据
const handleDicSuccess = (data: any, mode: any) => {
	if (mode == 'add') {
		getDict()
	} else if (mode == 'edit') {
		var editNode = dictTreeRef.value.getNode(data.id);
		//判断是否移动？
		var editNodeParentId = editNode.level == 1 ? undefined : editNode.parent.data.id
		if (editNodeParentId != data.parentId) {
			var obj = editNode.data;
			dictTreeRef.value.remove(data.id)
			dictTreeRef.value.append(obj, data.parentId[0])
		}
		Object.assign(editNode.data, data)
	}
}
//本地更新数据
const handleListSuccess = (data: any, mode: any) => {
	tableRef.value.reload({
		typeCode: data.oldTypeCode
	})
}

</script>

<style scoped>
.custom-tree-node {
	display: flex;
	flex: 1;
	align-items: center;
	justify-content: space-between;
	font-size: 14px;
	padding-right: 24px;
	height: 100%;
}

.custom-tree-node .code {
	font-size: 12px;
	color: #999;
}

.custom-tree-node .status {
	font-size: 12px;
	color: #999;
}

.custom-tree-node .do {
	display: none;
}

.custom-tree-node:hover .code {
	display: none;
}

.custom-tree-node:hover .status {
	display: none;
}

.custom-tree-node:hover .do {
	display: inline-block;
}
</style>