<template>
  <!--  <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </nav>-->
  <Other v-if="$route.meta.hidden"></Other>
  <Main v-else></Main>

</template>

<script>
import Main from "@/views/Main.vue";
import Other from "@/views/Other.vue";
import toa from "toastr/toastr"
import "metismenu/dist/metisMenu"

export default {
  components: {Other, Main},
  mounted() {
    $("#side-menu").metisMenu();

    toa.options = {
      "closeButton": true,
      "debug": false,
      "progressBar": true,
      "preventDuplicates": false,
      "positionClass": "toast-bottom-right",
      "onclick": null,
      "showDuration": "600",
      "hideDuration": "1000",
      "timeOut": "2000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    }
  }
}
</script>

<style>
@import "toastr";
</style>
