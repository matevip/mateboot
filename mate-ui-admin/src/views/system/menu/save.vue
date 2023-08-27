<template>
	<el-row :gutter="40">
		<el-col v-if="!form.id">
			<el-empty description="请选择左侧菜单后操作" :image-size="100"></el-empty>
		</el-col>
		<template v-else>
			<el-col :lg="12">
				<h2>{{form.title || "新增菜单"}}</h2>
				<el-form :model="form" :rules="rules" ref="dialogFormRef" label-width="80px" label-position="left">
					<el-form-item label="显示名称" prop="title">
						<el-input v-model="form.title" clearable placeholder="菜单显示名字"></el-input>
					</el-form-item>
					<el-form-item label="上级菜单" prop="pid">
						<el-cascader v-model="form.pid" :options="menuOptions" :props="menuProps" :show-all-levels="false" placeholder="顶级菜单" clearable disabled></el-cascader>
					</el-form-item>
					<el-form-item label="类型" prop="meta.type">
						<el-radio-group v-model="form.type">
							<el-radio-button label="0">菜单</el-radio-button>
							<el-radio-button label="2">Iframe</el-radio-button>
							<el-radio-button label="3">外链</el-radio-button>
							<!-- <el-radio-button label="3">按钮</el-radio-button> -->
						</el-radio-group>
					</el-form-item>
					<el-form-item label="别名" prop="name">
						<el-input v-model="form.name" clearable placeholder="菜单别名"></el-input>
						<div class="el-form-item-msg">系统唯一且与内置组件名一致，否则导致缓存失效。如类型为Iframe的菜单，别名将代替源地址显示在地址栏</div>
					</el-form-item>
					<el-form-item label="菜单图标" prop="meta.icon">
						<m-icon-select v-model="form.icon" clearable></m-icon-select>
					</el-form-item>
					<el-form-item label="路由地址" prop="path">
						<el-input v-model="form.path" clearable placeholder=""></el-input>
					</el-form-item>
					<!-- <el-form-item label="重定向" prop="redirect">
						<el-input v-model="form.redirect" clearable placeholder=""></el-input>
					</el-form-item> -->
					<!-- <el-form-item label="菜单高亮" prop="active">
						<el-input v-model="form.active" clearable placeholder=""></el-input>
						<div class="el-form-item-msg">子节点或详情页需要高亮的上级菜单路由地址</div>
					</el-form-item> -->
					<el-form-item label="组件" prop="component">
						<el-input v-model="form.component" clearable placeholder="">
							<template #prepend>views/</template>
						</el-input>
						<div class="el-form-item-msg">如父节点、链接或Iframe等没有视图的菜单不需要填写</div>
					</el-form-item>
					<el-form-item label="颜色" prop="color">
						<el-color-picker v-model="form.color" :predefine="predefineColors"></el-color-picker>

					</el-form-item>
					<el-form-item label="是否隐藏" prop="hidden">
						<el-checkbox v-model="form.hidden">隐藏菜单</el-checkbox>
						<el-checkbox v-model="form.hiddenBreadcrumb">隐藏面包屑</el-checkbox>
						<div class="el-form-item-msg">菜单不显示在导航中，但用户依然可以访问，例如详情页</div>
					</el-form-item>
					<!-- <el-form-item label="整页路由" prop="fullpage">
						<el-switch v-model="form.fullpage" />
					</el-form-item> -->
					<el-form-item label="标签" prop="tag">
						<el-input v-model="form.tag" clearable placeholder=""></el-input>
					</el-form-item>
					<el-form-item label="排序" prop="sort">
						<el-input-number v-model="form.sort" controls-position="right" :min="0" style="width: 100%;"></el-input-number>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="save" :loading="loading">保 存</el-button>
					</el-form-item>
				</el-form>

			</el-col>
			<el-col :lg="12" class="buttonlist">
				<h2>资源</h2>
				<m-form-table v-model="form.buttonList" :addTemplate="buttonListAddTemplate" placeholder="暂无匹配资源信息">
					<el-table-column prop="title" label="按钮名称" width="150">
						<template #default="scope">
							<el-input v-model="scope.row.title" placeholder="请输入按钮名称"></el-input>
						</template>
					</el-table-column>
					<el-table-column prop="authority" label="授权标识">
						<template #default="scope">
							<el-input v-model="scope.row.authority" placeholder="请输入授权标识"></el-input>
						</template>
					</el-table-column>
				</m-form-table>
			</el-col>
		</template>
	</el-row>

</template>

<script lang="ts" setup>
import { ref, reactive, watch } from 'vue'
import { ElMessage } from 'element-plus'
import MIconSelect from '@/components/MIconSelect/index.vue'
import MFormTable from '@/components/MFormTable/index.vue'
import { useMenuSave, useButtonList } from '@/api/system/menu'

const props = defineProps({
	menu: {
		type: Object,
		default: () => {}
	}
})

const form = reactive({
	id: "",
	pid: "",
	name: "",
	path: "",
	component: "",
	redirect: "",
	title: "",
	icon: "",
	active: "",
	color: "",
	type: "menu",
	fullpage: 0,
	hidden: 0,
	hiddenBreadcrumb: 0,
	tag: "",
	sort: 0,
	buttonList: []
})
const menuProps = reactive({
	value: 'id',
	label: 'title',
	checkStrictly: true
})
const buttonListAddTemplate = reactive({
	title: "",
	authority: "",
	type: '',
	pid: 0,
	id: 0
})

const menuOptions = ref([])
const predefineColors = ref([
	'#ff4500',
	'#ff8c00',
	'#ffd700',
	'#67C23A',
	'#00ced1',
	'#0058DC',
	'#c71585'
])
const rules = ref([])
const loading = ref(false)
const dialogFormRef = ref()
const emit = defineEmits(['getMenu'])

watch(()=> props.menu, (val)=>{
	menuOptions.value = treeToMap(val)
})

//简单化菜单
const treeToMap = (tree: any) => {
	const map = [] as any
	tree.forEach((item: any) => {
		var obj = {
			id: item.id,
			pid: item.pid,
			title: item.title,
			children: item.children && item.children.length > 0 ? treeToMap(item.children) : null
		}
		map.push(obj)
	})
	return map
}

//保存
const save = async () => {
	loading.value = true
	var res = await useMenuSave(form) as any
	loading.value = false
	if (res.code == 0) {
		emit('getMenu');
		ElMessage.success("保存成功")
	} else {
		ElMessage.warning(res.message)
	}
}

//表单注入数据
const setData = async (data: any, pid: string) => {
	Object.assign(form, data)
	form.buttonList = []
	if (data.id) {
		const res = await useButtonList(data.id)
		// 从接口获取
		Object.assign(form.buttonList, res.data)
	} 
	form.pid = pid
}

defineExpose({
	setData
})
</script>

<style scoped>
	h2 {font-size: 17px;color: #3c4a54;padding:0 0 30px 0;}
	.buttonList {border-left: 1px solid #eee;}

	[data-theme="dark"] h2 {color: #fff;}
	[data-theme="dark"] .apilist {border-color: #434343;}
</style>
