import { defineStore } from "pinia";
import cache from '@/utils/cache'
import { useAccountLogin, useMobileLogin, useLogout } from "@/api/model/auth";
import { useUserInfo } from "@/api/system/user";
import { useAuthorityList, useMenuNav } from "@/api/system/menu";

export const userStore = defineStore('userStore', {
    state: () => ({
        // 用户信息
		user: {
			id: '',
			username: '',
			avatar: ''
		},
		// 权限列表
		authorityList: [],
		// 菜单列表
		menuList: cache.getMenu(),
		// 登录token
		token: cache.getToken()
    }),
    actions: {
        setUser(val: any) {
			this.user = val
		},
		setToken(val: any) {
			this.token = val
			cache.setToken(val)
		},
		setMenu(val: any) {
			this.menuList = val;
			cache.setMenu(val)
		},
		// 账号登录
		async accountLoginAction(loginForm: any) {
			const { data } = await useAccountLogin(loginForm)
			this.setToken(data.access_token)
		},
		// 手机号登录
		async mobileLoginAction(loginForm: any) {
			const { data } = await useMobileLogin(loginForm)
			this.setToken(data.access_token)
		},
		// 获取用户信息
		async getUserInfoAction() {
			const { data } = await useUserInfo()
			this.setUser(data)
		},
		async getMenuListAction() {
			const { data } = await useMenuNav()
			this.setMenu(data)	
			return data;
		},
		// 获取权限
		async getAuthorityListAction() {
			const { data } = await useAuthorityList()
			this.authorityList = data || []
		},
		// 用户退出
		async logoutAction() {
			await useLogout()

			// 移除 token
			this.setToken(null)
		}
	
    }
})