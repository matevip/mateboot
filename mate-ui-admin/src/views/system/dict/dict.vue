<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="330" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" ref="dialogFormRef" label-width="80px" label-position="left">
			<el-form-item label="字典编码" prop="dictCode">
				<el-input v-model="form.dictCode" clearable placeholder="字典编码"></el-input>
			</el-form-item>
			<el-form-item label="字典名称" prop="dictName">
				<el-input v-model="form.dictName" clearable placeholder="字典显示名称"></el-input>
			</el-form-item>
			<el-form-item label="状态" prop="status">
				<el-radio-group v-model="form.status">
					<el-radio :label="'1'">正常</el-radio>
					<el-radio :label="'0'">禁用</el-radio>
				</el-radio-group>
        	</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取 消</el-button>
			<el-button type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useDictSave } from '@/api/system/dict';
const emit = defineEmits(['success', 'closed'])
const mode = ref("add")
const titleMap: any = ref({
	add: '新增字典',
	edit: '编辑字典'
})

const visible = ref(false)
const isSaveing = ref(false)

//表单数据
const form = reactive({
	id: "",
	dictName: "",
	dictCode: "",
	status: 1
})

//验证规则
const rules = reactive({
	dictCode: [
		{ required: true, message: '请输入字典编码' }
	],
	dictName: [
		{ required: true, message: '请输入字典名称' }
	]
})

const dic = ref([])
const dicProps = ref({
	value: "id",
	label: "name",
	emitPath: false,
	checkStrictly: true
})

const dialogFormRef = ref()
//显示
const open = (data = 'add') => {
	mode.value = data;
	visible.value = true;
}

//表单提交方法
const submit = () => {
	dialogFormRef.value.validate(async (valid: boolean) => {
		if (valid) {
			isSaveing.value = true;
			var res:any = await useDictSave(form);
			isSaveing.value = false;
			if (res.code == 0) {
				emit('success', form, mode.value)
				visible.value = false;
				ElMessage.success(res.msg)
			} else {
				ElMessageBox.alert(res.msg, "提示", { type: 'error' })
			}
		}
	})
}

//表单注入数据
const setData = (data: any) => {
	//可以和上面一样单个注入，也可以像下面一样直接合并进去
	Object.assign(form, data)
}

defineExpose({
	open,
	setData
})

</script>

<style></style>
