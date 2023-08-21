import { nextTick } from "vue";
import store from "@/store";

export function beforeEach(to: any, from: any) {
  var adminMain = document.querySelector("#adminui-main");
  if (!adminMain) {
    return false;
  }
  store.viewTagsStore.updateViewTags({
    fullPath: from.fullPath,
    scrollTop: adminMain.scrollTop,
  });
}

export function afterEach(to: any) {
  var adminMain = document.querySelector("#adminui-main");
  if (!adminMain) {
    return false;
  }
  nextTick(() => {
    var beforeRoute = store.viewTagsStore.viewTags.filter(
      (v: any) => v.fullPath == to.fullPath
    )[0];
    if (adminMain && beforeRoute) {
      adminMain.scrollTop = beforeRoute.scrollTop || 0;
    }
  });
}
