<template>
  <div class="classroom-booking">
    <el-button type="primary" @click="showBookingForm">预定教室</el-button>
    <el-dialog title="预定教室" :visible.sync="bookingFormVisible">
      <el-form ref="bookingForm" :model="bookingForm" label-width="100px">
        <el-form-item label="日期">
          <el-date-picker v-model="bookingForm.date" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="时间段">
          <el-select v-model="bookingForm.timeSlot" placeholder="选择时间段">
            <el-option
              v-for="timeSlot in timeSlots"
              :key="timeSlot"
              :label="formatTimeSlot(timeSlot)"
              :value="timeSlot"
              :disabled="isTimeSlotBooked(timeSlot)"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="cancelBooking">取消</el-button>
        <el-button type="primary" @click="confirmBooking">确认预定</el-button>
      </div>
    </el-dialog>

    <div class="booking-info">
      <h3>已预定教室：</h3>
      <ul>
        <li v-for="(booking, index) in bookings" :key="index">
          {{ booking.date }} {{ formatTimeSlot(booking.timeSlot) }}
          <el-button type="text" @click="cancelBooking(index)">取消预定</el-button>
          <el-button type="text" @click="viewBooking(index)">查看</el-button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      bookingFormVisible: false,
      bookingForm: {
        date: null,
        timeSlot: null,
      },
      bookings: [],
      timeSlots: ['08:00-10:00', '10:00-12:00', '14:00-16:00', '16:00-18:00'], // 可预定的时间段
    };
  },
  methods: {
    showBookingForm() {
      this.bookingFormVisible = true;
    },
    cancelBooking(index) {
      this.bookings.splice(index, 1);
    },
    viewBooking(index) {
      // 查看预定操作，根据需求实现
    },
    confirmBooking() {
      if (this.$refs.bookingForm.validate()) {
        this.bookings.push({
          date: this.bookingForm.date,
          timeSlot: this.bookingForm.timeSlot,
        });
        this.bookingFormVisible = false;
        this.$message.success('预定成功');
      }
    },
    isTimeSlotBooked(timeSlot) {
      return this.bookings.some((booking) => booking.timeSlot === timeSlot);
    },
    formatTimeSlot(timeSlot) {
      // 格式化时间段，根据需求实现
      return timeSlot;
    },
  },
};
</script>

<style scoped>
.classroom-booking {
  margin: 20px;
}

.booking-info {
  margin-top: 20px;
}
</style>
