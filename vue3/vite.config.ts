import {ConfigEnv, defineConfig, loadEnv, UserConfig} from "vite";
import vue from "@vitejs/plugin-vue";
import path, {resolve} from "path";
import Icons from "unplugin-icons/vite";
import IconsResolver from "unplugin-icons/resolver";
import Components from "unplugin-vue-components/vite";
import {ElementPlusResolver} from "unplugin-vue-components/resolvers";
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'


// https://vitejs.dev/config/
export default defineConfig(({mode}: ConfigEnv): UserConfig => {
  const env = loadEnv(mode, process.cwd());
  return {
    resolve: {
      alias: {
        "@": path.resolve(__dirname, "src")
      }
    },
    server: {
      // 允许IP访问
      host: "0.0.0.0",
      // 应用端口 (默认:3000)
      port: Number(env.VITE_APP_PORT),
      // 运行是否自动打开浏览器
      open: false,
      proxy: {
        "/hrs": {
          target: env.VITE_APP_API_URL,
          changeOrigin: true,
        },
        "/base": {
          target: env.VITE_APP_API_URL,
          changeOrigin: true,
        },
      },
    },
    plugins: [
      vue(),
      createSvgIconsPlugin({
        iconDirs: [resolve(__dirname, 'src/icons/svg')],
        symbolId: 'icon-[dir]-[name]'
      }),
      Components({
        resolvers: [
          // 自动导入 Element Plus 组件
          ElementPlusResolver(),
          // 自动注册图标组件
          IconsResolver({
            // element-plus图标库，其他图标库 https://icon-sets.iconify.design/
            enabledCollections: ["ep"],
          }),
        ],
      }),
      Icons({
        // 自动安装图标库
        autoInstall: true,
      }),
    ],
  };
});