<template>
  <div id="app">
    <template v-if="list.length">
      <el-table :data="list" border style="width: 100%;">
        <el-table-column type="index" label="编号"></el-table-column>
        <el-table-column prop="name" label="商品名称"></el-table-column>
        <el-table-column label="商品图片">
          <template slot-scope="scope">
            <img :src="scope.row.image" alt="商品图片" style="width: 100%; height: 100%;">
          </template>
        </el-table-column>
        <el-table-column prop="price" label="商品单价"></el-table-column>
        <el-table-column label="购买数量">
          <template slot-scope="scope">
            <el-button @click="subProduct(scope.$index)" :disabled="scope.row.count === 0" size="mini">-</el-button>
            <div>{{scope.row.count }}</div>
            <el-button @click="addProduct(scope.$index)" size="mini">+</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button @click="removeProduct(scope.$index)" size="mini" type="danger">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="total-price">总金额：￥{{ totalPrice }}</div>
      <el-button @click="showPaymentDialog" type="primary">支付</el-button>
    </template>
    <template v-else>
      <div class="empty-cart">购物车为空</div>
    </template>
    <el-button @click="showAddProductDialog" class="addgoods" type="primary">添加商品</el-button>

    <el-dialog :visible.sync="addProductDialogVisible" title="添加商品" width="30%">
      <el-form label-width="80px">
        <el-form-item label="商品编号">
          <el-input v-model="newProduct.id"></el-input>
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input v-model="newProduct.name"></el-input>
        </el-form-item>
        <el-form-item label="商品单价">
          <el-input-number v-model="newProduct.price" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="购买数量">
          <el-input-number v-model="newProduct.count" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="商品图片">
          <el-input v-model="newProduct.image"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer">
        <el-button @click="addProductDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="addProduct1">确认添加</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="paymentDialogVisible" title="支付" width="30%">
      <p>确认支付总金额：<strong>{{ totalPrice }}</strong>元</p>
      <p>请选择支付方式：</p>
      <el-radio-group v-model="paymentMethod">
        <el-radio label="wechat">微信支付</el-radio>
        <el-radio label="alipay">支付宝支付</el-radio>
      </el-radio-group>
      <div slot="footer">
        <el-button @click="paymentDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="pay">确认支付</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      list: [
        {
          id: 1,
          name: '南科大衬衫',
          price: 72,
          count: 2,
          image: '../static/爱心.jpg'
        },
        {
          id: 2,
          name: '卡套',
          price: 61,
          count: 1,
          image: './img/123.png'
        },
        {
          id: 3,
          name: '一丹积木',
          price: 399,
          count: 3,
          image: './img/123.png'
        }
      ],
      paymentDialogVisible: false,
      addProductDialogVisible: false,
      newProduct: {
        id: '',
        name: '',
        price: 0,
        count: 0,
        image: ''
      },
      paymentMethod: ''
    };
  },

  created() {
    this.$axios.get('http://localhost:8082/exer/shop')
      .then(res => {
        this.list=res.data
        console.log("123")
      })
      .catch(err => {
        console.log(err)
      })
  },
  methods: {
    addProduct1() {
      this.list.push({
        id: this.newProduct.id,
        name: this.newProduct.name,
        price: this.newProduct.price,
        count: this.newProduct.count,
        image: this.newProduct.image
      });

      this.addProductDialogVisible = false;

      // 重置newProduct对象
      this.newProduct = {
        id: '',
        name: '',
        price: 0,
        count: 0,
        image: ''
      };
    },
    showAddProductDialog() {
      this.addProductDialogVisible = true;
    },
    subProduct(index) {
      this.list[index].count--;
    },
    addProduct(index) {
      this.list[index].count++;
    },
    removeProduct(index) {
      this.list.splice(index, 1);
    },
    showPaymentDialog() {
      this.paymentDialogVisible = true;
    },
    pay() {
      // 执行支付逻辑
      // 可根据this.paymentMethod的值进行不同的支付方式处理
      // 支付成功后可以进行相应的操作，如清空购物车等
      this.paymentDialogVisible = false;
      // 清空购物车
      this.list = [];
      // 弹出支付成功的提示框
      this.$message.success('支付成功！');
    }
  },
  computed: {
    totalPrice() {
      let total = 0;
      for (let i = 0; i < this.list.length; i++) {
        total += this.list[i].price * this.list[i].count;
      }
      return total;
    }
  }
};
</script>

<style scoped>
#app {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
}

.addgoods{

}

.total-price {
  margin-top: 10px;
  font-size: 18px;
  font-weight: bold;
}

.empty-cart {
  margin-top: 20px;
  font-size: 20px;
  color: #999;
}

.el-button {
  margin-top: 20px;
}
</style>
