<template>
	<el-card shadow="never" header="修改密码">
		<el-alert title="密码更新成功后，您将被重定向到登录页面，您可以使用新密码重新登录。" type="info" show-icon style="margin-bottom: 15px;" />
		<el-form ref="formRef" :model="form" :rules="rules" label-width="120px" style="margin-top:20px;">
			<el-form-item label="当前密码" prop="userPassword">
				<el-input v-model="form.userPassword" type="password" show-password placeholder="请输入当前密码"></el-input>
				<div class="el-form-item-msg">必须提供当前登录用户密码才能进行更改</div>
			</el-form-item>
			<el-form-item label="新密码" prop="newPassword">
				<el-input v-model="form.newPassword" type="password" show-password placeholder="请输入新密码"></el-input>
				<m-password-strength v-model="form.newPassword"></m-password-strength>
				<div class="el-form-item-msg">请输入包含英文、数字的8位以上密码</div>
			</el-form-item>
			<el-form-item label="确认新密码" prop="confirmNewPassword">
				<el-input v-model="form.confirmNewPassword" type="password" show-password
					placeholder="请再次输入新密码"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="save">保存密码</el-button>
			</el-form-item>
		</el-form>
	</el-card>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { ElMessageBox } from 'element-plus';
import MPasswordStrength from '@/components/MPasswordStrength/index.vue'
import { useRouter } from 'vue-router';

const router = useRouter();
const formRef = ref()
const form = reactive({
	userPassword: "",
	newPassword: "",
	confirmNewPassword: ""
})

const rules = reactive({
	userPassword: [
		{ required: true, message: '请输入当前密码' }
	],
	newPassword: [
		{ required: true, message: '请输入新密码' }
	],
	confirmNewPassword: [
		{ required: true, message: '请再次输入新密码' },
		{
			validator: (rule: any, value: any, callback: any) => {
				if (value !== form.newPassword) {
					callback(new Error('两次输入密码不一致'));
				} else {
					callback();
				}
			}
		}
	]
})

const save = () => {
	formRef.value.validate((valid: any) => {
		if (valid) {
			ElMessageBox.alert("密码修改成功，是否跳转至登录页使用新密码登录", "修改成功", {
				type: 'success',
				center: true
			}).then(() => {
				router.replace({
					path: '/login'
				})
			}).catch(() => { })
		} else {
			return false
		}
	})
}	
</script>

<style>

</style>
