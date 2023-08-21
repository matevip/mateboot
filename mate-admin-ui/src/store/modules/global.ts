import { defineStore } from "pinia";
import config from "@/config";
import cache from "@/utils/cache";

export const globalStore = defineStore("globalStore", {
  state: () => ({
    //移动端布局
    ismobile: false,
    //布局
    layout: cache.getLayout(),
    //菜单是否折叠 toggle
    menuIsCollapse: config.MENU_IS_COLLAPSE,
    //多标签栏
    layoutTags: config.LAYOUT_TAGS,
    //主题
    theme: config.THEME,
    //语言
    language: cache.getLanguage(),
  }),
  actions: {
    setIsmobile(ismobile: boolean) {
      this.ismobile = ismobile;
    },
    setLayout(layout: string) {
      this.layout = layout;
      cache.setLayout(layout)
    },
    setTheme(theme: string) {
      this.theme = theme;
    },
    setLanguage(locale: string) {
      this.language = locale;
      cache.setLanguage(locale);
    },
    toggleMenuIsCollapse() {
      this.menuIsCollapse = !this.menuIsCollapse;
    },
    toggleLayoutTags() {
      this.layoutTags = !this.layoutTags;
    },
  },
});
