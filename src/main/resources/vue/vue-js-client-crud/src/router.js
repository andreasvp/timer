import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/metimers",
      name: "metimers",
      component: () => import("./components/MetimersList")
    },
    {
      path: "/cards",
      name: "metimers-cards",
      component: () => import("./components/CardMetimer")
    },
    {
      path: "/metimers/:id",
      name: "metimer-details",
      component: () => import("./components/Metimer")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddMetimer")
    }
  ]
});
