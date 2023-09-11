<template>
       	<el-dialog :title="titleMap[mode]" v-model="visible" :width="500" destroy-on-close @closed="$emit('closed')">
		<el-form :model="form" :rules="rules" :disabled="mode == 'show'" ref="dialogFormRef" label-width="100px"
			label-position="left">

			<el-form-item label="登录账号" prop="username">
				<el-input v-model="form.username" placeholder="用于登录系统" disabled></el-input>
			</el-form-item>
			<el-form-item label="新密码" prop="password">
				<el-input v-model="form.password" type="password" show-password placeholder="请输入新密码"></el-input>
				<m-password-strength v-model="form.password"></m-password-strength>
				<div class="el-form-item-msg">请输入包含英文、数字的8位以上密码</div>
			</el-form-item>
			<el-form-item label="确认新密码" prop="password2">
				<el-input v-model="form.password2" type="password" show-password
					placeholder="请再次输入新密码"></el-input>
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
import MPasswordStrength from '@/components/MPasswordStrength/index.vue'
import { useUserSubmit } from '@/api/system/user'

const emit = defineEmits(['success', 'closed'])
const mode = ref("add")
const titleMap: any = reactive({
	add: '设置密码',
})
const visible = ref(false)
const isSaveing = ref(false)
//表单数据
const form = reactive({
    username: "",
	password: "",
	password2: "",
})
//验证规则
const rules = reactive({
	password: [
		{ required: true, message: '请输入登录密码' },
		{
			validator: (rule: any, value: any, callback: any) => {
				if (form.password2 !== '') {
					dialogFormRef.value.validateField('password2');
				}
				callback();
			}
		}
	],
	password2: [
		{ required: true, message: '请再次输入密码' },
		{
			validator: (rule: any, value: any, callback: any) => {
				if (value !== form.password) {
					callback(new Error('两次输入密码不一致!'));
				} else {
					callback();
				}
			}
		}
	],
})

onBeforeMount(() => {
})


//所需数据选项
const groups = ref([])

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
}
//提供给父组件调用
defineExpose({
	open,
	setData
}) 
</script>
    
<style>
    
</style>