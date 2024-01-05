<template>
  <div>
    <reserve/>
<!--    <login/>-->
<!--    <shop/>-->
    <router-view/>
<!--<model/>-->
<!--    <demo/>-->
      </div>
</template>

<script>
import FlightTable from './components/FlightTable.vue'
import AddRoom from "./components/AddRoom.vue";
import demo from "./components/demo.vue";
import reserve from "./components/reserve.vue";
import login from "./components/Login.vue";
import shop from "./components/shop.vue";
import model from "./components/model.vue";

export default {
  name: 'App',
  components: {
    // reserve
    // demo
    // login
    // shop
    // model
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

#building{
  background:url('../static/web.jpeg');
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}

</style>
