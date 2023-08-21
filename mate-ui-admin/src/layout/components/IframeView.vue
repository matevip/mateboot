<template>
	<div v-show="route.meta.type == 'iframe'" class="iframe-pages">
		<iframe v-for="item in iframeList" :key="item.meta.url" v-show="route.meta.url == item.meta.url"
			:src="item.meta.url" frameborder='0'></iframe>
	</div>
</template>

<script lang="ts" setup>
import { watch, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'

import store from '@/store'

//@Todo
const route = useRoute();
const iframeList = computed(() => {
	return store.iframeStore.iframeList
})
const ismobile = computed(() => {
	return store.globalStore.ismobile
})
const layoutTags = computed(() => {
	return store.globalStore.layoutTags
})

watch(() => route, (val) => {
	push(val)
},
{ deep: true }
)
// watch(route, (r) => {
// 	push(r)
// })

onMounted(() => {
	push(route)
})


const push = (route: any) => {
	if (route.meta.type == 'iframe') {
		if (ismobile || !layoutTags) {
			store.iframeStore.setIframeList(route)
		} else {
			store.iframeStore.pushIframeList(route)
		}
	} else {
		if (ismobile || !layoutTags) {
			store.iframeStore.clearIframeList()
		}
	}
}
</script>

<style scoped>
.iframe-pages {
	width: 100%;
	height: 100%;
	background: #fff;
}

iframe {
	border: 0;
	width: 100%;
	height: 100%;
	display: block;
}
</style>
