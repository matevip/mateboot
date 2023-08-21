<template>
	<el-form ref="form" label-width="120px" label-position="left" style="padding:0 20px;">
		<el-alert title="以下配置可实时预览，开发者可在 config/index.js 中配置默认值，非常不建议在生产环境下开放布局设置" type="error"
			:closable="false"></el-alert>
		<el-divider></el-divider>
		<el-form-item :label="$t('user.darkmode')">
			<el-switch v-model="dark" inline-prompt active-icon="el-icon-moon"
				inactive-icon="el-icon-sunny"></el-switch>
			<div class="el-form-item-msg">{{ $t('user.darkmode_msg') }}</div>
		</el-form-item>
		<el-form-item :label="$t('user.language')">
			<el-select v-model="lang">
				<el-option label="简体中文" value="zh-cn"></el-option>
				<el-option label="English" value="en"></el-option>
			</el-select>
		</el-form-item>
		<el-divider></el-divider>
		<el-form-item :label="$t('user.theme_color')">
			<el-color-picker v-model="colorPrimary" :predefine="colorList">></el-color-picker>
		</el-form-item>
		<el-divider></el-divider>
		<el-form-item label="框架布局">
			<el-select v-model="layout" placeholder="请选择">
				<el-option label="默认" value="default"></el-option>
				<el-option label="通栏" value="header"></el-option>
				<el-option label="经典" value="menu"></el-option>
				<el-option label="功能坞" value="dock"></el-option>
			</el-select>
		</el-form-item>
		<el-form-item label="折叠菜单">
			<el-switch v-model="menuIsCollapse"></el-switch>
		</el-form-item>
		<el-form-item label="标签栏">
			<el-switch v-model="layoutTags"></el-switch>
		</el-form-item>
		<el-divider></el-divider>
	</el-form>
</template>

<script lang="ts" setup>
import { ref, watch } from 'vue'
import { lighten, darken } from '@/utils/color'
import store from '@/store'
import cache from '@/utils/cache'
import { useI18n } from 'vue-i18n';

const i18n = useI18n();
const layout = ref(store.globalStore.layout);
const menuIsCollapse = ref(store.globalStore.menuIsCollapse)
const layoutTags = ref(store.globalStore.layoutTags)
const lang = ref(cache.getLanguage())
const dark = ref(cache.getDark())
const colorList = ref(['#0058DC', '#009688', '#536dfe', '#ff5c93', '#c62f2f', '#fd726d'])
const colorPrimary = ref(cache.getColor())

watch(layout, (val) => {
	store.globalStore.setLayout(val)
})
watch(menuIsCollapse, () => {
	store.globalStore.toggleMenuIsCollapse()
})
watch(layoutTags, () => {
	store.globalStore.toggleLayoutTags()
})
watch(dark, (val) => {
	if (val) {
		document.documentElement.classList.add("dark")
		cache.setDark(val)
	} else {
		document.documentElement.classList.remove("dark")
		cache.removeDark()
	}
})
watch(lang, (val) => {
	i18n.locale.value = val
	cache.setLanguage(val)
})
watch(colorPrimary, (val: string) => {
	if (!val) {
		val = '#0058DC'
		colorPrimary.value = '#0058DC'
	}
	document.documentElement.style.setProperty('--el-color-primary', val);
	for (let i = 1; i <= 9; i++) {
		document.documentElement.style.setProperty(`--el-color-primary-light-${i}`, lighten(val, i / 10));
	}
	for (let i = 1; i <= 9; i++) {
		document.documentElement.style.setProperty(`--el-color-primary-dark-${i}`, darken(val, i / 10));
	}
	cache.setColor(val)
})
</script>

<style>

</style>
