<template>
	<el-form ref="loginForm" :model="form" :rules="loginRules" label-width="0" size="large" @keyup.enter="onLogin">
		<el-form-item prop="username">
			<el-input v-model="form.username" prefix-icon="el-icon-user" clearable
				:placeholder="$t('login.userPlaceholder')">
				<template #append>
					<el-select v-model="userType" style="width: 130px;">
						<el-option :label="$t('login.admin')" value="admin"></el-option>
						<el-option :label="$t('login.user')" value="user"></el-option>
					</el-select>
				</template>
			</el-input>
		</el-form-item>
		<el-form-item prop="password">
			<el-input v-model="form.password" prefix-icon="el-icon-lock" clearable show-password
				:placeholder="$t('login.PWPlaceholder')"></el-input>
		</el-form-item>
		<el-form-item prop="captcha" class="login-captcha">
			<el-input v-model="form.captcha" prefix-icon="el-icon-key" clearable placeholder="请输入验证码"></el-input>
			<img :src="captchaBase64" @click="onCaptcha" />
		</el-form-item>
		<el-form-item style="margin-bottom: 10px;">
			<el-col :span="12">
				<el-checkbox :label="$t('login.rememberMe')" v-model="form.autologin"></el-checkbox>
			</el-col>
			<el-col :span="12" class="login-forgot">
				<router-link to="/reset_password">{{ $t('login.forgetPassword') }}？</router-link>
			</el-col>
		</el-form-item>
		<el-form-item>
			<el-button type="primary" style="width: 100%;" :loading="islogin" round @click="onLogin">{{ $t('login.signIn')
			}}</el-button>
		</el-form-item>
		<div class="login-reg">
			{{ $t('login.noAccount') }} <router-link to="/user_register">{{ $t('login.createAccount') }}</router-link>
		</div>
	</el-form>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import store from '@/store'
import { useCaptcha } from '@/api/model/auth'
import { useI18n } from 'vue-i18n'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

interface LoginInfo {
	username: string;
	password: string;
	key?: string;
	captcha?: string;
	autologin: boolean;
}

const router = useRouter()
const { t } = useI18n()
const loginForm = ref()
const captchaBase64 = ref()
const userType = ref('admin')
const islogin = ref(false)

const form = reactive<LoginInfo>({
	username: 'admin',
	password: 'admin',
	autologin: false
})

const loginRules = ref({
	username: [{ required: true, message: t('login.userError'), trigger: 'blur' }],
	password: [{ required: true, message: t('login.PWError'), trigger: 'blur' }],
	captcha: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
})

onMounted(() => {
	onCaptcha()
})

const onCaptcha = async () => {
	const { data } = await useCaptcha()
	form.key = data.key
	captchaBase64.value = data.image
}

const onLogin = () => {
	loginForm.value.validate((valid: boolean) => {
		islogin.value = true
		if (!valid) {
			return false
		}
		// 用户登录
		store.userStore.accountLoginAction(form)
			.then(() => {
				router.replace({ path: '/' })
				store.userStore.getMenuListAction()
				ElMessage.success("登录成功")
				islogin.value = false
			})
			.catch(() => {
				onCaptcha()
				islogin.value = false
			})
	})
}
</script>

<style lang="scss" scoped>
.login-captcha {
	:deep(.el-input) {
		width: 200px;
	}

	img {
		margin-left: 20px;
	}
}
</style>
