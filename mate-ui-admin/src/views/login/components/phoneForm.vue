<template>
	<el-form ref="loginForm" :model="form" :rules="rules" label-width="0" size="large" @keyup.enter="login">
		<el-form-item prop="mobile">
			<el-input v-model="form.mobile" prefix-icon="el-icon-iphone" clearable :placeholder="$t('login.mobilePlaceholder')">
				<template #prepend>+86</template>
			</el-input>
		</el-form-item>
		<el-form-item prop="code"  style="margin-bottom: 35px;">
			<div class="login-msg-code">
				<el-input v-model="form.code" prefix-icon="el-icon-unlock" clearable :placeholder="$t('login.smsPlaceholder')"></el-input>
				<el-button @click="sendCode" :disabled="disabled">{{this.$t('login.smsGet')}}<span v-if="disabled"> ({{time}})</span></el-button>
			</div>
		</el-form-item>
		<el-form-item>
			<el-button type="primary" style="width: 100%;" :loading="isLogin" round @click="login">{{ $t('login.signIn') }}</el-button>
		</el-form-item>
		<div class="login-reg">
			{{$t('login.noAccount')}} <router-link to="/user_register">{{$t('login.createAccount')}}</router-link>
		</div>
	</el-form>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { useI18n } from 'vue-i18n'
import { ElMessage } from 'element-plus'

const { t } = useI18n()

const form = reactive({
	mobile: "",
	code: "",
})

const rules = reactive({
	mobile: [{ required: true, message: t('login.mobileError') }],
	code: [{ required: true, message: t('login.smsError') }]
})

const disabled = ref(false)
const time = ref(0)
const isLogin = ref(false)
const loginForm = ref()

const sendCode = async () => {
	let validate = await loginForm.value.validateField("mobile").catch(() => { })
	if (!validate) { return false }

	ElMessage.success(t('login.smsSent'))
	disabled.value = true
	time.value = 60
	let timer = setInterval(() => {
		time.value -= 1
		if (time.value < 1) {
			clearInterval(timer)
			disabled.value = false
			time.value = 0
		}
	}, 1000)
}

const login = async () => {
	var validate = await loginForm.value.validate().catch(() => { })
	if (!validate) { return false }
}

</script>

<style>

</style>
