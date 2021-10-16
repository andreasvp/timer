import Vue from 'vue'
import App from './App.vue'
import router from './router'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap an BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Filter for datetime - 
import VueFilterDateFormat from '@vuejs-community/vue-filter-date-format';
Vue.use(VueFilterDateFormat);

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)