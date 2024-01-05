<template>
  <div>
    <el-menu @select="handleMenuSelect" :default-active="defaultActive" mode="horizontal">
      <el-menu-item index="1">统计数据</el-menu-item>
      <el-menu-item index="2">用户信息</el-menu-item>
      <!-- 批量注册按钮 -->
    </el-menu>
    <el-container>
      <el-main>
        <!-- 统计功能 -->
        <div v-if="currentTab === '1'" class="building-description">
          <button class="export-button" @click="exportData">导出数据</button>
          <div class="echart" id="mychart" :style="myChartStyle"></div>
        </div>

        <!--用户信息 -->
        <div v-if="currentTab === '2'" class="building-description">
          <!-- 评论弹窗 -->
          <el-table
            :data="userData"
            border
            style="width: 100%">
            <el-table-column
              fixed
              prop="name"
              label="名字"
              width="150">
            </el-table-column>
            <el-table-column
              prop="password"
              label="密码"
              width="200">
            </el-table-column>
            <el-table-column
              prop="status"
              label="状态"
              width="150">
            </el-table-column>
            <el-table-column
              prop="type"
              label="身份"
              width="150">
            </el-table-column>
            <el-table-column
              prop="lastDate"
              label="最近登录时间"
              width="300">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="deleteClick(scope.row)" type="text" size="small">删除</el-button>
                <el-button @click="blacked(scope.row)" type="text" size="small" style="margin-left: 0">修改</el-button>
              </template>
            </el-table-column>
          </el-table>
          <button class="export-button" @click="handleBatchRegister">批量注册</button>
        </div>

      </el-main>
    </el-container>

  </div>
</template>

<script>
import * as echarts from "echarts";

export default {
  data() {
    return {
      currentTab: "1", // 默认选中的选项卡
      defaultActive: "1", // 默认高亮的选项卡
      userData: [{
        lastDate: '2016-05-02',
        name: '王小虎',
        password: '上海',
        status: 'black',
        type:'use'
      },
        {
          lastDate: '2016-05-02',
          name: '王da虎',
          password: 'xia海',
          status: 'white',
          type:'use'
        },
    ],
        data: [
        { name: "Mon", value: 23 },
        { name: "Tue", value: 24 },
        { name: "Wed", value: 18 },
        { name: "Thu", value: 25 },
        { name: "Fri", value: 27 },
        { name: "Sat", value: 28 },
        { name: "Sun", value: 25 }
      ],
      myChartStyle: { float: "left", width: "100%", height: "400px" }
    };
  },

  created() {
    this.$axios.get('http://localhost:8082/exer/finduser')
      .then(res => {
        this.userData=res.data
        console.log("123")
      })
      .catch(err => {
        console.log(err)
      })
  },
  methods: {
    async handleBatchRegister(){
      //to do
      const response = await this.$axios.get(`http://localhost:8082/exer/zhuceuser`);
      console.log(response.data)
      this.$message.success("成功注册10个账户")
    },
    handleMenuSelect(index) {
      this.currentTab = index;
      if (index==="1"){
        this.initEcharts();
      }
    },
    async blacked(index){
      console.log(index)
      const response = await this.$axios.get(`http://localhost:8082/exer/changeuser/${index.name}`);
      if (index.status==='black'){
        index.status='white'
      }else {
        index.status='black'
      }
      this.$notify({
        title: '成功',
        message: '修改状态成功',
        type: 'success'
      });
    },
    deleteClick(index){
      this.userData.splice(index,1)
      this.$notify({
        title: '成功',
        message: '删除成功',
        type: 'success'
      });
    },
    initEcharts() {
      const option = {
        xAxis: {
          type: "category",
          data: this.data.map(item => item.name)
        },
        yAxis: {},
        tooltip: {
          trigger: "axis",
          formatter: (params) => {
            const {name, value} = params[0].data;
            return `${name}: ${value}`;
          }
        },
        series: [
          {
            type: "bar",
            data: this.data.map(item => item.value),
            itemStyle: {
              color: "#2bdea6"
            }
          }
        ]
      };
      const myChart = echarts.init(document.getElementById("mychart"));
      myChart.setOption(option);
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    exportData() {
      const csvContent = "data:text/csv;charset=utf-8," + this.generateCSV();
      const encodedUri = encodeURI(csvContent);
      const link = document.createElement("a");
      link.setAttribute("href", encodedUri);
      link.setAttribute("download", "data.csv");
      document.body.appendChild(link);
      link.click();
    },
    generateCSV() {
      let csv = "Name,Value\n";
      this.data.forEach(item => {
        csv += `${item.name},${item.value}\n`;
      });
      return csv;
    }
  }
};
</script>
<style>
.echart {
  position: relative;
  width: 100%;
  height: 400px;
}

.export-button {
  position: relative;
  right: 10px;
  padding: 8px 16px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

.export-button:hover {
  background-color: #45a049;
}

/* 图表样式 */
#mychart {
  border: 1px solid #ddd;
  border-radius: 4px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

/* X 轴样式 */
.echart .x-axis {
  font-weight: bold;
}

/* Y 轴样式 */
.echart .y-axis {
  font-weight: bold;
}

/* 柱形图样式 */
.echart .bar-item {
  color: #888;
}
</style>
