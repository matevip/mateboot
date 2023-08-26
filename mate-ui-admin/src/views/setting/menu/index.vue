<template>
	<el-container>
		<el-aside width="300px" v-loading="menuloading">
			<el-container>
				<el-header>
					<el-input placeholder="输入关键字进行过滤" v-model="menuFilterText" clearable></el-input>
				</el-header>
				<el-main class="nopadding">
					<el-tree ref="menuRef" class="menu" node-key="id" :data="menuList" :props="menuProps" draggable
						highlight-current :expand-on-click-node="false" check-strictly show-checkbox
						:filter-node-method="menuFilterNode" @node-click="menuClick" @node-drop="nodeDrop">

						<template #default="{ node, data }">
							<span class="custom-tree-node el-tree-node__label">
								<span class="label">
									<el-icon>
										<component :is="data.icon" />
									</el-icon> {{ node.label }}
								</span>
								<span class="do">
									<el-icon @click.stop="add(node, data)">
										<el-icon-plus />
									</el-icon>
								</span>
							</span>
						</template>

					</el-tree>
				</el-main>
				<el-footer style="height:51px;">
					<el-button type="primary" size="small" icon="el-icon-plus" @click="add()"></el-button>
					<el-button type="primary" size="small" plain icon="el-icon-refresh" @click="getMenu"></el-button>
					<el-button type="danger" size="small" plain icon="el-icon-delete" @click="delMenu"></el-button>
				</el-footer>
			</el-container>
		</el-aside>
		<el-container>
			<el-main class="nopadding" style="padding:20px;" ref="mainRef">
				<save ref="saveRef" :menu="menuList" @getMenu="getMenu"></save>
			</el-main>
		</el-container>
	</el-container>
</template>

<script lang="ts" setup name="menu">
import { ref, reactive, watch, onMounted } from 'vue'

import save from './save.vue'
import { useMenuList, useMenuSave, useMenuDel } from '@/api/system/menu'
import { ElMessage, ElMessageBox } from 'element-plus'

const newMenuIndex = ref(1)
const menuloading = ref(false)
const menuFilterText = ref('')
const menuList = ref([])
const menuProps = reactive({
	label: (data: any) => {
		return data.title
	}
})

const menuRef = ref()
const saveRef = ref()
const mainRef = ref()

watch(menuFilterText, (val) => {
	menuRef.value.filter(val)
})

onMounted(() => {
	getMenu()
})

//加载树数据
const getMenu = async () => {
	menuloading.value = true
	const res = await useMenuList(4);
	menuloading.value = false
	menuList.value = res.data;
}

//树点击
const menuClick = (data: any, node: any) => {
	var pid = node.level == 1 ? undefined : node.parent.data.id;
	saveRef.value.setData(data, pid)
	mainRef.value.$el.scrollTop = 0
}

//树过滤
const menuFilterNode = (value: any, data: any) => {
	if (!value) return true;
	var targetText = data.title;
	return targetText.indexOf(value) !== -1;
}
//树拖拽
const nodeDrop = (draggingNode: any, dropNode: any, dropType: any) => {
	saveRef.value.setData({})
	ElMessage.warning(`拖拽对象：${draggingNode.data.title}, 释放对象：${dropNode.data.title}, 释放对象的位置：${dropType}`)
}

//增加
const add = async (node?: any, data?: any) => {
	var newTitle = "未命名" + newMenuIndex.value;
	var newName = "undefined" + newMenuIndex.value;
	newMenuIndex.value++;
	const newMenuData = {
		id: "",
		pid: data ? data.id : "",
		name: newName,
		path: "",
		component: "",
		title: newTitle,
		type: 0
	}
	menuloading.value = true
	var res = await useMenuSave(newMenuData)
	menuloading.value = false
	newMenuData.id = res.data

	menuRef.value.append(newMenuData, node)
	menuRef.value.setCurrentKey(newMenuData.id)
	var pid = node ? node.data.id : ""
	saveRef.value.setData(newMenuData, pid)
}

//删除菜单
const delMenu = async () => {
	var CheckedNodes = menuRef.value.getCheckedNodes()
	if (CheckedNodes.length == 0) {
		ElMessage.warning("请选择需要删除的项")
		return false;
	}

	var confirm = await ElMessageBox.confirm('确认删除已选择的菜单吗？选中的菜单下包含子菜单则不能删除！', '提示', {
		type: 'warning',
		confirmButtonText: '删除',
		confirmButtonClass: 'el-button--danger'
	}).catch(() => { })
	if (confirm != 'confirm') {
		return false
	}
	var reqData = CheckedNodes.map((item: any) => item.id);
	var res = await useMenuDel(reqData) as any

	if (res.code == 0) {
		CheckedNodes.forEach((item: any) => {
			var node = menuRef.value.getNode(item)
			if (node.isCurrent) {
				saveRef.value.setData({})
			}
			menuRef.value.remove(item)
		})
		ElMessage.success(res.msg)
	} else {
		ElMessage.warning(res.msg)
	}
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

.custom-tree-node .label {
	display: flex;
	align-items: center;
	;
	height: 100%;
}

.custom-tree-node .label .el-tag {
	margin-left: 5px;
}

.custom-tree-node .do {
	display: none;
}

.custom-tree-node .do i {
	margin-left: 5px;
	color: #999;
}

.custom-tree-node .do i:hover {
	color: #333;
}

.custom-tree-node:hover .do {
	display: inline-block;
}

.el-icon {
	margin-right: 8px;
}</style>
