import { defineStore } from "pinia";

export const keepAliveStore = defineStore("keepAliveStore", {
  state: () => ({
    keepLiveRoute: [] as any[],
    routeKey: null,
    routeShow: true,
  }),
  actions: {
    pushKeepLive(component: any) {
      if (!this.keepLiveRoute.includes(component)) {
        this.keepLiveRoute.push(component);
      }
    },
    removeKeepLive(component: any) {
      var index = this.keepLiveRoute.indexOf(component);
      if (index !== -1) {
        this.keepLiveRoute.splice(index, 1);
      }
    },
    clearKeepLive() {
      this.keepLiveRoute = [];
    },
    setRouteKey(key: any) {
      this.routeKey = key;
    },
    setRouteShow(key: any) {
      this.routeShow = key;
    },
  },
});