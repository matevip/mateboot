import { defineConfig, loadEnv } from "vite";
import type { ConfigEnv, UserConfig } from "vite";
import { resolve } from "path";
import vue from "@vitejs/plugin-vue";
import Unocss from "unocss/vite";
import {
  presetAttributify,
  presetIcons,
  presetUno,
  transformerDirectives,
  transformerVariantGroup,
} from 'unocss'

// https://vitejs.dev/config/
export default defineConfig((env: ConfigEnv): UserConfig => {
  const viteEnv = loadEnv(env.mode, process.cwd());
  return {
    base: "./",
    resolve: {
      // 配置别名
      alias: {
        "@": resolve(__dirname, "src"),
        "vue-i18n": "vue-i18n/dist/vue-i18n.cjs.js",
      },
      extensions: [".mjs", ".js", ".ts", ".jsx", ".tsx", ".json", ".vue"],
    },
    plugins: [
      vue(),
      // https://github.com/antfu/unocss
      // see unocss.config.ts for config
      Unocss({
        presets: [
          presetUno(),
          presetAttributify(),
          presetIcons({
            scale: 1.2,
            warn: true,
          }),
        ],
        transformers: [transformerDirectives(), transformerVariantGroup()],
      }),
    ],
    // 配置sass
    css: {
      preprocessorOptions: {
        scss: {
          additionalData: `$injectedColor: orange;`,
        },
      },
    },
    server: {
      host: "0.0.0.0",
      port: Number(viteEnv.VITE_APP_PORT),
      open: false,
      proxy: {
        "/api": {
          target: viteEnv.VITE_APP_API_BASE_URL,
          ws: false,
          changeOrigin: true, // 允许跨域
          rewrite: (path) => path.replace("/api/", "/"),
        },
      },
    },
  };
});
