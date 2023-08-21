<template>
	<el-card shadow="never" header="近7天操作记录">
		<m-table ref="table" :data="data" height="auto" paginationLayout="total, prev, pager, next" hideDo>
			<m-table-column label="序号" type="index"></m-table-column>
			<m-table-column label="业务名称" prop="title"></m-table-column>
			<m-table-column label="IP" prop="ip"></m-table-column>
			<m-table-column label="结果" prop="code">
				<el-tag type="success">成功</el-tag>
			</m-table-column>
			<m-table-column label="操作时间" prop="time"></m-table-column>

		</m-table>
	</el-card>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import MTable from '@/components/MTable/index.vue'
import MTableColumn from '@/components/MTable/column'

const data = ref([
	{
		title: "修改用户 matecloud",
		ip: "211.187.11.18",
		code: "成功",
		time: "2022-12-10 08:41:17"
	},
	{
		title: "用户登录",
		ip: "211.187.11.18",
		code: "成功",
		time: "2022-12-10 08:21:51"
	}
])
</script>

<style>

</style>
