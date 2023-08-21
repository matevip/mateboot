<template>
	<el-config-provider :locale="locale" :size="config.size">
		<router-view />
	</el-config-provider>
</template>

<script lang="ts" setup>
import { reactive, onMounted, nextTick, computed } from 'vue'
import { handleThemeStyle } from '@/utils/theme'
import { RouterView } from 'vue-router'
import { messages } from '@/locales'
import store from '@/store'

onMounted(() => {
	nextTick(() => {
		// 初始化主题样式
		handleThemeStyle()
	})
})

const locale = computed(() => messages[store.globalStore.language].el)

const config = reactive({
	size: "default",
	zIndex: 2000,
	button: {
		autoInsertSpace: false
	}
})
</script>

<style lang="scss">

</style>
