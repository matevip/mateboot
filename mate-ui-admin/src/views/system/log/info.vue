<template>
	<el-main style="padding:0 20px;">
		<el-descriptions :column="1" border size="small">
			<el-descriptions-item label="请求接口">{{ form.method }}</el-descriptions-item>
			<el-descriptions-item label="请求URI">{{ form.reqUri }}</el-descriptions-item>
			<el-descriptions-item label="请求方法">{{ form.reqMethod }}</el-descriptions-item>
			<el-descriptions-item label="状态代码">
				<div v-if="form.status == 0">
					<el-tag type="success">成功</el-tag>
				</div>
				<div v-else-if="form.status == 1">
					<el-tag type="danger">失败</el-tag>
				</div>
			</el-descriptions-item>
			<el-descriptions-item label="日志名">{{ form.name }}</el-descriptions-item>
			<el-descriptions-item label="日志时间">{{ form.createTime }}</el-descriptions-item>
		</el-descriptions>
		<el-collapse v-model="activeNames" style="margin-top: 20px;">
			<el-collapse-item title="请求" name="1">
					<el-space>
					<VueJsonPretty v-if="form.reqParams" :path="'res'" :data="JSON.parse(form.reqParams)"
						:show-length="true" />
					<span v-else>无</span>
				</el-space>
			</el-collapse-item>
			<el-collapse-item title="响应" name="2">
				<el-space>
					<VueJsonPretty v-if="form.jsonResult" :path="'res'" :data="JSON.parse(form.jsonResult)"
						:show-length="true" />
					<span v-else>无</span>
				</el-space>
			</el-collapse-item>
			<el-collapse-item title="异常" name="3">
				<el-space>
					<VueJsonPretty v-if="form.errorMsg" :path="'res'" :data="JSON.parse(form.errorMsg)"
						:show-length="true" />
					<span v-else>无</span>

				</el-space>
			</el-collapse-item>
		</el-collapse>
	</el-main>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import VueJsonPretty from 'vue-json-pretty';
import 'vue-json-pretty/lib/styles.css';

const form = reactive({})
const activeNames = ref(['1'])
const typeMap = reactive({
	'info': "info",
	'warn': "warning",
	'error': "error"
})

const setData = (row: any) => {
	Object.assign(form, row)
}
defineExpose({
	setData
})
</script>

<style scoped>
.code {
	background: #848484;
	padding: 15px;
	color: #fff;
	font-size: 12px;
	border-radius: 4px;
}
</style>
