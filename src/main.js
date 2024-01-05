// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import router from "./router";
import 'element-ui/lib/theme-chalk/index.css'
//main.js
import axios from 'axios'
//把axios对象挂到Vue实例上面，使用axios的时候直接全局调用this.$axios就可以了
Vue.prototype.$axios = axios


Vue.config.productionTip = false
Vue.use(ElementUI)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
