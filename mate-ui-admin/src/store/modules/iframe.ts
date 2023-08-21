import { defineStore } from "pinia";
import { RouteRecordRaw } from "vue-router";

export const iframeStore = defineStore('iframeStore', {
    state: () => ({
        iframeList:  [] as RouteRecordRaw[]
    }),
    actions: {
        setIframeList(route: any){
			this.iframeList = []
			this.iframeList.push(route)
		},
		pushIframeList(route: any){
			let target = this.iframeList.find((item: any) => item.path === route.path)
			if(!target){
				this.iframeList.push(route);
			}
		},
		removeIframeList(route: any){
			this.iframeList.forEach((item: any, index) => {
				if (item.path === route.path){
					this.iframeList.splice(index, 1)
				}
			})
		},
		refreshIframe(route: any){
			this.iframeList.forEach((item: any) => {
            if (item.path == route.path) {
              var url = route.meta.url;
              item.meta.url = "";
              setTimeout(function () {
                item.meta.url = url;
              }, 200);
            }
         });
		},
		clearIframeList(){
			this.iframeList = []
		}
    }
})