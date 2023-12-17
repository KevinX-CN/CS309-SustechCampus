<template>
  <div id ="app" >
    <template v-if="list.length">
      <table border="1px" style="width: 400px;">
        <thead>
        <tr>
          <th>编号</th>
          <th>商品名称</th>
          <th>商品单价</th>
          <th>购买数量</th>
          <th>操作</th>
        </tr>
        </thead>
        <tr v-for="(product,index) in list">
          <td>{{index+1}}</td>
          <td>{{product.name}}</td>
          <td>{{product.price}} </td>
          <td>
            <button @click="subProduct(index)" :disabled="product.count==1">-</button>
            {{product.count}}
            <button @click="addProduct(index)">+</button>
          </td>
          <td><button @click="removePriduct(index)">移除</button></td>
        </tr>
        <tbody>
        </tbody>
      </table>
      <div>总金额：￥{{totalPrice}}</div>
      <button @click="back">支付</button>
    </template>
    <template v-else>购物车为空 </template>
  </div>

</template>


<script>
//创建Vue实例,得到 ViewModel
import homepage from "./Homepage.vue";

export default {
  data:function (){
    return{
      list: [
        {
          id:1,
          name:'南科大衬衫',
          price:72,
          count:2
        },
        {
          id:2,
          name:'卡套',
          price:61,
          count:1,
        },
        {
          id:3,
          name:'一丹积木',
          price:399,
          count:3
        }]
    }
  },
  /*自动加载函数*/
    /*执行触发函数*/
    methods: {
      //数量减少
      subProduct:function(index){
        this.list[index].count--;
      },
      //数量增加
      addProduct:function(index){
        this.list[index].count++;
      },
      //清除订单
      removePriduct:function(index){
        this.list.splice(index,1);
      },
      back:function () {
        this.$router.push("Homepage")
      }
    },
    /*动态计算属性*/
    computed: {
      //返回总金额
      totalPrice:function(){
        var total=0;
        for (var i=0;i < this.list.length;i++){
          total+=this.list[i].price*this.list[i].count;
        }
        return total;
      }
    },
  }
</script>




<style>

</style>
