<template>
	<!-- 通栏布局 -->
	<template v-if="layout == 'header'">
		<header class="adminui-header">
			<div class="adminui-header-left">
				<Logo :app-name="mateConfig.APP_NAME" />
				<ul v-if="!ismobile" class="nav">
					<li v-for="item in menu" :key="item" :class="pmenu.path == item.path ? 'active' : ''"
						@click="showMenu(item)">
						<el-icon>
							<component :is="item.meta.icon || 'el-icon-menu'" />
						</el-icon>
						<span>{{ item.meta.title }}</span>
					</li>
				</ul>
			</div>
			<div class="adminui-header-right">
				<user-bar></user-bar>
			</div>
		</header>
		<section class="adminui-wrapper">
			<div v-if="!ismobile && nextMenu.length > 0 || !pmenu.component"
				:class="menuIsCollapse ? 'adminui-side isCollapse' : 'adminui-side'">
				<div v-if="!menuIsCollapse" class="adminui-side-top">
					<h2>{{ pmenu.meta.title }}</h2>
				</div>
				<div class="adminui-side-scroll">
					<el-scrollbar>
						<el-menu :default-active="active" router :collapse="menuIsCollapse"
							:unique-opened="mateConfig.MENU_UNIQUE_OPENED">
							<NavMenu :navMenus="nextMenu"></NavMenu>
						</el-menu>
					</el-scrollbar>
				</div>
				<div class="adminui-side-bottom" @click="store.globalStore.toggleMenuIsCollapse()">
					<el-icon><el-icon-expand v-if="menuIsCollapse" /><el-icon-fold v-else /></el-icon>
				</div>
			</div>
			<side-menu v-if="ismobile"></side-menu>
			<div class="adminui-body el-container">
				<top-bar v-if="!ismobile"></top-bar>
				<tags v-if="!ismobile && layoutTags"></tags>
				<div class="adminui-main" id="adminui-main">
					<router-view v-slot="{ Component }">
						<keep-alive :include="store.keepAliveStore.keepLiveRoute">
							<component :is="Component" :key="route.fullPath" v-if="store.keepAliveStore.routeShow" />
						</keep-alive>
					</router-view>
					<iframe-view></iframe-view>
				</div>
			</div>
		</section>
	</template>

	<!-- 经典布局 -->
	<template v-else-if="layout == 'menu'">
		<header class="adminui-header">
			<div class="adminui-header-left">
				<Logo :app-name="mateConfig.APP_NAME" />
			</div>
			<div class="adminui-header-right">
				<user-bar></user-bar>
			</div>
		</header>
		<section class="adminui-wrapper">
			<div v-if="!ismobile" :class="menuIsCollapse ? 'adminui-side isCollapse' : 'adminui-side'">
				<div class="adminui-side-scroll">
					<el-scrollbar>
						<el-menu :default-active="active" router :collapse="menuIsCollapse"
							:unique-opened="mateConfig.MENU_UNIQUE_OPENED">
							<NavMenu :navMenus="menu"></NavMenu>
						</el-menu>
					</el-scrollbar>
				</div>
				<div class="adminui-side-bottom" @click="store.globalStore.toggleMenuisCollapse()">
					<el-icon><el-icon-expand v-if="menuIsCollapse" /><el-icon-fold v-else /></el-icon>
				</div>
			</div>
			<side-menu v-if="ismobile"></side-menu>
			<div class="adminui-body el-container">
				<top-bar v-if="!ismobile"></top-bar>
				<tags v-if="!ismobile && layoutTags"></tags>
				<div class="adminui-main" id="adminui-main">
					<router-view v-slot="{ Component }">
						<keep-alive :include="store.keepAliveStore.keepLiveRoute">
							<component :is="Component" :key="route.fullPath" v-if="store.keepAliveStore.routeShow" />
						</keep-alive>
					</router-view>
					<iframe-view></iframe-view>
				</div>
			</div>
		</section>
	</template>

	<!-- 功能坞布局 -->
	<template v-else-if="layout == 'dock'">
		<header class="adminui-header">
			<div class="adminui-header-left">
				<Logo :app-name="mateConfig.APP_NAME" />
			</div>
			<div class="adminui-header-right">
				<div v-if="!ismobile" class="adminui-header-menu">
					<el-menu mode="horizontal" :default-active="active" router background-color="#222b45" text-color="#fff"
						active-text-color="var(--el-color-primary)">
						<NavMenu :navMenus="menu"></NavMenu>
					</el-menu>
				</div>
				<side-menu v-if="ismobile"></side-menu>
				<user-bar></user-bar>
			</div>
		</header>
		<section class="adminui-wrapper">
			<div class="adminui-body el-container">
				<tags v-if="!ismobile && layoutTags"></tags>
				<div class="adminui-main" id="adminui-main">
					<router-view v-slot="{ Component }">
						<keep-alive :include="store.keepAliveStore.keepLiveRoute">
							<component :is="Component" :key="route.fullPath" v-if="store.keepAliveStore.routeShow" />
						</keep-alive>
					</router-view>
					<iframe-view></iframe-view>
				</div>
			</div>
		</section>
	</template>

	<!-- 默认布局 -->
	<template v-else>
		<section class="adminui-wrapper">
			<div v-if="!ismobile" class="adminui-side-split">
				<div class="adminui-side-split-top">
					<router-link :to="mateConfig.DASHBOARD_URL">
						<img class="logo" :title="mateConfig.APP_NAME" src="/img/logo-r.png">
					</router-link>
				</div>
				<div class="adminui-side-split-scroll">
					<el-scrollbar>
						<ul>
							<li v-for="item in menu" :key="item" :class="pmenu.path == item.path ? 'active' : ''"
								@click="showMenu(item)">
								<el-icon>
									<component :is="item.meta.icon || 'el-icon-menu'" />
								</el-icon>
								<p>{{ item.meta.title.substring(0, 2) }}</p>
							</li>
						</ul>
					</el-scrollbar>
				</div>
			</div>
			<div v-if="!ismobile && nextMenu.length > 0 || !pmenu.component"
				:class="menuIsCollapse ? 'adminui-side isCollapse' : 'adminui-side'">
				<div v-if="!menuIsCollapse" class="adminui-side-top">
					<h2>{{ pmenu.meta.title }}</h2>
				</div>
				<div class="adminui-side-scroll">
					<el-scrollbar>
						<el-menu :default-active="active" router :collapse="menuIsCollapse"
							:unique-opened="mateConfig.MENU_UNIQUE_OPENED">
							<NavMenu :navMenus="nextMenu"></NavMenu>
						</el-menu>
					</el-scrollbar>
				</div>
				<div class="adminui-side-bottom" @click="store.globalStore.toggleMenuIsCollapse()">
					<el-icon><el-icon-expand v-if="menuIsCollapse" /><el-icon-fold v-else /></el-icon>
				</div>
			</div>
			<side-menu v-if="ismobile"></side-menu>
			<div class="adminui-body el-container">
				<top-bar>
					<user-bar></user-bar>
				</top-bar>
				<tags v-if="!ismobile && layoutTags"></tags>
				<div class="adminui-main" id="adminui-main">
					<router-view v-slot="{ Component }">
						<keep-alive :include="store.keepAliveStore.keepLiveRoute">
							<component :is="Component" :key="route.fullPath" v-if="store.keepAliveStore.routeShow" />
						</keep-alive>
					</router-view>
					<iframe-view></iframe-view>
				</div>
			</div>
		</section>
	</template>

	<div class="main-maximize-exit" @click="exitMaximize"><el-icon><el-icon-close /></el-icon></div>

	<div class="layout-setting" @click="openSetting"><el-icon><el-icon-brush-filled /></el-icon></div>

	<el-drawer title="布局实时演示" v-model="settingDialog" :size="400" append-to-body destroy-on-close>
		<setting></setting>
	</el-drawer>
</template>

<script lang="ts" setup>
import { ref, onBeforeMount, computed, nextTick, watch } from 'vue';
import SideMenu from './components/SideMenu.vue';
import TopBar from './components/TopBar.vue';
import Tags from './components/Tags.vue';
import NavMenu from './components/NavMenu.vue';
import UserBar from './components/UserBar.vue';
import Setting from './components/Setting.vue';
import IframeView from './components/IframeView.vue';
import Logo from './components/Logo.vue'
import mateConfig from '@/config'
import store from '@/store'
import { useRoute } from 'vue-router';
import router from '@/router'

const settingDialog = ref(false)
const menu: any = ref([])
const nextMenu = ref([])
const pmenu: any = ref([])
const active: any = ref('')

const route = useRoute()
const ismobile = computed(() => {
	return store.globalStore.ismobile
})
const layout = computed(() => {
	return store.globalStore.layout
})
const menuIsCollapse = computed(() => {
	return store.globalStore.menuIsCollapse
})
const layoutTags = computed(() => {
	return store.globalStore.layoutTags
})


// watch(() => route, () => {
// 	showThis()
// })

watch(layout, (val) => {
	document.body.setAttribute('data-layout', val)
})

onBeforeMount(() => {
	onLayoutResize();
	window.addEventListener('resize', onLayoutResize);
	menu.value = store.userStore.menuList!
	showThis()
})

const openSetting = () => {
	settingDialog.value = true;
}
const onLayoutResize = () => {
	store.globalStore.setIsmobile(document.body.clientWidth < 992)
}
//路由监听高亮
const showThis = async () => {
	pmenu.value = route.meta.breadcrumb ? route.meta.breadcrumb[0] : {}
	nextMenu.value = filterUrl(pmenu.value.children)
	await nextTick()
	active.value = route.meta.active || route.fullPath
}
//点击显示
const showMenu = (route: any) => {
	pmenu.value = route;
	nextMenu.value = filterUrl(route.children);
	if ((!route.children || route.children.length == 0) && route.component) {
		router.push({ path: route.path })
	}
}
//转换外部链接的路由
const filterUrl = (map: any) => {
	var newMap: any = []
	map && map.forEach((item: any) => {
		item.meta = item.meta ? item.meta : {};
		//处理隐藏
		if (item.meta.hidden || item.meta.type == "button") {
			return false
		}
		//处理http
		if (item.meta.type == 'iframe') {
			item.path = `/i/${item.name}`;
		}
		//递归循环
		if (item.children && item.children.length > 0) {
			item.children = filterUrl(item.children)
		}
		newMap.push(item)
	})
	return newMap;
}
//退出最大化
const exitMaximize = () => {
	document.getElementById('app')?.classList.remove('main-maximize')
}
</script>
