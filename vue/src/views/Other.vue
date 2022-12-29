<template>
    <router-view :key="$route.fullPath"/>
</template>

<script>
export default {
  name: "Other",
  // 创建节点之前就设置背景色
  beforeCreate() {
    document.querySelector('body').setAttribute('class', 'gray-bg')
  }
}
</script>

<style scoped>

</style>