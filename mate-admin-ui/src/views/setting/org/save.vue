<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="500" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode=='show'" ref="dialogFormRef" label-width="100px">
			<el-form-item label="上级机构" prop="pid">
				<el-cascader v-model="form.pid" :options="groups" :props="groupsProps" :show-all-levels="false" :disabled="mode!='add'" clearable style="width: 100%;"></el-cascader>
			</el-form-item>
			<el-form-item label="机构名称" prop="name">
				<el-input v-model="form.name" placeholder="请输入机构名称" clearable></el-input>
			</el-form-item>
			<el-form-item label="排序" prop="sort">
				<el-input-number v-model="form.sort" controls-position="right" :min="1" style="width: 100%;"></el-input-number>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button v-if="mode!='show'" type="primary" :loading="isSaving" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import {  ElMessage , ElMessageBox } from 'element-plus'
import { useOrgList, useOrgSave } from '@/api/system/org'

const emit = defineEmits(['success', 'closed'])
const mode = ref('add')
const titleMap:any = reactive({
	add: '新增',
	edit: '编辑',
	show: '查看'
})
const visible = ref(false)
const isSaving = ref(false)
const dialogFormRef = ref()
//表单数据
const form = reactive({
	id: "",
	pid: "-1",
	name: "",
	sort: 1,
	status: "1",
	remark: ""
})
const rules = reactive({
	sort: [
		{ required: true, message: '请输入排序', trigger: 'change' }
	],
	pid: [
		{ required: true, message: '请选择上级机构' }
	],
	name: [
		{ required: true, message: '请输入机构名称' }
	]
})
onMounted(() => {
	getGroup()
})
//所需数据选项
const groups: any = reactive([])
const groupsProps = reactive({
	value: "id",
	label: "name",
	emitPath: false,
	checkStrictly: true
})

//显示
const open = (m = 'add') => {
	mode.value = m;
	visible.value = true;
}
//加载树数据
const getGroup = async () => {

	var res = await useOrgList();
	Object.assign(groups, res.data)
	// 添加顶级机构
	groupsDefaultHandle()
}
// 上级菜单树, 设置默认值
const groupsDefaultHandle = () => {
	const parentGroups = {
		id: 0,
		name: '顶级机构'
	}
	groups.unshift(parentGroups)
}
//表单提交方法
const submit = () => {
	dialogFormRef.value.validate(async (valid: any) => {
		if (valid) {
			isSaving.value = true;
			var res: any = await useOrgSave(form);
			isSaving.value = false;
			if (res.code == 200) {
				emit('success', form, mode.value)
				visible.value = false;
				ElMessage.success("操作成功")
			} else {
				ElMessageBox.alert(res.message, "提示", { type: 'error' })
			}
		}
	})
}
//表单注入数据
const setData = (data: any) => {
	// form.id = data.id
	// form.label = data.label
	// form.status = data.status
	// form.sort = data.sort
	// form.parentId = data.parentId
	// form.remark = data.remark

	//可以和上面一样单个注入，也可以像下面一样直接合并进去
	Object.assign(form, data)
}
//提供给父组件调用
defineExpose({
	open,
	setData
})
</script>

<style>
</style>
