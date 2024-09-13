import {createApp} from "vue";
import App from "./App.vue";
import ElementPlus from "element-plus";
import svgIcon from "@/components/svg-icon";
import {router} from "./router";

import 'element-plus/dist/index.css'
import '@/styles/index.scss'
import '@/icons/iconfont/iconfont'

createApp(App).use(svgIcon).use(router).use(ElementPlus).mount("#app");