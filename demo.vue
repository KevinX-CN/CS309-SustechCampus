<template>
  <div id="building">
    <!-- 建筑介绍 -->
    <h2 class="app-container">建筑介绍</h2>
    <div class="building-description">
      <div v-if="!isEditMode">
        <div >
          {{buildingContent}}}
          <img src="../assets/images.jpg" alt="Building Image">
        </div>
        <button class="edit-button" @click="toggleEditMode">修改</button>
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
    <h2 class="app-container">评论</h2>
    <el-button class="add-review-button" type="primary" @click="showModal">添加评价</el-button>

    <!-- 评论弹窗 -->
    <el-dialog
      title="评论"
      :visible.sync="showCommentModal"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :append-to-body="true"
    >
      <ul>
        <li v-for="review in reviews" :key="review.id" class="review-item">
          <p>{{ review.username }}</p>
          <p>{{ review.comment }}</p>
          <img v-if="review.image" :src=review.image alt="用户上传的图片">
          <hr>
        </li>
      </ul>

      <el-form ref="commentForm" :model="userReview" label-width="80px">
        <el-form-item label="用户名：" required>
          <el-input v-model="userReview.username"></el-input>
        </el-form-item>
        <el-form-item label="评论：" required>
          <el-input type="textarea" v-model="userReview.comment" :rows="4"></el-input>
        </el-form-item>
        <el-form-item label="上传图片：">
          <el-upload
            class="upload-demo"
            action="/upload"
            :on-success="handleImageUpload"
            :show-file-list="false"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeModal">取消</el-button>
        <el-button type="primary" @click="submitReview">提交评价</el-button>
      </span>
    </el-dialog>

    <h2 class="app-container">公交查询</h2>
    <div class="input-row">
      <span class="input-label">公交路线操作</span>
      <el-input v-model="input1" placeholder="起始点"class="inputbutton"></el-input>
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
</template>

<script>
export default {
  data() {
    return {
      buildingContent: "这是建筑的介绍",
      isEditMode: false,
      reviews: [
        { id: 1, username: "用户A", comment: "很棒的建筑！", image:"../assets/images.jpg" },
        { id: 2, username: "用户B", comment: "非常漂亮！", image: null },
        { id: 3, username: "用户C", comment: "有点小问题，但总体还不错。", image: null },
      ],
      userReview: {
        username: "",
        comment: "",
        image: null,
      },
      showCommentModal: false, // 控制评论弹窗显示/隐藏
      input1: '',
      input2:'',
      showPopupDialog: false, // 控制公交查询弹窗显示/隐藏
      name: '公交查询',
    };
  },
  methods: {
    toggleEditMode() {
      this.isEditMode = !this.isEditMode;
    },
    saveBuildingContent() {
      // 保存建筑介绍的编辑内容
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
    handleImageUpload(file) {
      // 处理图片上传
      // 你可以在这里实现图片上传的逻辑
    },
    submitReview() {
      // 提交评论
      const newReview = {
        id: this.reviews.length + 1,
        username: this.userReview.username,
        comment: this.userReview.comment,
        image: this.userReview.image,
      };
      this.reviews.push(newReview);
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
    resetReviewForm() {
      // 重置评论表单
      this.userReview.username = '';
      this.userReview.comment = '';
      this.userReview.image = null;
    },
  },
};
</script>

<style>
#building {
  background-image: url('../assets/web.jpeg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  text-align: center;
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

</style>
