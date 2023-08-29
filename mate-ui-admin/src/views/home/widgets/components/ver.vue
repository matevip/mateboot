<template>
	<el-card shadow="hover" header="版本信息">
		<div class="h-52 text-center">
			<img class="h-35" src="/img/ver.svg" />
			<h2 class="mt-4 font-medium">MateBoot Admin {{ mateConfig.CORE_VER }}</h2>
			<p class="m-1">最新版本 {{ version }}</p>
		</div>
		<div class="mt-6 flex flex-wrap justify-center items-center">
			<el-button type="primary" plain round @click="golog">更新日志</el-button>
			<el-button type="primary" plain round @click="gogitee">gitee</el-button>
			<el-button type="primary" plain round @click="gogithub">github</el-button>
		</div>
	</el-card>
</template>

<script lang="ts">
export default {
	title: "版本信息",
	icon: "el-icon-monitor",
	description: "当前项目版本信息",
}
</script>
<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useVersion } from '@/api/model/auth';
import mateConfig from '@/config'

const version = ref("loading...")

onMounted(() => {
	getVer()
})

const getVer = async () => {
	const verData = await useVersion()
	version.value = verData.data
}


const golog = () => {
	window.open("https://gitee.com/matevip/mateboot/releases")
}
const gogitee = () => {
	window.open("https://gitee.com/matevip/mateboot")
}
const gogithub = () => {
	window.open("https://github.com/matevip/mateboot")
}
</script>
