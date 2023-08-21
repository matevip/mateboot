import { lighten, darken } from "@/utils/color";
import mateConfig from "@/config";
import cache from '@/utils/cache';
// 处理主题样式
export const handleThemeStyle = () => {
  //设置主题颜色
  const app_color = mateConfig.COLOR || cache.getColor();
  if (app_color) {
    document.documentElement.style.setProperty("--el-color-primary", app_color);
    for (let i = 1; i <= 9; i++) {
      document.documentElement.style.setProperty(
        `--el-color-primary-light-${i}`,
        lighten(app_color, i / 10)
      );
    }
    for (let i = 1; i <= 9; i++) {
      document.documentElement.style.setProperty(
        `--el-color-primary-dark-${i}`,
        darken(app_color, i / 10)
      );
    }
  }
};

// hex颜色转rgb颜色
const hexToRgb = (str: any) => {
  str = str.replace("#", "");
  let hexs = str.match(/../g);
  for (let i = 0; i < 3; i++) {
    hexs[i] = parseInt(hexs[i], 16);
  }
  return hexs;
};

// rgb颜色转Hex颜色
const rgbToHex = (r: any, g: any, b: any) => {
  let hexs = [r.toString(16), g.toString(16), b.toString(16)];
  for (let i = 0; i < 3; i++) {
    if (hexs[i].length == 1) {
      hexs[i] = `0${hexs[i]}`;
    }
  }
  return `#${hexs.join("")}`;
};
