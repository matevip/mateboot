<template>
	<el-card shadow="never" header="语言主题">
		<el-form ref="formRef" label-width="120px" style="margin-top:20px;">
			<el-form-item :label="$t('user.darkmode')">
				<el-switch v-model="config.dark" inline-prompt active-icon="el-icon-moon"
					inactive-icon="el-icon-sunny"></el-switch>
				<div class="el-form-item-msg">{{ $t('user.darkmode_msg') }}</div>
			</el-form-item>
			<el-form-item :label="$t('user.theme_color')">
				<el-color-picker v-model="config.colorPrimary" :predefine="colorList">></el-color-picker>
			</el-form-item>
			<el-form-item :label="$t('user.language')">
				<el-select v-model="config.lang">
					<el-option label="简体中文" value="zh-cn"></el-option>
					<el-option label="English" value="en"></el-option>
				</el-select>
				<div class="el-form-item-msg">{{ $t('user.language_msg') }}</div>
			</el-form-item>
		</el-form>
	</el-card>
</template>

<script lang="ts" setup>
import { ref, reactive, watch, onMounted } from 'vue'
import { lighten, darken } from '@/utils';
import { useI18n } from 'vue-i18n';
import cache from '@/utils/cache';

const i18n = useI18n();
const colorList = ref(['#3265f5', '#02a863', '#536dfe', '#ff5c93', '#c62f2f', '#fd726d'])
const config = reactive({
	lang: cache.getLanguage(),
	dark: cache.getDark(),
	colorPrimary: cache.getColor()
})

watch(() => config.dark, (val) => {
	if (val) {
		document.documentElement.classList.add("dark")
		cache.setDark(val)
	} else {
		document.documentElement.classList.remove("dark")
		cache.removeDark()
	}
})

watch(() => config.lang, (val) => {
	i18n.locale.value = val
	cache.setLanguage(val)
})

watch(() => config.colorPrimary, (val) => {
	if (!val) {
		val = '#3265f5'
		config.colorPrimary = '#3265f5'
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
