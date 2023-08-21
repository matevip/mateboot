import type { RouteLocationNormalizedLoaded } from "vue-router";
import { getRawRoute } from "@/utils/routerHelper";
import { defineStore } from "pinia";

export interface TagsViewState {
  viewTags: RouteLocationNormalizedLoaded[];
  cachedViews: Set<string>;
}

export const viewTagsStore = defineStore("viewTagsStore", {
  state: (): TagsViewState => ({
    viewTags: [],
    cachedViews: new Set(),
  }),

  getters: {
    getVisitedViews(): RouteLocationNormalizedLoaded[] {
      return this.viewTags;
    },
    getCachedViews(): string[] {
      return Array.from(this.cachedViews);
    },
  },

  actions: {
    // 新增缓存和tag
    pushViewTags(view: RouteLocationNormalizedLoaded) {
      this.addVisitedView(view);
      this.addCachedView();
    },

    // 新增tag
    addVisitedView(view: RouteLocationNormalizedLoaded) {
      if (this.viewTags.some((v) => v.path === view.path)) return;
      if (view.meta?.noTagsView) return;
      this.viewTags.push(
        Object.assign({}, view, {
          title: view.meta?.title || "no-name",
        })
      );
    },
    // 新增缓存
    addCachedView() {
      const cacheMap: Set<string> = new Set();
      for (const v of this.viewTags) {
        const item = getRawRoute(v);
        const needCache = !item.meta?.noCache;
        if (!needCache) {
          continue;
        }
        const name = item.name as string;
        cacheMap.add(name);
      }
      if (
        Array.from(this.cachedViews).sort().toString() ===
        Array.from(cacheMap).sort().toString()
      )
        return;
      this.cachedViews = cacheMap;
    },

    removeViewTags(route: RouteLocationNormalizedLoaded) {
      this.viewTags.forEach((item: any, index) => {
        if (item.fullPath === route.fullPath) {
          this.viewTags.splice(index, 1);
        }
      });
    },

    updateViewTags(view: RouteLocationNormalizedLoaded) {
      for (let v of this.viewTags) {
        if (v.path === view.path) {
          v = Object.assign(v, view);
          break;
        }
      }
    },

    updateViewTagsTitle(title: string) {
      const nowFullPath = location.hash.substring(1);
      this.viewTags.forEach((item: any) => {
        if (item.fullPath == nowFullPath) {
          item.meta.title = title;
        }
      });
    },

    clearViewTags() {
      this.viewTags = [];
    },
  },
});