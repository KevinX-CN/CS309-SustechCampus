<template>
  <div id="app">
    <el-header style="background-color: #8a7d7d;">
      <div class="header-content">
        <h1 class="title">Sustech Server</h1>
        <!-- 右上角账号信息 -->
        <div class="placeholder"></div>
        <el-dropdown class="drop" @command="handleAccountClick">
        <span class="el-dropdown-link">
             <img class="avatar" :src="accountInfo.avatar" alt="头像"><i class="el-icon-arrow-down el-icon--right"></i>
            </span>
          <el-dropdown-menu slot="dropdown" style="width: 100px">
            <el-dropdown-item>
              <div>
                <img class="avatar" :src="accountInfo.avatar" alt="头像">
                <div>
                  <p>{{ accountInfo.name }}</p>
                  <p>{{ accountInfo.status }}</p>
                  <!-- 添加其他个人信息字段 -->
                </div>
              </div>
            </el-dropdown-item>
            <el-dropdown-item command="settings" v-if="accountInfo.name!=='游客'">设置</el-dropdown-item>
            <el-dropdown-item command="logout">登出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </div>
    </el-header>
    <el-container style="height: 100vh;">
      <el-aside width="200px" style="background-color: #9d7f7f;">
        <!-- 左侧导航栏 -->
        <el-menu default-active="1" class="el-menu-vertical-demo" @select="handleMenuSelect">
          <el-menu-item index="1">详细信息</el-menu-item>
          <el-menu-item index="2">购物</el-menu-item>
          <el-menu-item index="3">预定</el-menu-item>
          <el-menu-item v-if="accountInfo.status==='admin'" index="4">高级功能</el-menu-item>
        </el-menu>
      </el-aside>
      <el-dialog
        title="评论"
        :visible.sync="setpassword"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :append-to-body="true">
        <el-form ref="commentForm" label-width="80px">
          <el-form-item label="新密码：" required>
            <el-input type="textarea" v-model="oldpsd" ></el-input>
          </el-form-item>
          <el-form-item label="再次输入：" required>
            <el-input type="textarea" v-model="newpsd"></el-input>
          </el-form-item>
        </el-form>
        <el-button @click="submit1">提交</el-button>
      </el-dialog>

      <el-main style="background: rgba(225,217,217,0.25)">

        <!-- 主要内容区域 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import shop from "./shop.vue";
import login from "./Login.vue";
import reserve from "./reserve.vue";
import demo from "./demo.vue";

export default {
  data() {
    return {
      accountInfo: {
        name: '游客',
        avatar: '../static/images.jpg',
        type:'',
        password:'',
        status:''
        // 其他账号信息
      },
      setpassword:false,
      newpsd:'',
      oldpsd:''
    };
  },
  created() {
    this.accountInfo.name=this.$route.query.username
    this.accountInfo.status=this.$route.query.type
    this.accountInfo.password=this.$route.query.password
    this.accountInfo.type=this.$route.query.status
    console.log(this.accountInfo.status)
    console.log(this.accountInfo.status==='admin')
  },
  methods: {
    submit1(){
      if (this.newpsd===this.oldpsd){
        this.accountInfo.password=this.newpsd
        this.setpassword=false
        this.$message.success('成功了');
      }else {
        this.$message.error('两者应该一致');
      }
    },
    handleMenuSelect(index) {
      if (index==="1"){
        this.$router.push({path:"/model/demo",query:{
          status:this.accountInfo.status,
            username:this.accountInfo.name,
            type:this.accountInfo.type
          }});
      }else if (index==="2"){
        this.$router.push({path:"/model/shop",query:{
            status:this.accountInfo.status,
            username:this.accountInfo.name
          }});
      }else if (index==="3"){
        this.$router.push({path:"/model/reserve",query:{
            status:this.accountInfo.status,
            username:this.accountInfo.name
          }});
      }else {
        this.$router.push({path:"/model/heightfunction"})
      }
      console.log(this.accountInfo.status)
      // 处理导航栏菜单项点击事件
      console.log('选中菜单项：', index);
    },
    handleAccountClick(command) {
      // 处理账号信息点击事件
      console.log('点击账号信息：', command);
      if (command === 'settings') {
        // 生成设置
        this.setpassword=true
      } else if (command === 'logout') {
        // 执行登出操作
        this.$router.push('/')
      }
    }
  }
};
</script>

<style scoped>
.el-menu-vertical-demo{
  background: rgba(0, 0, 0, 0);
}
.header-content {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0 20px;
}


.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  margin-right: 5px;
}

.drop{
  text-align: right;
}

.title {
  font-size: 24px;
  text-align: center;
}

.placeholder {
  flex-grow: 1; /* 占据左侧剩余空间 */
}

</style>
