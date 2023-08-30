<!--
 * @Descripttion: 移动端菜单
 * @version: 1.0
 * @Author: matevip
 * @Date: 2022年12月28日
 * @LastEditors:
 * @LastEditTime:
-->
<template>
	<div ref="" class="mobile-nav-button" @click="showMobileNav($event)" v-drag draggable="false">
		<el-icon><el-icon-menu /></el-icon>
	</div>

	<el-drawer ref="mobileNavBoxRef" title="移动端菜单" :size="240" v-model="nav" direction="ltr" :with-header="false"
		destroy-on-close>
		<el-container class="mobile-nav">
			<el-header>
				<div class="logo-bar"><img class="logo" src="/img/logo.png"><span>{{ mateConfig.APP_NAME }}</span></div>
			</el-header>
			<el-main>
				<el-scrollbar>
					<el-menu :default-active="route.meta.active || route.fullPath" @select="select" router
						background-color="#212d3d" text-color="#fff" active-text-color="#3265f5">
						<NavMenu :navMenus="menu"></NavMenu>
					</el-menu>
				</el-scrollbar>
			</el-main>
		</el-container>
	</el-drawer>

</template>

<script lang="ts" setup>
import { ref, onMounted, defineAsyncComponent } from 'vue'
import { useRoute } from 'vue-router'
import router from '@/router';
import mateConfig from '@/config'
import store from '@/store'

const NavMenu = defineAsyncComponent(() => import('./NavMenu.vue'))

const nav = ref(false)
const menu = ref([])
const mobileNavBoxRef = ref()
const route = useRoute()

onMounted(() => {
	// var menuData = router.sc_getMenu()
	// menu.value = filterUrl(menuData)
	menu.value = store.userStore.menuList!
})

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
			item.children = filterUrl(item.children);
		}
		newMap.push(item)
	})
	return newMap;
}

const showMobileNav = (e: any) => {
	var isdrag = e.currentTarget.getAttribute('drag-flag')
	if (isdrag == 'true') {
		return false;
	} else {
		nav.value = true;
	}

}

const select = () => {
	mobileNavBoxRef.value.handleClose()
}

</script>

<style scoped>
.mobile-nav-button {
	position: fixed;
	bottom: 10px;
	left: 10px;
	z-index: 10;
	width: 50px;
	height: 50px;
	background: #3265f5;
	box-shadow: 0 2px 12px 0 rgba(64, 158, 255, 1);
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
}

.mobile-nav-button i {
	color: #fff;
	font-size: 20px;
}

.mobile-nav {
	background: #212d3d;
}

.mobile-nav .el-header {
	background: transparent;
	border: 0;
}

.mobile-nav .el-main {
	padding: 0;
}

.mobile-nav .logo-bar {
	display: flex;
	align-items: center;
	font-weight: bold;
	font-size: 20px;
	color: #fff;
}

.mobile-nav .logo-bar img {
	width: 30px;
	margin-right: 10px;
}

.mobile-nav .el-submenu__title:hover {
	background: #fff !important;
}
</style>
