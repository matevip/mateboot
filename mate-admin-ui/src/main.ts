import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import App from './App.vue'
import {createPinia} from 'pinia'

import router from './router'
import { registerStore } from "./store"
import mate from './mate'
import i18n from './locales'


import 'element-plus/dist/index.css'
import '@/styles/style.scss'
import 'virtual:windi.css'
import 'virtual:windi-devtools'

const app = createApp(App)

app.use(createPinia())
// 注册 Pinia
registerStore()

app.use(ElementPlus)
app.use(router)
app.use(mate)
app.use(i18n);
app.mount('#app')
