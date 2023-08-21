// import drag from './directives/drag'

import { App } from "vue";
import * as elIcons from "@element-plus/icons-vue";
import * as scIcons from "@/assets/icons";

export default {
  install(app: App) {
    //统一注册el-icon图标
    for (let icon in elIcons) {
      app.component(`ElIcon${icon}`, elIcons[icon]);
    }
    //统一注册sc-icon图标
    for (let icon in scIcons) {
      app.component(`ScIcon${icon}`, scIcons[icon]);
    }
  },
};
