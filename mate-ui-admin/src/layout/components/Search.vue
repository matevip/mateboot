<template>
	<div class="sc-search">
		<el-input ref="inputRef" v-model="input" placeholder="搜索" size="large" clearable prefix-icon="el-icon-search"
			:trigger-on-focus="false" @input="inputChange" />
		<div class="sc-search-history" v-if="history.length > 0">
			<el-tag closable effect="dark" type="info" v-for="(item, index) in history" :key="item"
				@click="historyClick(item)" @close="historyClose(index)">{{ item }}</el-tag>
		</div>
		<div class="sc-search-result">
			<div class="sc-search-no-result" v-if="result.length <= 0">暂无搜索结果</div>
			<ul v-else>
				<el-scrollbar max-height="366px">
					<li v-for="item in result" :key="item.path" @click="to(item)">
						<el-icon>
							<component :is="item.icon || 'el-icon-menu'" />
						</el-icon>
						<span class="title">{{ item.breadcrumb }}</span>
					</li>
				</el-scrollbar>
			</ul>
		</div>
	</div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'

import { Storage } from '@/utils/storage'
import router from '@/router'
import CacheKey from '@/utils/cache/key'

const input = ref("")
const menu: any = ref([])
const result: any = ref([])
const history: any = ref([])
const inputRef = ref()

const emit = defineEmits([('success')])

onMounted(() => {
	const searchHistory = Storage.getItem("SEARCH_HISTORY") || []
	history.value = searchHistory;
	const menuTree = Storage.getItem(CacheKey.MenuKey) || []
	filterMenu(menuTree)
	inputRef.value.focus()
})

const filterMenu = (map: any) => {
	map.forEach((item: any) => {
		if (item.meta.hidden || item.meta.type == "button") {
			return false
		}
		if (item.meta.type == 'iframe') {
			item.path = `/i/${item.name}`
		}
		if (item.children && item.children.length > 0 && !item.component) {
			filterMenu(item.children)
		} else {
			menu.value.push(item)
		}
	})
}

const inputChange = (value: any) => {
	if (value) {
		result.value = menuFilter(value)
	} else {
		result.value = []
	}
}

const menuFilter = (queryString: any) => {
	var res: any = []
	//过滤菜单树
	var filterMenuResult = menu.value.filter((item: any) => {
		if ((item.meta.title).toLowerCase().indexOf(queryString.toLowerCase()) >= 0) {
			return true
		}
		if ((item.name).toLowerCase().indexOf(queryString.toLowerCase()) >= 0) {
			return true
		}
	})
	//匹配系统路由
	var routerLocal = router.getRoutes()
	var filterRouter = filterMenuResult.map((m: any) => {
		if (m.meta.type == "link") {
			return routerLocal.find(r => r.path == '/' + m.path)
		} else {
			return routerLocal.find(r => r.path == m.path)
		}
	})
	//重组对象
	filterRouter.forEach((item: any) => {
		res.push({
			name: item.name,
			type: item.meta.type,
			path: item.meta.type == "link" ? item.path.slice(1) : item.path,
			icon: item.meta.icon,
			title: item.meta.title,
			breadcrumb: item.meta.breadcrumb.map((v: any) => v.meta.title).join(' - ')
		})
	})
	return res
}

const to = (item: any) => {
	if (!history.value.includes(input.value)) {
		history.value.push(input.value)
		Storage.setItem("SEARCH_HISTORY", history.value)
	}
	if (item.type == "link") {
		setTimeout(() => {
			let a = document.createElement("a")
			a.style = "display: none"
			a.target = "_blank"
			a.href = item.path
			document.body.appendChild(a)
			a.click()
			document.body.removeChild(a)
		}, 10);
	} else {
		router.push({ path: item.path })
	}
	emit('success', true)
}

const historyClick = (text: any) => {
	input.value = text
	inputChange(text)
}
const historyClose = (index: any) => {
	history.value.splice(index, 1);
	if (history.value.length <= 0) {
		Storage.removeItem("SEARCH_HISTORY")
	} else {
		Storage.setItem("SEARCH_HISTORY", history.value)
	}
}
</script>

<style scoped>
.sc-search {}

.sc-search-no-result {
	text-align: center;
	margin: 40px 0;
	color: #999;
}

.sc-search-history {
	margin-top: 10px;
}

.sc-search-history .el-tag {
	cursor: pointer;
}

.sc-search-result {
	margin-top: 15px;
}

.sc-search-result li {
	height: 56px;
	padding: 0 15px;
	background: var(--el-bg-color-overlay);
	border: 1px solid var(--el-border-color-light);
	list-style: none;
	border-radius: 4px;
	margin-bottom: 5px;
	font-size: 14px;
	display: flex;
	align-items: center;
	cursor: pointer;
}

.sc-search-result li i {
	font-size: 20px;
	margin-right: 15px;
}

.sc-search-result li:hover {
	background: var(--el-color-primary);
	color: #fff;
	border-color: var(--el-color-primary);
}
</style>
