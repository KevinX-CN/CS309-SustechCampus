<template>
  <div>
    <el-menu @select="handleMenuSelect" :default-active="defaultActive" mode="horizontal">
      <el-menu-item index="1">建筑介绍</el-menu-item>
      <el-menu-item index="2">评论</el-menu-item>
      <el-menu-item index="3">公交查询</el-menu-item>
    </el-menu>

    <el-container>
      <el-main>
        <!-- 建筑介绍 -->
        <div v-if="currentTab === '1'" class="building-description">
          <div v-if="!isEditMode">
            <div class="content-container">
              <img class="building-image1" src="../../static/工学院.jpg" alt="Building Image">
            </div>
            <div class="custom-container"> {{ buildingContent }}</div>
            <button v-if="status==='admin'" class="edit-button" @click="toggleEditMode">修改</button>
          </div>
          <div v-else>
            <el-input
              type="textarea"
              v-model="buildingContent"
              :rows="4"
              placeholder="请输入建筑介绍"
            ></el-input>
            <button class="save-button" @click="saveBuildingContent">保存</button>
          </div>
        </div>

        <!-- 评论 -->
        <div v-if="currentTab === '2'" class="building-description">
          <!-- 评论弹窗 -->
          <div>
            <ul>
              <li v-for="review in reviews" :key="review.id" class="review-item">
                <p>{{ review.username }}</p>
                <img v-if="review.image" :src="review.image" class="building-image" alt="用户上传的图片">
                <p>{{ review.comment }}</p>

                <hr>
              </li>
            </ul>
            <el-button v-if="type==='white'" class="add-review-button" type="primary" @click="showModal">添加评价</el-button>
            <el-button v-if="status==='admin'" type="primary" @click="openDialog">审批评论</el-button>
          </div>
          <el-dialog
            title="审核评论"
            :visible.sync="dialogVisible"
            width="70%"
            @close="resetDialog"
          >
            <el-table :data="comments" style="width: 100%">
              <el-table-column label="评论用户" prop="username"></el-table-column>
              <el-table-column label="评论内容" prop="comment"></el-table-column>
              <el-table-column label="评论图片" prop="image">
                <template slot-scope="scope">
                  <!-- 使用 img 标签显示评论头像 -->
                  <img :src="scope.row.image" alt="评论头像" style="width: 40px; height: 40px; border-radius: 50%;">
                </template>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button type="success" @click="approveComment(scope.row)">同意</el-button>
                  <el-button type="danger" @click="rejectComment(scope.row)">不同意</el-button>
                </template>
              </el-table-column>
            </el-table>


            <span slot="footer" class="dialog-footer">
        <el-button @click="resetDialog">取 消</el-button>
      </span>
          </el-dialog>
          <el-dialog
            title="评论"
            :visible.sync="showCommentModal"
            :close-on-click-modal="false"
            :close-on-press-escape="false"
            :append-to-body="true"
            @open="handleDialogOpen"
          >
            <el-form ref="commentForm" :model="userReview" label-width="80px">
              <el-form-item label="评论：" required>
                <el-input type="textarea" v-model="userReview.comment" :rows="4"></el-input>
              </el-form-item>
              <el-form-item label="选择图片：">
                <input ref="imageUploadInput" type="file" @change="handleImageSelect" accept="image/*">
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
    <el-button @click="closeModal">取消</el-button>
    <el-button type="primary" @click="submitReview">提交评价</el-button>
  </span>
          </el-dialog>
        </div>

        <!-- 公交查询 -->
        <div v-if="currentTab === '3'" class="building-description">
          <div class="input-row">
            <span class="input-label">公交路线操作</span>
            <el-input v-model="input1" placeholder="起始点" class="inputbutton"></el-input>
            <el-input v-model="input2" placeholder="终点" class="inputbutton"></el-input>
          </div>
          <el-button class="query-button" type="primary" @click="showPopup">查询</el-button>
          <el-button class="edit-button" type="primary" @click="changeBus">修改</el-button>
          <el-dialog
            title="弹窗信息"
            :visible.sync="showPopupDialog"
            :close-on-click-modal="false"
            :close-on-press-escape="false"
            :append-to-body="true"
          >
            <p>名称: {{ name }}</p>
            <p>值1: {{ input1 }}</p>
            <p>值2: {{ input2 }}</p>
            <p>其他弹窗内容...</p>
            <span slot="footer" class="dialog-footer">
              <el-button @click="closePopup">关闭</el-button>
            </span>
          </el-dialog>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>

export default {
  data() {
    return {
      dialogVisible: false,
      currentComment: null,
      comments: [
        { id: 1, username: "用户A", comment: "很棒的建筑！", image:"../assets/images.jpg" },
        { id: 2, username: "用户B", comment: "非常漂亮！", image: "https://th.bing.com/th/id/OIP.duz6S7Fvygrqd6Yj_DcXAQHaF7?rs=1&pid=ImgDetMain" },
        { id: 3, username: "用户C", comment: "有点小问题，但总体还不错。", image: null },
        // 添加更多评论...
      ],
      status:'admin',
      username: 'musk',
      type:'white',
      chartOptions: {
        xAxis: {
          data: ['2018年', '2019年', '2020年', '2021年', '2022年']
        },
        yAxis: { name: '指标单位'},
        series: [
          {
            name: '项目名称1',
            data: [40, 25, 35, 20, 28]
          },
          {
            name: '项目名称2',
            data: [9, 3, 15, 20, 7]
          },
          {
            name: '项目名称3',
            data: [-15, -20, -10, -8, -6]
          }
        ]
      },
      currentTab: "1", // 默认选中的选项卡
      defaultActive: "1", // 默认高亮的选项卡
      buildingContent: "为响应国家加快和深化工程教育改革的号召，同时发挥南方科技大学在高等教育改革中的先导和示范作用，南方科技大学工学院在2015年5月成立。\n" +
        "工学院设有电子与电气工程系、材料科学与工程系、 环境科学与工程学院、 海洋科学与工程系、力学与航空航天工程系、 机械与能源工程系、计算机科学与工程系、生物医学工程系、深港微电子学院（国家示范性微电子学院）、系统设计与智能制造学院等10个系（院）。同时下设斯发基斯可信自主研究院、复杂流体及软物质研究中心、创新材料研究院、深圳可持续发展研究院、创新智造研究院、碳中和能源研究院、纳米科学与应用研究院、精密光学工程中心8个研究机构。\n" +
        "工学院现拥有1个博士后科研流动站，2个一级学科博士学位授权点，4个一级学科硕士学位授权点，5个硕士专业学位授权类别，开设21个本科专业。同时拥有25个省部级以上科研平台，46个市级科研平台。\n" +
        "工学院面向海内外选聘人才，已基本建成一支高水平的师资队伍。工学院现有教师310人，其中，全职院士22人，教育部特聘教授（含青年）17人，国家自然科学基金杰出青年18人，国家自然科学基金优秀青年11人。\n" +
        "工学院积极开展国际交流与合作，已与麻省理工学院、加拿大英属哥伦比亚大学、澳大利亚莫纳什大学、日本东京大学生产技术研究所等美洲、泛太平洋地区、欧洲的高校和研究机构建立了密切联系。\n" +
        "展望未来，工学院将国家发展和产业需求与人才培养相结合，知识传授与能力培养一体化，不断探索新工科人才培养模式，注重培养具有国际视野、厚基础、强实践能力的复合型创新领军人才，努力建成国际一流工学院，助力粤港澳大湾区经济可持续发展。",
      isEditMode: false,
      reviews: [
        { id: 1, username: "用户A", comment: "很棒的建筑！", image:"C:/Users/86133/Pictures/me.webp" },
        { id: 2, username: "用户B", comment: "非常漂亮！", image: "https://th.bing.com/th/id/OIP.duz6S7Fvygrqd6Yj_DcXAQHaF7?rs=1&pid=ImgDetMain" },
        { id: 3, username: "用户C", comment: "有点小问题，但总体还不错。", image: null },
      ],
      userReview: {
        username: "",
        comment: "",
        image:'',
      },
      params:{},
      showCommentModal: false, // 控制评论弹窗显示/隐藏
      input1: '',
      input2:'',
      showPopupDialog: false, // 控制公交查询弹窗显示/隐藏
      name: '公交查询',
    };
  },
  async created() {
    this.status=this.$route.query.status
    this.username=this.$route.query.username
    this.type=this.$route.query.type
    // const  res = await this.$axios.get('http://localhost:8082/exer/findbuilding/工学院')
    // this.buildingContent=res.data.introduction
    this.$axios.get('http://localhost:8082/exer/commentt')
      .then(res => {
        this.reviews=res.data
        console.log("123")
      })
      .catch(err => {
        console.log(err)
      })
  },
  methods: {
    handleDialogOpen() {
      // 初始化文件选择框
      const fileInput = this.$refs.imageUploadInput;
      if (fileInput) {
        fileInput.value = ""; // 清空选择的文件
      }
    },
    toggleEditMode() {
      this.isEditMode = !this.isEditMode;
    },
    saveBuildingContent() {
      // 保存建筑介绍的编辑内容
      this.$message.success("成功修改内容")
      this.toggleEditMode();
    },
    showModal() {
      // 显示评论弹窗
      this.showCommentModal = true;
    },
    closeModal() {
      // 关闭评论弹窗
      this.showCommentModal = false;
      this.resetReviewForm();
    },
    handleImageSelect(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = () => {
          this.userReview.image = reader.result;
        };
        reader.readAsDataURL(file);
      }
    },
    async submitReview() {
      // 提交评论
      this.params={ username: this.username, comment: this.userReview.comment,image:this.userReview.image }
      const registerResponse = await this.$axios.post('http://localhost:8082/exer/newcomment',this.params);
      console.log(registerResponse.data);
      alert('添加成功，待管理员审核通过即可发表');
      this.closeModal();
    },
    showPopup() {
      // 显示公交查询弹窗
      this.showPopupDialog = true;
    },
    closePopup() {
      // 关闭公交查询弹窗
      // 重置输入框的值
      this.showPopupDialog = false;
      this.input1 = '';
      this.input2 = '';
    },
    changeBus() {
      // 修改公交查询
      // 在这里实现修改公交查询的逻辑
    },
    handleMenuSelect(index) {
      this.currentTab = index;
    },
    resetReviewForm() {
      // 重置评论表单
      this.userReview.username = '';
      this.userReview.comment = '';
      this.userReview.image = null;
    },
    async openDialog() {
      const response = await this.$axios.get(`http://localhost:8082/exer/commentf`);
      this.comments=response.data;
      this.dialogVisible = true;

    },
    resetDialog() {
      this.dialogVisible = false;
      this.currentComment = null;
    },
    async approveComment(comment) {
      // 在这里添加同意评论的逻辑，使用 comment 进行相关操作
      console.log("同意评论:", comment);
      const registerResponse = await this.$axios.post('http://localhost:8082/exer/changecomment',null,{params:{ username: comment.username, comment:comment.comment }});
      // 你可以更新评论状态等...
      console.log(registerResponse.data)
      // 移除当前评论
      const index = this.comments.findIndex(c => c.id === comment.id);
      if (index !== -1) {
        this.comments.splice(index, 1);
      }
      this.$message.success("成功添加评论，审核通过")
      this.$axios.get('http://localhost:8082/exer/commentt')
        .then(res => {
          this.reviews=res.data
          console.log("123")
        })
        .catch(err => {
          console.log(err)
        })
    },
    rejectComment(comment) {
      // 在这里添加不同意评论的逻辑，使用 comment 进行相关操作
      console.log("不同意评论:", comment);

      // 你可以更新评论状态等...

      // 移除当前评论
      const index = this.comments.findIndex(c => c.id === comment.id);
      if (index !== -1) {
        this.comments.splice(index, 1);
      }
    }
  },
};
</script>

<style>
#building {

}

h2 {
  color: #333;
  font-size: 24px;
  margin: 20px 0;
}

h2:first-of-type {
  margin-top: 0;
}

button,
.el-button {
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  color: #fff;
  background-color: #409eff;
  cursor: pointer;
}

button:hover,
.el-button:hover {
  background-color: #66b1ff;
}

button:active,
.el-button:active {
  background-color: #3a8ee6;
}

.el-input {
  width: 200px;
}

.building-description {
  margin-bottom: 20px;

}

.edit-button {
  margin-left: 10px;

}

.save-button {
  margin-top: 10px;
}

.inputbutton{
  margin-right: 20px;
  margin-top: 10px;
}
.add-review-button {
  margin-bottom: 10px;
}

.review-item {
  margin-bottom: 10px;
}

.upload-demo {
  margin-top: 10px;
}

.input-row {
  display: flex;
  align-items: center;
}

.input-label {
  margin-right: 10px;
  margin-top: 10px;
}

.query-button {
  margin-right: 10px;
}

.edit-button {
  margin-left: 10px;
}

.app-container {
  margin: 0 auto; /* 水平居中对齐 */
  display: flex;
  justify-content: center; /* 垂直居中对齐 */
  align-items: center; /* 垂直居中对齐 */
  border-radius: 5px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  position: relative;
  font-family: "Malgun Gothic", sans-serif;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  animation: move-animation 2s infinite alternate;
}


@keyframes move-animation {
  0% {
    transform: translateX(-10px);
  }
  100% {
    transform: translateX(10px);
  }
}
.building-image {
  width: 20%; /* 设置宽度 */
  height: auto; /* 高度自动调整，保持原始比例 */
  margin-bottom: 10px;
  margin-right: 100px;
}
.building-image1 {
  width: 50%; /* 设置宽度 */
  height: auto; /* 高度自动调整，保持原始比例 */
  margin-bottom: 30px;
  margin-left: 250px;
}

.content-container {
  margin-bottom: 10px; /* 添加底部间距，可以根据需要进行调整 */
  margin-right: 20px;
}

.custom-container {
  font-family: 'Arial', sans-serif;
  font-size: 16px;
  color: #333;
  line-height: 1.5;
  padding: 10px;
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
