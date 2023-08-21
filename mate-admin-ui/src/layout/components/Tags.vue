<template>
    <div class="adminui-tags">
        <ul ref="tagsRef">
            <li v-for="tag in tagList" v-bind:key="tag"
                :class="[isActive(tag) ? 'active' : '', tag.meta.affix ? 'affix' : '']"
                @contextmenu.prevent="openContextMenu($event, tag)">
                <router-link :to="tag">
                    <span>{{ tag.meta.title }}</span>
                    <el-icon v-if="!tag.meta.affix"
                        @click.prevent.stop='closeSelectedTag(tag)'><el-icon-close /></el-icon>
                </router-link>
            </li>
        </ul>
    </div>

    <transition name="el-zoom-in-top">
        <ul v-if="contextMenuVisible" :style="{ left: left + 'px', top: top + 'px' }" class="contextmenu"
            id="contextmenu">
            <li @click="refreshTab()"><el-icon><el-icon-refresh /></el-icon>刷新</li>
            <hr>
            <li @click="closeTabs()" :class="contextMenuItem.meta.affix ? 'disabled' : ''">
                <el-icon><el-icon-close /></el-icon>关闭标签
            </li>
            <li @click="closeOtherTabs()"><el-icon><el-icon-folder-delete /></el-icon>关闭其他标签</li>
            <hr>
            <li @click="maximize()"><el-icon><el-icon-full-screen /></el-icon>最大化</li>
            <li @click="openWindow()"><el-icon><el-icon-copy-document /></el-icon>在新的窗口中打开</li>
        </ul>
    </transition>
</template>

<script lang="ts" setup name="tags">
import { ref, unref, watch, nextTick, onMounted } from 'vue';
import Sortable from 'sortablejs'
import store from '@/store'
import { useRoute } from 'vue-router';
import { ElMessageBox } from 'element-plus';
import router from '@/router'
import mateConfig from '@/config'

const contextMenuVisible = ref(false);
const contextMenuItem: any = ref(null)
const left = ref(0)
const top = ref(0)
const tagList = store.viewTagsStore.viewTags
const tipDisplayed = ref(false)
const route = useRoute()
const tagsRef = ref()

watch(route, async newRoute => {
    
    const pushRoute = {
        path: newRoute.path,
        fullPath: newRoute.fullPath,
        meta: newRoute.meta,
        name: newRoute.name
    }

    addViewTags(pushRoute);
    //判断标签容器是否出现滚动条
    await nextTick()
    const tags = tagsRef.value
    if (tags && tags.scrollWidth > tags.clientWidth) {
        //确保当前标签在可视范围内
        let targetTag = tags.querySelector(".active")
        targetTag.scrollIntoView()
        //显示提示
        if (!tipDisplayed.value) {
            ElMessageBox.confirm('当前标签数量过多，可通过鼠标滚轴滚动标签栏。关闭标签数量可减少系统性能消耗。', '提示', {
                type: 'warning'
            }).then(() => {
            }).catch(() => {
                //取消
            })
            tipDisplayed.value = true
        }
    }
})
watch(contextMenuVisible, (val) => {
    var cm = function (e: any) {
        let sp = document.getElementById("contextmenu");
        if (sp && !sp.contains(e.target)) {
            closeMenu()
        }
    }
    if (val) {
        document.body.addEventListener('click', e => cm(e))
    } else {
        document.body.removeEventListener('click', e => cm(e))
    }
})

onMounted(() => {
    // var menu = router.sc_getMenu()
    var menu = store.userStore.menuList!
    var dashboardRoute = treeFind(menu, (node: any) => node.path == mateConfig.DASHBOARD_URL)
    if (dashboardRoute) {
        dashboardRoute.fullPath = dashboardRoute.path
        addViewTags(dashboardRoute)
        addViewTags(route)
    }
    tagDrop();
    scrollInit()
})

//查找树
const treeFind = (tree: any, func: any) => {
    for (const data of tree) {
        if (func(data)) return data
        if (data.children) {
            const res: any = treeFind(data.children, func)
            if (res) return res
        }
    }
    return null
}
//标签拖拽排序
const tagDrop = () => {
    const target = tagsRef.value
    Sortable.create(target, {
        draggable: 'li',
        animation: 300
    })
}
//增加tag
const addViewTags = (route: any) => {
    if (route.name && !route.meta.fullpage) {
        store.viewTagsStore.pushViewTags(route)
        store.keepAliveStore.pushKeepLive(route.name)
    }
}
//高亮tag
const isActive = (routeParam: any) => {
    return routeParam.fullPath === route.fullPath
}
//关闭tag
const closeSelectedTag = (tag: any, autoPushLatestView = true) => {
    const nowTagIndex = tagList.findIndex((item: any) => item.fullPath == tag.fullPath)
    store.viewTagsStore.removeViewTags(tag)
    store.iframeStore.removeIframeList(tag)
    store.keepAliveStore.removeKeepLive(tag.name)
    if (autoPushLatestView && isActive(tag)) {
        const leftView = tagList[nowTagIndex - 1]
        if (leftView) {
            router.push(leftView)
        } else {
            router.push('/')
        }
    }
}
//tag右键
const openContextMenu = (e: any, tag: any) => {
    contextMenuItem.value = tag;
    contextMenuVisible.value = true;
    left.value = e.clientX + 1;
    top.value = e.clientY + 1;

    //FIX 右键菜单边缘化位置处理
    nextTick(() => {
        let sp: any = document.getElementById("contextmenu");
        if (document.body.offsetWidth - e.clientX < sp.offsetWidth) {
            left.value = document.body.offsetWidth - sp.offsetWidth + 1;
            top.value = e.clientY + 1;
        }
    })
}
//关闭右键菜单
const closeMenu = () => {
    contextMenuItem.value = null;
    contextMenuVisible.value = false
}
//TAB 刷新
const refreshTab = () => {
    var nowTag: any = contextMenuItem.value;
    contextMenuVisible.value = false
    //判断是否当前路由，否的话跳转
    if (route.fullPath != nowTag.fullPath) {
        router.push({
            path: nowTag.fullPath,
            query: nowTag.query
        })
    }
    store.iframeStore.refreshIframe(nowTag)
    setTimeout(function () {
        store.keepAliveStore.removeKeepLive(nowTag.name)
        store.keepAliveStore.setRouteShow(false)
        nextTick(() => {
            store.keepAliveStore.pushKeepLive(nowTag.name)
            store.keepAliveStore.setRouteShow(true)
        })
    }, 0);
}
//TAB 关闭
const closeTabs = () => {
    var nowTag: any = contextMenuItem.value;
    if (!nowTag.meta.affix) {
        closeSelectedTag(nowTag)
        contextMenuVisible.value = false
    }
}
//TAB 关闭其他
const closeOtherTabs = () => {
    var nowTag: any = contextMenuItem.value;
    //判断是否当前路由，否的话跳转
    if (route.fullPath != nowTag.fullPath) {
        router.push({
            path: nowTag.fullPath,
            query: nowTag.query
        })
    }
    var tags = [...tagList];
    tags.forEach(tag => {
        if (tag.meta && tag.meta.affix || nowTag.fullPath == tag.fullPath) {
            return true
        } else {
            closeSelectedTag(tag, false)
        }
    })
    contextMenuVisible.value = false
}
//TAB 最大化
const maximize = () => {
    var nowTag: any = contextMenuItem.value;
    contextMenuVisible.value = false
    //判断是否当前路由，否的话跳转
    if (route.fullPath != nowTag.fullPath) {
        router.push({
            path: nowTag.fullPath,
            query: nowTag.query
        })
    }
    document.getElementById('app')?.classList.add('main-maximize')
}
//新窗口打开
const openWindow = () => {
    var nowTag: any = contextMenuItem.value;
    var url = nowTag.href || '/';
    if (!nowTag.meta.affix) {
        closeSelectedTag(nowTag)
    }
    window.open(url);
    contextMenuVisible.value = false
}
//横向滚动
const scrollInit = () => {
    const scrollDiv = tagsRef.value;
    scrollDiv.addEventListener('mousewheel', handler, false) || scrollDiv.addEventListener("DOMMouseScroll", handler, false)
    function handler(event: any) {
        const detail = event.wheelDelta || event.detail;
        //火狐上滚键值-3 下滚键值3，其他内核上滚键值120 下滚键值-120
        const moveForwardStep = 1;
        const moveBackStep = -1;
        let step = 0;
        if (detail == 3 || detail < 0 && detail != -3) {
            step = moveForwardStep * 50;
        } else {
            step = moveBackStep * 50;
        }
        scrollDiv.scrollLeft += step;
    }
}

</script>

<style>
.contextmenu {
    position: fixed;
    width: 200px;
    margin: 0;
    border-radius: 0px;
    background: var(--el-bg-color-overlay);
    border: 1px solid var(--el-border-color-light);
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
    z-index: 3000;
    list-style-type: none;
    padding: 10px 0;
}

.contextmenu hr {
    margin: 5px 0;
    border: none;
    height: 1px;
    font-size: 0px;
    background-color: var(--el-border-color-light);
}

.contextmenu li {
    display: flex;
    align-items: center;
    margin: 0;
    cursor: pointer;
    line-height: 30px;
    padding: 0 17px;
    color: #606266;
}

.contextmenu li i {
    font-size: 14px;
    margin-right: 10px;
}

.contextmenu li:hover {
    background-color: #ecf5ff;
    color: #66b1ff;
}

.contextmenu li.disabled {
    cursor: not-allowed;
    color: #bbb;
    background: transparent;
}

.tags-tip {
    padding: 5px;
}

.tags-tip p {
    margin-bottom: 10px;
}

.dark .contextmenu li {
    color: var(--el-text-color-primary);
}
</style>
