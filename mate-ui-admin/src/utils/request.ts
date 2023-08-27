import axios from 'axios'
import qs from 'qs'
import { ElMessage } from 'element-plus'
import store from '@/store'
import { Storage } from './storage'
import defaultConfig from "@/config"

// axios实例
const http = axios.create({
	baseURL: defaultConfig.API_URL as any,
	timeout: 60000,
	headers: { 'Content-Type': 'application/json;charset=UTF-8' }
})

// 请求拦截器
http.interceptors.request.use(
	(config: any) => {
		const userStore = store.userStore
		if (userStore?.token) {
			config.headers.Authorization = userStore.token
		}

		config.headers['Accept-Language'] = Storage.getItem('APP_LANG') || defaultConfig.LANG

		// 追加时间戳，防止GET请求缓存
		if (config.method?.toUpperCase() === 'GET') {
			config.params = { ...config.params, t: new Date().getTime() }
		}

		if (Object.values(config.headers).includes('application/x-www-form-urlencoded')) {
			config.data = qs.stringify(config.data)
		}

		return config
	},
	error => {
		return Promise.reject(error)
	}
)

// 响应拦截器
http.interceptors.response.use(
	response => {
		if (response.status !== 200) {
			return Promise.reject(new Error(response.statusText || 'Error'))
		}

		const res = response.data
		// 响应成功
		if (res.code === 0 || res.code === 500) {
			return res
		}

		// 错误提示
		ElMessage.error(res.msg)

		// 没有权限，如：未登录、登录过期等，需要跳转到登录页
		if (res.code === 401) {
			store.userStore?.setToken('')
			location.reload()
		}

		return Promise.reject(new Error(res.msg || 'Error'))
	},
	error => {
		ElMessage.error(error.message)
		return Promise.reject(error)
	}
)

// 导出 axios 实例
export default http
