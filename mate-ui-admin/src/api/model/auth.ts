import http from "@/utils/request"

export const token = (data: any) => {
	return http.post('/token', data)
}

// 获取验证码
export const useCaptcha = () => {
	return http.get('/v1/auth/captcha')
}

// 账户密码登录
export const useAccountLogin = (data: any) => {
	return http.post('/v1/auth/login', data)
}

// 获取验证码
export const useSendCode = (mobile: string) => {
	return http.post('/sys/auth/send/code?mobile=' + mobile)
}

// 手机号码登录
export const useMobileLogin = (data: any) => {
	return http.post('/sys/auth/mobile', data)
}

// 登出
export const useLogout = () => {
	return http.post('/sys/auth/logout')
}

// 查看版本
export const useVersion = () => {
	return http.get('/sys/version')
}