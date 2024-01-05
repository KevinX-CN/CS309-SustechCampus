<template>
  <div >
    <h2 id="a">Add Room list</h2>
    <el-table :data="rooms" style="width: 80%;;margin: 0 auto;background-color: transparent">
      <el-table-column prop="Room Name" label="Room Name" />
      <el-table-column prop="Department" label="Department"/>
      <el-table-column prop="Type" label="Type"/>
      <el-table-column prop="Location" label="Location">
        <template slot-scope="{ row }">
          <span>{{ row.Location.subLocation1 }}</span>
          <span>{{ row.Location.subLocation2 }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="Date" label="Date"/>
      <el-table-column prop="Star Time" label="Star Time"/>
      <el-table-column prop="End Time" label="End Time"/>
      <el-table-column prop="Max Duration" label="Max Duration"/>
      <el-table-column label="Operation">
        <template #default="scope">
          <div class="button-container">
          <el-button class="delete-button" type="danger" @click="deleteRoom(scope.$index)">
            <i class="el-icon-delete"></i>
            Delete
          </el-button>
          <el-button class="edit-button" type="primary" @click="editRoom(scope.$index)">
            <i class="el-icon-edit"></i>
            Edit
          </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>

    <el-button style="margin-left: 45%; margin-top: 20px" type="primary" @click="createNewRoom">Add Room</el-button>

    <el-dialog
       :visible.sync="dialogVisible"
       title="Add Room"
       width="40%"
     >
       <el-form
         ref="RoomForm"
         :model="RoomForm"
         :rules="rules"
         label-width="auto"
         label-position="right"
         size="default"
       >
         <el-form-item label="Room Name" prop="RoomName">
           <el-input v-model="RoomForm.RoomName"/>
         </el-form-item>
         <el-form-item label="Type" prop="Type">
           <el-radio-group v-model="RoomForm.Type" size="medium">
             <el-radio-button v-for="(item,index) in listArr" :key="index" :label=item>{{item}}</el-radio-button>
           </el-radio-group>
         </el-form-item>
         <el-form-item label="Department" prop="Department">
           <el-input v-model="RoomForm.Department"/>
         </el-form-item>
         <el-form-item label="Date" prop="Date">
           <el-date-picker
             value-format='yyyy/MM/dd'
             v-model="RoomForm.Date"
             type="date"
             label="Pick a date"
             placeholder="Pick a date"
             style="width: 100%"
             :picker-options="{ disabledDate: disabledDate }"
           />
         </el-form-item>
         <el-form-item label="Start Time" prop="StarTime">
           <el-time-picker
             format='HH:mm'
             value-format='HH:mm'
             v-model="RoomForm.StarTime"
             label="Pick a time"
             placeholder="Pick a time"
             style="width: 100%"
             @change="checkTime"
           />
         </el-form-item>
         <el-form-item label="End Time" prop="EndTime">
           <el-time-picker
             format='HH:mm'
             value-format='HH:mm'
             v-model="RoomForm.EndTime"
             label="Pick a time"
             placeholder="Pick a time"
             style="width: 100%"
             :picker-options="{ disabledHours: disabledEndHours, disabledMinutes: disabledEndMinutes }"
             @change="checkTime"
           />
         </el-form-item>
         <el-form-item label="Location" prop="Location">
           <el-select v-model="RoomForm.Location.subLocation1" style="width: 300px" placeholder="Select a Location">
             <el-option v-for="option in options" :label="option.label" :value="option.value"></el-option>
           </el-select>
           <el-input v-model="RoomForm.Location.subLocation2" />
         </el-form-item>
         <el-form-item label="Max Duration" prop="MaxDuration">
           <el-input v-model="RoomForm.MaxDuration"></el-input>
         </el-form-item>
         <el-form-item>
           <el-button type="primary" @click="AddFlight('RoomForm')">Create</el-button>
           <el-button type="primary" @click="endRoom()">cancel</el-button>
         </el-form-item>
       </el-form>
     </el-dialog>
  </div>

</template>

<script >
export default{
  name:'RoomTable',
  data(){
    const RoomNameValidator = (rule, value, callback) => {
      const re = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$/;
      if (!value) {
        return callback(new Error('Please input room name'));
      }
      if (!re.test(value)) {
        return callback(new Error('Invalid room name'));
      }
      callback();
    };
    const departmentValidator = (rule, value, callback) => {
      const re = /^[a-zA-Z\s]+$/;
      if (!value) {
        callback(new Error('Please input department'));
      }
      if (!re.test(value)) {
        return callback(new Error('Invalid department'));
      }
      callback();
    };
    const locationValidator = (rule, value, callback) => {
      const re = /^[A-Za-z0-9]+$/;
      if (!value.subLocation1||!value.subLocation2) {
        callback(new Error('Please input location'));
      }
      if (!re.test(value.subLocation2)) {
        return callback(new Error('Invalid location'));
      }
      callback();
    };
    const maxValidator = (rule, value, callback) => {
      const re = /^[0-9]+$/;
      if (!value) {
        callback(new Error('Please input maxduration'));
      }
      if (!re.test(value)) {
        return callback(new Error('Invalid maxduration'));
      }
      callback();
    };
    return {
      rules: {
        RoomName: [
          {validator: RoomNameValidator, trigger: 'blur'},
          {required: true, trigger: true}
        ],
        Type: [
          {required: true, message: 'Please input Type', trigger: 'blur'},
        ],
        Department: [
          {validator: departmentValidator, trigger: 'blur'},
          {required: true, trigger: true}
        ],
        Date: [
          {required: true, message: 'Please select the date of room', trigger: 'blur'},
        ],
        StarTime: [
          {required: true, message: 'Please select the start time of room', trigger: 'blur'},
        ],
        EndTime: [
          {required: true, message: 'Please select the end time of room', trigger: 'blur'},
        ],
        Location: [
          {validator: locationValidator, trigger: 'blur'},
          {required: true, trigger: true}
        ],
        MaxDuration: [
          {validator: maxValidator, trigger: 'blur'},
          {required: true, trigger: true}
        ],
      },
      rooms: [
        {
          "Room Name" :"Room1",
          "Department" :"Electrical",
          "Type" :"small",
          "Location" :{
            "subLocation1":"South Building",
            "subLocation2":"426A"
          },
          "Date":"2023/9/10",
          "Star Time":"08:00",
          "End Time":"20:00",
          "Max Duration":"2h"
        },
        {
          "Room Name" :"Room2",
          "Department" :"Computer Science",
          "Type" :"big",
          "Location" :{
            "subLocation1":"South Building",
            "subLocation2":"434A"
          },
          "Date":"2023/9/10",
          "Star Time":"00:00",
          "End Time":"24:00",
          "Max Duration":"4h"
        },
      ],
      RoomForm: {
        RoomName :"",
        Department :"",
        Type :"",
        Location :{
          subLocation1:"",
          subLocation2:"",
        },
        Date:"",
        StarTime:"",
        EndTime:"",
        MaxDuration:""
      },
      options: [ // 选项列表
        { label: 'Teaching Building No.1 Lecture Hall', value: 'Teaching Building No.1 Lecture Hall' },
        { label: 'Research Building Lecture Hall', value: 'Research Building Lecture Hall' },
        { label: 'Library Conference Hall', value: 'Library Conference Hall' },
        { label: 'South Building', value: 'South Building' },
      ],
      //  循环列表
      listArr:["small","medium","big"],
      dialogVisible: false,
      selectedFlightIndex: -1,
      initialFlightForm: {
        RoomName :"",
        Department :"",
        Type :"",
        Location :{
          subLocation1:"",
          subLocation2:"",
        },
        Date:"",
        StarTime:"",
        EndTime:"",
        MaxDuration:""
      },
    }
  },
  methods:{
    createNewRoom() {
      // console.log(this.dialogVisible)
      this.RoomForm = { ...this.initialFlightForm }
      this.dialogVisible = true
      // console.log(this.dialogVisible)
    },
    deleteRoom(index) {
      this.rooms.splice(index, 1)
    },
    editRoom(index) {
      this.selectedFlightIndex = index;
      const selectedFlight = this.rooms[index];
      this.RoomForm = {
        RoomName: selectedFlight['Room Name'],
        Department : selectedFlight.Department,
        Type : selectedFlight.Type,
        Date: selectedFlight.Date,
        Location: selectedFlight.Location,
        StarTime: selectedFlight["Star Time"],
        EndTime: selectedFlight["End Time"],
        MaxDuration:selectedFlight["Max Duration"].slice(0,-1)
      };
      this.dialogVisible = true;
    },
    AddFlight(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.selectedFlightIndex !== -1) {
            // 在编辑模式下更新选中航班的值
            const selectedFlight = this.rooms[this.selectedFlightIndex];
            selectedFlight["Room Name"] = this.RoomForm.RoomName;
            selectedFlight.Department = this.RoomForm.Department;
            selectedFlight.Type = this.RoomForm.Type;
            selectedFlight.Date = this.RoomForm.Date;
            selectedFlight.Location = this.RoomForm.Location;
            selectedFlight["Star Time"] = this.RoomForm.StarTime;
            selectedFlight["End Time"] = this.RoomForm.EndTime;
            selectedFlight["Max Duration"] = this.RoomForm.MaxDuration+"h";
            alert('Submit!');
          } else {
            // 在添加模式下添加新的航班
            this.rooms.push({
              "Room Name":this.RoomForm.RoomName,
              "Department" :this.RoomForm.Department,
              "Type" :this.RoomForm.Type,
              "Date" :this.RoomForm.Date,
              "Location" : this.RoomForm.Location,
             "Star Time" : this.RoomForm.StarTime,
              "End Time" : this.RoomForm.EndTime,
              "Max Duration" : this.RoomForm.MaxDuration+"h"
            });
          }
          this.dialogVisible = false;
          this.selectedFlightIndex = -1; // 重置选中航班的索引值
        } else {
          console.log('Add/Edit failed');
        }
      });
    },
    endRoom(){
        this.dialogVisible=false;
    },
    disabledDate(time) { // 禁用今天之前的时间
        return time.getTime() < new Date()
      },
    disabledEndHours() {
      return Array.from({ length: 24 }, (_, i) => i < new Date(this.RoomForm.StarTime).getHours());
    },
    disabledEndMinutes(hour) {
      if (hour === new Date(this.RoomForm.StarTime).getHours()) {
        return Array.from({ length: 60 }, (_, i) => i < new Date(this.RoomForm.StarTime).getMinutes());
      }
      return [];
    },
    checkTime() {
      if (this.RoomForm.StarTime && this.RoomForm.EndTime) {
        const start = this.RoomForm.StarTime.split(":");
        const end = this.RoomForm.EndTime.split(":");
        if (start[0] > end[0] || (start[0] === end[0] && start[1] >= end[1])) {
          this.RoomForm.EndTime = "";
          this.$message.error("结束时间必须晚于开始时间");
        }
      }
    },
  },

}

</script>
<style >

#a {
  text-align: center;
  font-size: 20px;
  font-style: italic;
  margin: 20px;
}

.delete-button {
  margin-bottom: 6px;
  /* 自定义按钮样式 */
  color: #fff;
  border: none;
  font-size: 14px;
  padding: 8px 16px;
  border-radius: 4px;
}

.edit-button {
  /* 自定义编辑按钮的样式 */
  margin-bottom: 10px;
  /* 自定义按钮样式 */
  background-color: #409eff;
  color: #fff;
  border: none;
  font-size: 14px;
  padding: 8px 16px;
  border-radius: 4px;
}

.edit-button:hover {
  background-color: #66b1ff;
}

.edit-button i {
  margin-right: 6px;
}
.button-container {
  display: flex;
  flex-direction: column;
}

</style>
