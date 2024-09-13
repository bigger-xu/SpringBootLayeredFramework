<template>
	<div class="navbar-container">
		<Logo />
		<div class="navbar-left">代码生成器工作台</div>
	</div>
</template>
<script setup lang="ts">
import Logo from './components/Logo.vue'
</script>

<style lang="scss" scoped>
.navbar-container {
	height: var(--theme-header-height);
	display: flex;
	align-items: center;
	background: var(--theme-header-bg-color);
	border-bottom: 1px solid var(--theme-border-color-light);
	color: var(--theme-header-text-color);
	::v-deep(.svg-icon) {
		align-items: center;
		cursor: pointer;
		height: var(--theme-header-height);
		line-height: var(--theme-header-height);
		padding: 0 12px;
		svg {
			color: var(--theme-header-text-color) !important;
			font-size: 16px;
		}
		&:hover {
			background: var(--theme-header-hover-color);
		}
	}
}
.navbar-left {
	flex: 1;
	height: inherit;
	display: flex;
	align-items: center;
}
</style>