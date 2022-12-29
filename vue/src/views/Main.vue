<template>
  <div id="wrapper">
    <Header></Header>
    <router-view :key="$route.fullPath"/>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";

export default {
  name: "Main",
  components: {Header},
  beforeCreate() {
    document.querySelector('body').removeAttribute('class')
  }
}
</script>

<style scoped>

</style>