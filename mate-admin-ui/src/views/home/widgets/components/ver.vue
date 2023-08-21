<template>
	<el-card shadow="hover" header="版本信息">
		<div style="height: 210px;text-align: center;">
			<img src="/img/ver.svg" style="height:140px" />
			<h2 style="margin-top: 15px;">MateBoot Admin {{ mateConfig.CORE_VER }}</h2>
			<p style="margin-top: 5px;">最新版本 {{ version }}</p>
		</div>
		<div style="margin-top: 20px;">
			<el-button type="primary" plain round @click="golog">更新日志</el-button>
			<el-button type="primary" plain round @click="gogit">gitee</el-button>
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
const gogit = () => {
	window.open("https://gitee.com/matevip/mateboot")
}
</script>
