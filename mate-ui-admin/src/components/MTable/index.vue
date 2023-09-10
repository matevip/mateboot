<template>
	<div class="scTable" :style="{ 'height': _height }" ref="scTableMain" v-loading="loading">
		<div class="scTable-table" :style="{ 'height': _table_height }">
			<el-table v-bind="$attrs" :data="tableData" :row-key="rowKey" :key="toggleIndex" ref="scTableRef"
				:height="height == 'auto' ? null : '100%'" :size="configPage.size" :border="configPage.border"
				:stripe="configPage.stripe" :summary-method="remoteSummary ? remoteSummaryMethod : summaryMethod"
				@sort-change="sortChange" @filter-change="filterChange">
				<slot></slot>
				<template v-for="(item, index) in userColumn" :key="index">
					<el-table-column v-if="!item.hide" :column-key="item.prop" :label="item.label" :prop="item.prop"
						:width="item.width" :sortable="item.sortable" :fixed="item.fixed" :filters="item.filters"
						:filter-method="remoteFilter || !item.filters ? null : filterHandler"
						:show-overflow-tooltip="item.showOverflowTooltip">
						<template #default="scope">
							<slot :name="item.prop" v-bind="scope">
								{{ scope.row[item.prop] }}
							</slot>
						</template>
					</el-table-column>
				</template>
				<el-table-column min-width="1"></el-table-column>
				<template #empty>
					<el-empty :description="emptyText" :image-size="100"></el-empty>
				</template>
			</el-table>
		</div>
		<div class="scTable-page" v-if="!hidePagination || !hideDo">
			<div class="scTable-pagination">
				<el-pagination v-if="!hidePagination" background :small="true" :layout="paginationLayout" :total="total"
					:page-size="scPageSize" :page-sizes="pageSizes" v-model:current-page="currentPage"
					@current-change="paginationChange" @update:page-size="pageSizeChange"></el-pagination>
			</div>
			<div class="scTable-do" v-if="!hideDo">
				<el-button v-if="!hideRefresh" @click="refresh" icon="el-icon-refresh" circle
					style="margin-left:15px"></el-button>
				<el-popover v-if="column" placement="top" title="列设置" :width="500" trigger="click" :hide-after="0"
					@show="customColumnShow = true" @after-leave="customColumnShow = false">
					<template #reference>
						<el-button icon="el-icon-set-up" circle style="margin-left:15px"></el-button>
					</template>
					<columnSetting v-if="customColumnShow" ref="columnSettingRef" @userChange="columnSettingChange"
						@save="columnSettingSave" @back="columnSettingBack" :column="userColumn"></columnSetting>
				</el-popover>
				<el-popover v-if="!hideSetting" placement="top" title="表格设置" :width="400" trigger="click" :hide-after="0">
					<template #reference>
						<el-button icon="el-icon-setting" circle style="margin-left:15px"></el-button>
					</template>
					<el-form label-width="80px" label-position="left">
						<el-form-item label="表格尺寸">
							<el-radio-group v-model="configPage.size" size="small" @change="configSizeChange">
								<el-radio-button label="large">大</el-radio-button>
								<el-radio-button label="default">正常</el-radio-button>
								<el-radio-button label="small">小</el-radio-button>
							</el-radio-group>
						</el-form-item>
						<el-form-item label="样式">
							<el-checkbox v-model="configPage.border" label="纵向边框"></el-checkbox>
							<el-checkbox v-model="configPage.stripe" label="斑马纹"></el-checkbox>
						</el-form-item>
					</el-form>
				</el-popover>
			</div>
		</div>
	</div>
</template>

<script lang="ts" setup>
import { ref, reactive, watch, computed, onMounted, onActivated, onDeactivated, PropType } from 'vue'
import { ElMessage } from 'element-plus'
import config from "@/config/table";
import columnSetting from './columnSetting.vue'

const props = defineProps({
	tableName: { type: String, default: "" },
	apiObj: { type: Function as PropType<(...args: any) => any>, default: null },
	params: { type: Object as PropType<any>, default: () => ({}) },
	data: { type: Object as PropType<any>, default: () => { } },
	height: { type: [String, Number], default: "100%" },
	size: { type: String, default: "default" },
	border: { type: Boolean, default: false },
	stripe: { type: Boolean, default: false },
	pageSize: { type: Number, default: config.pageSize },
	pageSizes: { type: Array, default: config.pageSizes },
	rowKey: { type: String, default: "" },
	summaryMethod: { type: Function, default: null },
	column: { type: Object as PropType<any>, default: () => { } },
	remoteSort: { type: Boolean, default: false },
	remoteFilter: { type: Boolean, default: false },
	remoteSummary: { type: Boolean, default: false },
	hidePagination: { type: Boolean, default: false },
	hideDo: { type: Boolean, default: false },
	hideRefresh: { type: Boolean, default: false },
	hideSetting: { type: Boolean, default: false },
	paginationLayout: { type: String, default: config.paginationLayout },
})

const scPageSize = ref(props.pageSize)
const isActivat = ref(true)
const emptyText = ref("暂无数据")
const toggleIndex = ref(0)

const tableData: any = ref([])
const total = ref(0)
const currentPage = ref(1)
const prop = ref(null)
const order = ref(null)
const loading = ref(false)
const tableHeight = ref("100%")
const tableParams = ref()
const userColumn: any = reactive({})
const customColumnShow = ref(false)
const summary: any = ref([])
const configPage = reactive({
	size: props.size,
	border: props.border,
	stripe: props.stripe
})
const emit = defineEmits(['dataChange'])
const scTableRef = ref()
const columnSettingRef = ref()

/**
 * 监视表格参数，作为查询条件
 */
watch(()=> props.params, (val) => {
	tableParams.value = val;
})

watch(() => props.data, (val) => {
	Object.assign(tableData, val)
})

watch(() => props.apiObj, (val) => {
	Object.assign(tableParams, val);
	refresh()
})


const _height = computed(() => Number(props.height) ? Number(props.height) + 'px' : props.height)

const _table_height = computed(() => props.hidePagination && props.hideDo ? "100%" : "calc(100% - 50px)")

onMounted(() => {
	//判断是否开启自定义列
	if (props.column) {
		getCustomColumn()
	} else {
		Object.assign(userColumn, props.column)
	}
	//判断是否静态数据
	if (props.apiObj != null) {
		getData();
	} else if (props.data) {
		Object.assign(tableData, props.data)
		total.value = tableData.value.length
	}
})

onActivated(() => {
	if (!isActivat.value) {
		scTableRef.value.doLayout()
	}
})

onDeactivated(() => {
	isActivat.value = false;
})

// 获取列
const getCustomColumn = async () => {
	const customColumn = await config.columnSettingGet(props.tableName, props.column)
	Object.assign(userColumn, customColumn)
}

// 获取数据
const getData = async () => {
	loading.value = true;
	var reqData = {
		[config.request.page]: currentPage.value,
		[config.request.pageSize]: scPageSize.value,
		[config.request.prop]: prop.value,
		[config.request.order]: order.value
	}
	if (props.hidePagination) {
		delete reqData[config.request.page]
		delete reqData[config.request.pageSize]
	}
	Object.assign(reqData, tableParams.value)

	try {
		// var res = await this.apiObj.get(reqData);
		var res = await props.apiObj(reqData);
	} catch (error: any) {
		loading.value = false;
		emptyText.value = error.statusText;
		return false;
	}
	try {
		var response = config.parseData(res);
	} catch (error) {
		loading.value = false;
		emptyText.value = "数据格式错误";
		return false;
	}
	if (response.code != config.successCode) {
		loading.value = false;
		emptyText.value = response.msg;
	} else {
		emptyText.value = "暂无数据";
		if (props.hidePagination) {
			tableData.value = response.data || []
		} else {
			tableData.value = response.rows || []
		}
		total.value = Number(response.total) || 0;
		Object.assign(summary, response.summary || {})
		loading.value = false;
	}
	scTableRef.value.setScrollTop(0)
	emit('dataChange', res, tableData)
}

//分页点击
const paginationChange = () => {
	getData();
}

//条数变化
const pageSizeChange = (size: number) => {
	scPageSize.value = size
	getData();
}

//刷新数据
const refresh = () => {
	scTableRef.value.clearSelection();
	getData();
}

//更新数据 合并上一次params
const upData = (params: any, page = 1) => {
	currentPage.value = page;
	scTableRef.value.clearSelection();
	Object.assign(tableParams, params || {})
	getData()
}

//重载数据 替换params
const reload = (params: any, page = 1) => {
	currentPage.value = page;
	Object.assign(tableParams, params || {})
	scTableRef.value.clearSelection();
	scTableRef.value.clearSort()
	scTableRef.value.clearFilter()
	getData()
}

//自定义变化事件
const columnSettingChange = (column: any) => {
	Object.assign(userColumn, column)
	toggleIndex.value += 1;
}

//自定义列保存
const columnSettingSave = async (column: any) => {
	columnSettingRef.value.isSave = true
	try {
		await config.columnSettingSave(props.tableName, column)
	} catch (error) {
		ElMessage.error('保存失败')
		columnSettingRef.value.isSave = false
	}
	ElMessage.success('保存成功')
	columnSettingRef.value.isSave = false
}

//自定义列重置
const columnSettingBack = async () => {
	columnSettingRef.value.isSave = true
	try {
		const column = await config.columnSettingReset(props.tableName, props.column)
		Object.assign(userColumn, column)
		columnSettingRef.value.usercolumn = JSON.parse(JSON.stringify(userColumn || []))
	} catch (error) {
		ElMessage.error('重置失败')
		columnSettingRef.value.isSave = false
	}
	columnSettingRef.value.isSave = false
}

//排序事件
const sortChange = (obj: any) => {
	if (!props.remoteSort) {
		return false
	}
	if (obj.column && obj.prop) {
		prop.value = obj.prop
		order.value = obj.order
	} else {
		prop.value = null
		order.value = null
	}
	getData()
}

//本地过滤
const filterHandler = (value: any, row: any, column: any) => {
	const property = column.property;
	return row[property] === value;
}

//过滤事件
const filterChange = (filters: any) => {
	if (!props.remoteFilter) {
		return false
	}
	Object.keys(filters).forEach(key => {
		filters[key] = filters[key].join(',')
	})
	upData(filters)
}

//远程合计行处理
const remoteSummaryMethod = (param: any) => {
	const { columns } = param
	const sums: any = []
	columns.forEach((column: any, index: any) => {
		if (index === 0) {
			sums[index] = '合计'
			return
		}
		const values = summary[column.property]
		if (values) {
			sums[index] = values
		} else {
			sums[index] = ''
		}
	})
	return sums
}

const configSizeChange = () => {
	scTableRef.value.doLayout()
}
//插入行 unshiftRow
const unshiftRow = (row: any) => {
	tableData.value.unshift(row)
}
//插入行 pushRow
const pushRow = (row: any) => {
	tableData.value.push(row)
}
//根据key覆盖数据
const updateKey = (row: any, rowKey = props.rowKey) => {
	tableData.value.filter((item: any) => item[rowKey] === row[rowKey]).forEach((item: any) => {
		Object.assign(item, row)
	})
}
//根据index覆盖数据
const updateIndex = (row: any, index: any) => {
	tableData.value[index] = row
}
//根据index删除
const removeIndex = (index: any) => {
	tableData.value.splice(index, 1)
}
//根据index批量删除
const removeIndexes = (indexes = []) => {
	indexes.forEach(index => {
		tableData.value.splice(index, 1)
	})
}
//根据key删除
const removeKey = (key: any, rowKey = props.rowKey) => {
	tableData.value.splice(tableData.value.findIndex((item: any) => item[rowKey] === key), 1)
}
//根据keys批量删除
const removeKeys = (keys = [], rowKey = props.rowKey) => {
	keys.forEach(key => {
		tableData.value.splice(tableData.value.findIndex((item: any) => item[rowKey] === key), 1)
	})
}
//原生方法转发
const clearSelection = () => {
	scTableRef.value.clearSelection()
}
const toggleRowSelection = (row: any, selected: any) => {
	scTableRef.value.toggleRowSelection(row, selected)
}
const toggleAllSelection = () => {
	scTableRef.value.toggleAllSelection()
}
const toggleRowExpansion = (row: any, expanded: any) => {
	scTableRef.value.toggleRowExpansion(row, expanded)
}
const setCurrentRow = (row: any) => {
	scTableRef.value.setCurrentRow(row)
}
const clearSort = () => {
	scTableRef.value.clearSort()
}
const clearFilter = (columnKey: any) => {
	scTableRef.value.clearFilter(columnKey)
}
const doLayout = () => {
	scTableRef.value.doLayout()
}
const sort = (prop: any, order: any) => {
	scTableRef.value.sort(prop, order)
}

defineExpose({
	upData,
	clearSelection,
	toggleRowSelection,
	toggleAllSelection,
	toggleRowExpansion,
	setCurrentRow,
	clearSort,
	clearFilter,
	doLayout,
	sort,
	removeKeys,
	removeKey,
	removeIndexes,
	updateIndex,
	removeIndex,
	updateKey,
	pushRow,
	unshiftRow,
	reload,
	refresh
})

</script>

<style scoped>
.scTable {}

.scTable-table {
	height: calc(100% - 50px);
}

.scTable-page {
	height: 50px;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 15px;
}

.scTable-do {
	white-space: nowrap;
}

.scTable:deep(.el-table__footer) .cell {
	font-weight: bold;
}

.scTable:deep(.el-table__body-wrapper) .el-scrollbar__bar.is-horizontal {
	height: 12px;
	border-radius: 12px;
}

.scTable:deep(.el-table__body-wrapper) .el-scrollbar__bar.is-vertical {
	width: 12px;
	border-radius: 12px;
}
</style>
