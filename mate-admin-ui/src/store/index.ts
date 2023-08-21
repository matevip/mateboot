import { globalStore } from "./modules/global";
import { iframeStore } from "./modules/iframe";
import { keepAliveStore } from "./modules/keepAlive";
import { viewTagsStore } from "./modules/viewTags";
import { userStore } from "./modules/user";

const store: any = {};

export const registerStore = () => {
  store.globalStore = globalStore();
  store.iframeStore = iframeStore();
  store.keepAliveStore = keepAliveStore();
  store.viewTagsStore = viewTagsStore();
  store.userStore = userStore();
};

export default store;
