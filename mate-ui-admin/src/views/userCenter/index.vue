<template>
	<el-container class="page-user">
		<el-aside style="width: 240px;">
			<el-container>
				<el-header style="height: auto;display: block;">
					<div class="user-info-top">
						<el-avatar :size="70" :src="user.avatar"></el-avatar>
						<h2>{{ user.username }}</h2>
						<p><el-tag effect="dark" round size="large">{{ user.realName }}</el-tag></p>
					</div>
				</el-header>
				<el-main class="nopadding">
					<el-menu class="menu" :default-active="page">
						<el-menu-item-group v-for="group in menu" :key="group.groupName" :title="group.groupName">
							<el-menu-item v-for="item in group.list" :key="item.component" :index="item.component"
								@click="openPage">
								<el-icon v-if="item.icon">
									<component :is="item.icon" />
								</el-icon>
								<template #title>
									<span>{{ item.title }}</span>
								</template>
							</el-menu-item>
						</el-menu-item-group>
					</el-menu>
				</el-main>
			</el-container>
		</el-aside>
		<el-main>
			<Suspense>
				<template #default>
					<div>
						<component :is="comArr[page]" />
					</div>

				</template>
				<template #fallback>
					<div><el-skeleton :rows="3" /></div>
				</template>
			</Suspense>
		</el-main>
	</el-container>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, shallowRef, defineAsyncComponent } from 'vue'
import store from '@/store'

const account = defineAsyncComponent(() => import('./user/account.vue'))
const seting = defineAsyncComponent(() => import('./user/seting.vue'))
const pushSettings = defineAsyncComponent(() => import('./user/pushSettings.vue'))
const password = defineAsyncComponent(() => import('./user/password.vue'))
const space = defineAsyncComponent(() => import('./user/space.vue'))
const logs = defineAsyncComponent(() => import('./user/logs.vue'))
const upToEnterprise = defineAsyncComponent(() => import('./user/upToEnterprise.vue'))

const comArr: any = shallowRef({
	"account": account,
	"seting": seting,
	"pushSettings": pushSettings,
	"password": password,
	"space": space,
	"logs": logs,
	"upToEnterprise": upToEnterprise
})

const menu = reactive([{
	groupName: "基本设置",
	list: [
		{
			icon: "el-icon-postcard",
			title: "账号信息",
			component: "account"
		},
		{
			icon: "el-icon-operation",
			title: "个人设置",
			component: "seting"
		},
		{
			icon: "el-icon-lock",
			title: "密码",
			component: "password"
		},
		{
			icon: "el-icon-bell",
			title: "通知设置",
			component: "pushSettings"
		}
	]
},
{
	groupName: "数据管理",
	list: [
		{
			icon: "el-icon-coin",
			title: "存储空间信息",
			component: "space"
		},
		{
			icon: "el-icon-clock",
			title: "操作日志",
			component: "logs"
		}
	]
},
{
	groupName: "账号升级",
	list: [
		{
			icon: "el-icon-office-building",
			title: "升级为企业账号",
			component: "upToEnterprise"
		}
	]
}])

const user = reactive({
	username: "",
	realName: "",
	avatar: ""
})
const page = ref("account")

const openPage = (item: any) => {
	page.value = item.index
}

onMounted(() => {
	user.username = store.userStore.user.username
	user.realName = store.userStore.user.realName
	user.avatar = store.userStore.user.avatar
})


</script>