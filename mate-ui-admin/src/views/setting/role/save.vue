<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="500" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode=='show'" ref="dialogFormRef" label-width="100px" label-position="left">
			<el-form-item label="角色名称" prop="name">
				<el-input v-model="form.name" clearable></el-input>
			</el-form-item>
			<el-form-item label="角色编码" prop="code">
				<el-input v-model="form.code" clearable></el-input>
			</el-form-item>
			<el-form-item label="排序" prop="sort">
				<el-input-number v-model="form.sort" controls-position="right" :min="1" style="width: 100%;"></el-input-number>
			</el-form-item>
			<el-form-item label="是否有效" prop="status">
				<el-switch v-model="form.status" active-value="1" inactive-value="0"></el-switch>
			</el-form-item>
			<el-form-item label="备注" prop="remark">
				<el-input v-model="form.remark" clearable type="textarea"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible=false" >取 消</el-button>
			<el-button v-if="mode!='show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRoleSave } from '@/api/system/role'

const emit = defineEmits(['success', 'closed'])
const mode = ref("add")
const titleMap: any = ref({
	add: '新增',
	edit: '编辑',
	show: '查看'
})
const visible = ref(false)
const isSaveing = ref(false)
//表单数据
const form = reactive({
	id: "",
	name: "",
	code: "",
	sort: 1,
	status: "1",
	remark: ""
})
//验证规则
const rules = reactive({
	sort: [
		{ required: true, message: '请输入排序', trigger: 'change' }
	],
	name: [
		{ required: true, message: '请输入角色名称' }
	],
	code: [
		{ required: true, message: '请输入角色别名' }
	]
})
const dialogFormRef = ref()

//显示
const open = (data = 'add') => {
	mode.value = data;
	visible.value = true;
}
//表单提交方法
const submit = () => {
	dialogFormRef.value.validate(async (valid: any) => {
		if (valid) {
			isSaveing.value = true;
			var res: any = await useRoleSave(form);
			isSaveing.value = false;
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
	// this.form.id = data.id
	// this.form.label = data.label
	// this.form.alias = data.alias
	// this.form.sort = data.sort
	// this.form.status = data.status
	// this.form.remark = data.remark

	//可以和上面一样单个注入，也可以像下面一样直接合并进去
	Object.assign(form, data)
}

defineExpose({
	open,
	setData
})

</script>

<style>
</style>
