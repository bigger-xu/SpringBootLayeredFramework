import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import jQuery from "jquery";


import "bootstrap";
import "bootstrap/dist/js/bootstrap.js";
import "popper.js"
import "bootstrap/dist/css/bootstrap.min.css";
import "@/inspinia/css/style.css";
import "@/inspinia/css/animate.css";
import "@/inspinia/font-awesome/css/font-awesome.css";


const app = createApp(App);
app.use(jQuery)
app.use(store);
app.use(router);
app.mount("#app");

