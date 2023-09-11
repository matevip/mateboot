<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="400" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" ref="dialogFormRef" label-width="100px" label-position="left">
			<el-form-item label="字典分类" prop="dictId">
				<el-cascader v-model="form.dictId" :options="dictOptions" :props="dictProps" :show-all-levels="false"
					clearable disabled></el-cascader>
			</el-form-item>
			<el-form-item label="项名称" prop="name">
				<el-input v-model="form.dictName" clearable></el-input>
			</el-form-item>
			<el-form-item label="键值" prop="key">
				<el-input v-model="form.dictValue" clearable></el-input>
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
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useDictList, useDictItemSave } from '@/api/system/dict';


const emit = defineEmits(['success', 'closed'])

const mode = ref("add")
const titleMap: any = ref({
	add: '新增字典项',
	edit: '编辑字典项'
})

const visible = ref(false)
const isSaveing = ref(false)

//表单数据
const form = reactive({
	id: "",
	dictId: "",
	dictName: "",
	dictValue: "",
	status: '1',
	sort: 0
})

//验证规则
const rules = reactive({
	dictName: [
		{ required: true, message: '请输入字典项名称' }
	],
	dictValue: [
		{ required: true, message: '请输入字典项值' }
	]
})

const dictOptions = ref([])
const dictProps = ref({
	value: "id",
	label: "dictName",
	emitPath: false,
	checkStrictly: true
})


const dialogFormRef = ref()

onMounted(() => {
	getDict()
})
//显示
const open = (data = 'add') => {
	mode.value = data;
	visible.value = true;
}

//获取字典列表
const getDict = async () => {
	var res = await useDictList();
	dictOptions.value = res.data.list;
}

//表单提交方法
const submit = () => {
	dialogFormRef.value.validate(async (valid: boolean) => {
		if (valid) {
			isSaveing.value = true;
			var res: any = await useDictItemSave(form);
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
