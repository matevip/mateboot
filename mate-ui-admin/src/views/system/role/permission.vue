<template>
	<el-dialog title="角色权限设置" v-model="visible" :width="500" destroy-on-close @closed="$emit('closed')">
		<el-tabs tab-position="top">
			<el-tab-pane label="菜单权限">
				<div class="treeMain">
					<el-tree ref="menuRef" node-key="id" :data="menu.list" :props="menu.props" show-checkbox></el-tree>
				</div>
			</el-tab-pane>
			<el-tab-pane label="数据权限">
				<el-form label-width="100px" label-position="left">
					<el-form-item label="规则类型">
						<el-select v-model="data.dataType" placeholder="请选择">
							<el-option label="全部可见" value="1"></el-option>
							<el-option label="本人可见" value="2"></el-option>
							<el-option label="所在部门可见" value="3"></el-option>
							<el-option label="所在部门及子级可见" value="4"></el-option>
							<el-option label="选择的部门可见" value="5"></el-option>
							<el-option label="自定义" value="6"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="选择部门" v-show="data.dataType == '5'">
						<div class="treeMain" style="width: 100%;">
							<el-tree ref="deptRef" node-key="id" :data="data.list" :props="data.props"
								show-checkbox></el-tree>
						</div>
					</el-form-item>
					<el-form-item label="规则值" v-show="data.dataType == '6'">
						<el-input v-model="data.rule" clearable type="textarea" :rows="6"
							placeholder="请输入自定义规则代码"></el-input>
					</el-form-item>
				</el-form>
			</el-tab-pane>
			<el-tab-pane label="控制台">
				<el-form label-width="100px" label-position="left">
					<el-form-item label="控制台视图">
						<el-select v-model="dashboard" placeholder="请选择">
							<el-option v-for="item in dashboardOptions" :key="item.value" :label="item.label"
								:value="item.value">
								<span style="float: left">{{ item.label }}</span>
								<span style="float: right; color: #8492a6; font-size: 12px">{{ item.views }}</span>
							</el-option>
						</el-select>
						<div class="el-form-item-msg">用于控制角色登录后控制台的视图</div>
					</el-form-item>
				</el-form>
			</el-tab-pane>
		</el-tabs>
		<template #footer>
			<el-button @click="visible = false">取 消</el-button>
			<el-button type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted, nextTick } from 'vue'
import { ElMessage } from 'element-plus'
import { useMenuList } from '@/api/system/menu'
import { useDeptList } from '@/api/system/dept'
import { useGetMenuIds, useRoleSave, useUpdateRoleMenu } from '@/api/system/role'

const emit = defineEmits(['success', 'closed'])

onMounted(() => {
	getMenu()
	getDept()
})

const visible = ref(false)
const isSaveing = ref(false)
const dashboard = ref("0")
const menu: any = reactive({
	list: [],
	checked: [],
	props: {
		label: (data: any) => {
			return data.title
		}
	}
})
const data: any = reactive({
	dataType: "1",
	list: [],
	checked: [],
	props: {
		label: (data: any) => {
			return data.name
		}
	},
	rule: ""
})
const dashboardOptions = ref([
	{
		value: '0',
		label: '数据统计',
		views: 'stats'

	},
	{
		value: '1',
		label: '工作台',
		views: 'work'
	},
])
const menuRef = ref()
const deptRef = ref()

const form = reactive({
	id: "",
	menuIds: [],
	deptIds: []
})

const open = () => {
	visible.value = true;
}
const submit = () => {
	isSaveing.value = true;

	//选中的和半选的合并后传值接口
	var checkedKeys = menuRef.value.getCheckedKeys().concat(menuRef.value.getHalfCheckedKeys())
	form.menuIds = checkedKeys
	

	var checkedKeys_dept = deptRef.value.getCheckedKeys().concat(deptRef.value.getHalfCheckedKeys())
	console.log(checkedKeys_dept)

	setTimeout(async() => {
		await useUpdateRoleMenu(form);	
		isSaveing.value = false;
		visible.value = false;
		ElMessage.success("操作成功")
		emit('success')
	}, 1000)
}

const getMenu = async () => {
	var res = await useMenuList(-1)
	menu.list = res.data

	var idsRes = await useGetMenuIds(form.id)
	menu.checked = idsRes.data

	// //获取接口返回的之前选中的和半选的合并，处理过滤掉有叶子节点的key
	// menu.checked = ["system", "user", "user.add", "user.edit", "user.del", "directive.edit", "other", "directive"]
	nextTick(() => {
		let filterKeys = menu.checked.filter((key: any) => menuRef.value.getNode(key).isLeaf)
		menuRef.value.setCheckedKeys(filterKeys, true)
	})
}
const getDept = async () => {
	var res = await useDeptList();
	data.list = res.data
	data.checked = ["12", "2", "21", "22", "1"]
	nextTick(() => {
		let filterKeys = data.checked.filter((key: string) => deptRef.value.getNode(key).isLeaf)
		deptRef.value.setCheckedKeys(filterKeys, true)
	})
}

//表单注入数据
const setData = (data: any) => {
	form.id = data.id
}

defineExpose({
	open, setData
})
</script>

<style scoped>.treeMain {
	height: 280px;
	overflow: auto;
	border: 1px solid #dcdfe6;
	margin-bottom: 10px;
}</style>
