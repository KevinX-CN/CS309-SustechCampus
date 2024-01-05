import Vue from 'vue'
import Router from 'vue-router'
import demo from '../components/demo.vue' //主页
import Login from "../components/Login.vue" //登陆
import Register from "../components/Register.vue";
import shop from "../components/shop.vue"; //注册
import reserve from "../components/reserve.vue";
import login from "../components/Login.vue";
import model from "../components/model.vue";
import heightfunction from "../components/heightfunction.vue";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/model',
      name: 'model',
      component: model,
      children:[
        {path:"shop", component:shop},
        {path:"reserve", component:reserve},
        {path:"demo", component:demo},
        {path:"heightfunction",component:heightfunction}
      ]
    },
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },
  ]
})
