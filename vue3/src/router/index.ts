import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

export const menuRoutes: RouteRecordRaw[] = [
	{
		path: '/p/gen',
		meta: {
			title: '代码生成器',
			icon: 'icon-appstore'
		},
		children: [
			{
				path: '/gen/datasource',
				name: 'DataSource',
				component: () => import('../components/HelloWorld.vue'),
				meta: {
					title: '数据源管理',
					icon: 'icon-database-fill'
				}
			},
		]
	},
	{
		path: '/p/gen1',
		meta: {
			title: '代码生成器',
			icon: 'icon-database'
		},
		children: [
			{
				path: '/gen/datasource',
				name: 'DataSource',
				component: () => import('../components/HelloWorld.vue'),
				meta: {
					title: '数据源管理',
					icon: 'icon-database-fill'
				}
			},
		]
	}
]

export const constantRoutes: RouteRecordRaw[] = [
	{
		path: '/redirect',
		component: () => import('../layout/index.vue'),
		children: [
			{
				path: '/redirect/:path(.*)',
				component: () => import('../layout/components/Router/Redirect.vue')
			}
		]
	},
	{
		path: '/',
		component: () => import('../layout/index.vue'),
		redirect: '/gen/generator',
		children: [...menuRoutes]
	},
	{
		path: '/404',
		component: () => import('../views/404.vue')
	},
	{
		path: '/:pathMatch(.*)',
		redirect: '/404'
	}
]

export const router = createRouter({
	history: createWebHashHistory(),
	routes: constantRoutes
})