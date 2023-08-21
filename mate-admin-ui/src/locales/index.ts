import { globalStore } from "./../store/modules/global";
import { createI18n } from "vue-i18n";
// import el_zh_cn from "element-plus/lib/locale/lang/zh-cn";
// import el_en from "element-plus/lib/locale/lang/en";
import el_zh_cn from "element-plus/es/locale/lang/zh-cn";
import el_en from "element-plus/es/locale/lang/en";

import zh_cn from "./lang/zh-cn";
import en from "./lang/en";

import cache from "@/utils/cache";

export const messages: { [key: string]: any } = {
  "zh-cn": {
    el: el_zh_cn,
    ...zh_cn,
  },
  en: {
    el: el_en,
    ...en,
  },
};

export const i18n = createI18n({
  legacy: false,
  locale: cache.getLanguage(),
  fallbackLocale: "zh-cn",
  globalInjection: true,
  messages,
});

export default i18n;
