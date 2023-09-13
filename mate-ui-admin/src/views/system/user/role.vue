<template>
	<el-dialog :title="titleMap[mode]" v-model="visible" :width="500" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode == 'show'" ref="dialogFormRef" label-width="100px"
			label-position="left">
			<el-form-item label="登录账号" prop="username">
				<el-input v-model="form.username" placeholder="用于登录系统" disabled></el-input>
			</el-form-item>
			<el-form-item label="所属角色" prop="roleIdList">
				<el-cascader class="w-full" v-model="form.roleIdList" :options="roleOptions" :props="roleProps" :show-all-levels="false"
					clearable></el-cascader>
			</el-form-item>
		</el-form>
		<template #footer>
			<el-button @click="visible = false">取 消</el-button>
			<el-button v-if="mode != 'show'" type="primary" :loading="isSaveing" @click="submit()">保 存</el-button>
		</template>
	</el-dialog>
</template>
    
<script setup lang='ts'>
import { ref, reactive, onBeforeMount } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useGetRoleApi, useUserSubmit } from '@/api/system/user'
import { useRoleList } from '@/api/system/role';

const emit = defineEmits(['success', 'closed'])
const mode = ref("add")
const titleMap: any = reactive({
	add: '分配角色',
})
const visible = ref(false)
const isSaveing = ref(false)
//表单数据
const form = reactive({
	id: "",
	username: "",
	roleIdList: []
})
const roleOptions = ref([])
const roleProps = ref({
	value: "id",
	label: "name",
	emitPath: false,
	checkStrictly: true,
	multiple: true,
})
//验证规则
const rules = reactive({
	roleIdList: [
		{ required: true, message: '请选择所属角色' }
	],	
})

onBeforeMount(() => {
	getRole();
	
})

const dialogFormRef = ref()

//获取角色列表
const getRole = async () => {
	var res = await useRoleList();
	roleOptions.value = res.data;
}

const getUserRole = async () => {
	console.log(form.id)
	var res = await useGetRoleApi(form.id);
	form.roleIdList = res.data;
}

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
			var res: any = await useUserSubmit(form);
			isSaveing.value = false;
			if (res.code === 0) {
				emit('success', form, mode.value)
				visible.value = false;
				ElMessage.success(res.msg)
			} else {
				ElMessageBox.alert(res.msg, "提示", { type: 'error' })
			}
		} else {
			return false;
		}
	})
}
//表单注入数据
const setData = (data: any) => {
	Object.assign(form, data)
	getUserRole()
}
//提供给父组件调用
defineExpose({
	open,
	setData
}) 
</script>
    
<style></style>