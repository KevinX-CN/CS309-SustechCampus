<template>
  <div class="building">
    <h2 class="app-container">教室预订系统</h2>
    <el-row>
      <el-col :span="6">
        <h3>教学楼列表</h3>
        <el-menu
          default-active="building1"
          @select="selectBuilding"
          class="meanchoice"
        >
          <el-menu-item v-for="(building, index) in buildings" :key="index" :index="building.id">
            {{ building.name }}
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="18">
        <h3>教室列表</h3>
        <el-row v-if="selectedBuilding">
          <el-col :span="6" v-for="(room, index) in selectedBuilding.rooms" :key="index">
            <div class="room-card">
              <div class="room-name">{{ room.roomname }}</div>
              <div class="room-status" :class="{'room-available': some}">
                {{ some ? '可预订' : '已预订' }}
              </div>
              <el-button
                v-if="some"
                class="reserve-button"
                type="primary"
                @click="showBookingForm"
              >
                预订
              </el-button>
            </div>
            <el-dialog title="预定教室" :visible.sync="bookingFormVisible"  :append-to-body="true">
              <el-form ref="bookingForm" :model="bookingForm" label-width="100px">
                <el-form-item label="日期">
                  <el-date-picker v-model="bookingForm.date" type="date" placeholder="选择日期"></el-date-picker>
                </el-form-item>
                <el-form-item label="时间段">
                  <el-select v-model="bookingForm.timeSlot" placeholder="选择时间段">
                    <el-option
                      v-for="timeSlot in available"
                      :key="timeSlot"
                      :label="formatTimeSlot(timeSlot)"
                      :value="timeSlot"
                      :disabled="isTimeSlotBooked(timeSlot)"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-form>
              <div slot="footer">
                <el-button @click="notshowBookingForm">取消</el-button>
                <el-button type="primary" @click="confirmBooking(room)">确认预定</el-button>
              </div>
            </el-dialog>
          </el-col>
        </el-row>
        <div v-else>请选择一个教学楼</div>

      </el-col>
    </el-row>
    <div class="reservation-info">
      <h3 class="app-container">预订信息</h3>
      <div v-if="bookings.length > 0">
        <h3>已预定教室：</h3>
        <ul>
          <li v-for="(booking, index) in bookings" :key="index">
            {{booking.building}}-{{booking.roomname}}-{{ booking.data }} {{ formatTimeSlot(booking.time) }}
            <el-button type="text" @click="cancelBooking(index)">取消预定</el-button>
            <el-button type="text" @click="changeBooking(booking,index)">修改</el-button>
          </li>
        </ul>
    </div>
      <div v-else>暂无预订信息</div>
  </div>
    <div class="query">
      <h3 class="app-container">查询</h3>
      <el-button type="primary" @click="querybook" class="roomquery">教室查询</el-button>
    </div>
    <el-dialog title="查询教室" :visible.sync="queryFormVisible"  :append-to-body="true">
      <el-form ref="qureyForm" :model="qureyForm" label-width="100px">
        <el-form-item label="时间段">
          <el-select v-model="qureyForm.timeSlot" placeholder="选择时间段">
            <el-option
              v-for="timeSlot in available"
              :key="timeSlot"
              :label="formatTimeSlot(timeSlot)"
              :value="timeSlot"
              :disabled="isTimeSlotBooked(timeSlot)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日期">
          <el-date-picker v-model="qureyForm.date" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="教室楼名称">
          <el-select v-model="qureyForm.build" placeholder="选择教学楼">
            <el-option
              v-for="timeSlot in build"
              :key="timeSlot"
              :value="timeSlot"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="closequrey">取消</el-button>
        <el-button type="primary" @click="closequrey">确认预定</el-button>
      </div>
    </el-dialog>

    </div>
</template>

<script>

export default {
  data() {
    return {
      some : 'w',
      buildings: [
        {
          id: 'building1',
          name: '楼层1',
          rooms: [
            { id: 1, name: '101', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
            { id: 2, name: '102', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
            { id: 3, name: '103', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
          ],
        },
        {
          id: 'building2',
          name: '楼层2',
          rooms: [
            { id: 4, name: '201', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
            { id: 5, name: '202', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
            { id: 6, name: '203', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
            { id: 7, name: '204', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
          ],
        },
        {
          id: 'building3',
          name: '楼层3',
          rooms: [
            { id: 4, name: '201', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
            { id: 5, name: '202', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
            { id: 6, name: '203', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
            { id: 7, name: '204', available: true, availableTimes: ['08:00-10:00', '10:00-12:00', '14:00-16:00'] },
          ],
        },
        {
          id:'',
          name:'查询结果',
          rooms: []
        },
      ],
      bookingForm: {
        date: null,
        timeSlot: null,
      },
      qureyForm:{
        build:null,
        date: null,
        timeSlot: null,
      },
      available: ['08:00-10:00', '10:00-12:00', '14:00-16:00'],
      build: ['楼层1','楼层2'],
      selectedBuilding: null,
      bookingFormVisible:false,
      queryFormVisible:false,
      bookings: [],
      status:'',
      username: '',
      building: '工学院'
    };
  },
  async created() {
    this.status=this.$route.query.status
    this.username=this.$route.query.username
    await this.initbuilding()
  },
  methods: {
    async initbuilding(){
      const response = await this.$axios.get(`http://localhost:8082/exer/room/${this.building}/${1}`)
      this.buildings[0].rooms=response.data
      const response1 = await this.$axios.get(`http://localhost:8082/exer/room/${this.building}/${2}`)
      this.buildings[1].rooms=response1.data
      const response2 = await this.$axios.get(`http://localhost:8082/exer/room/${this.building}/${3}`)
      this.buildings[2].rooms=response2.data
      const response3 = await this.$axios.get(`http://localhost:8082/exer/findreserve/${this.username}`)
      this.bookings=response3.data
    },
    querybook(){
      this.queryFormVisible=true
    },
    closequrey(){
      this.queryFormVisible=false
    },
    changeBooking(booking,index){
      this.bookingForm.date=booking.date;
      this.bookingForm.timeSlot=booking.timeSlot;
      this.bookingFormVisible=true;
      this.cancelBooking(index)
    },
    isTimeSlotBooked(timeSlot) {
      return this.bookings.some((booking) => booking.timeSlot === timeSlot);
    },
    formatTimeSlot(timeSlot) {
      // 格式化时间段，根据需求实现
      return timeSlot;
    },
    async cancelBooking(index) {
      const response3 = await this.$axios.get(`http://localhost:8082/exer/deletereserve/${this.username}/${this.bookings[index].roomname}/${this.bookings[index].data}/${this.bookings[index].time}`)
      console.log(response3.data)
      this.bookings.splice(index, 1);
      this.$message.success('删除成功')
    },
    async confirmBooking(room) {
      console.log(room)
      const response = await this.$axios.get(`http://localhost:8082/exer/reserve/${this.username}/${room.roomname}/${this.bookingForm.date}/${this.bookingForm.timeSlot}`)
      if (response.data==='no'){
        alert('该时间已经被预定，请重新选择')
        return
      }
        this.bookingFormVisible = false;
        this.initbookform();
        await this.initbuilding();
        this.$message.success('预定成功');
    },
    showBookingForm() {
      this.bookingFormVisible = true;
    },
    notshowBookingForm() {
      this.bookingFormVisible = false;
      this.initbookform()
    },
    initbookform(){
      this.bookingForm.date=null;
      this.bookingForm.timeSlot=null;
    },
    selectBuilding(buildingId) {
      this.selectedBuilding = this.buildings.find((building) => building.id === buildingId);
    },
  },
};
</script>

<style scoped>
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
  animation: move-animation 4s infinite alternate;
}


@keyframes move-animation {
  0% {
    transform: translateX(-10px);
  }
  100% {
    transform: translateX(10px);
  }
}

.room-card {
  background: url("../../static/room.jpg");
  border: 1px solid #ccc;
  padding: 10px;
  background-size: contain;
  animation: scale-animation 2s infinite alternate;
  margin-right: 40px; /* 添加水平间距 */
  margin-bottom: 40px; /* 添加垂直间距 */
  text-align: center;
}

@keyframes scale-animation {
  0% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.1);
  }
  100% {
    transform: scale(1);
  }
}

.meanchoice{
  background-color: transparent;
}
.room-name {
  font-weight: bold;
  margin-bottom: 5px;
  text-align: center;
  display: block;
  color: rgba(222, 27, 72, 0.91);
}

.room-status {
  margin-bottom: 5px;
  text-align: center;
  display: block;
}

.room-available {
  color: #de1b1b;
}

.reserve-button {
  margin-top: 10px;
  display: inline-block;
}

.reservation-info {
  margin-top: 20px;
}

.reservation-info ul {
  padding-left: 0;
  list-style-type: none;
}

.reservation-info li {
  margin-bottom: 5px;
}

.query{
  text-align: center;
}

.roomquery{
  margin-top: 10px;
  display: inline-block;
}

.building{
  //background:url('../../static/web.jpeg');
  //width:100%;
  //height:100%;
  //position:fixed;
  //background-size:100% 100%;
}
</style>
