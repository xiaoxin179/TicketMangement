<template>
  <div>
    <el-button type="primary" @click="addDialogFormVisible = !addDialogFormVisible"
               style="margin-bottom: 10px; margin-left: 170px; background-color: #636e83;border-radius: 10px;">添加车票
    </el-button>
    <el-dropdown @command="handleCommand">
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="start_station">始发站</el-dropdown-item>
        <el-dropdown-item command="end_station">终点站</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-input v-model="StartAdd" placeholder="输入始发站" style="width: 20%;margin-left:340px">
    </el-input>
    <el-input v-model="EndAdd" placeholder="输入终点站" style="width: 20%">
    </el-input>
    <el-button type="primary" slot="append" icon="el-icon-search" @click="queryInfo" style="border-radius: 10px;">查询</el-button>
    <!--  添加学生信息弹窗  -->
    <el-dialog title="添加车票" :visible.sync="addDialogFormVisible">
      <el-form :model="newStuForm" label-width="50px" :rules="rules">
        <el-form-item label="始发站" prop="start_station"  style="margin-right: 30px;">
          <el-input v-model="newStuForm.start_station" autocomplete="off" placeholder="请输入始发站"></el-input>
        </el-form-item>
        <el-form-item label="终点站" prop="end_station">
          <el-input v-model="newStuForm.end_station" autocomplete="off" placeholder="请输入终点站"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addNewStudent">添 加</el-button>
      </div>
    </el-dialog>
    <!--  修改学生信息弹窗  -->
    <el-dialog title="修改车票信息" :visible.sync="updateDialogFormVisible">
      <el-form :model="editForm" label-width="80px">
        <!--autocomplete:禁止表单元素自动填充  -->
        <el-form-item label="始发站" prop="start_station">
          <el-input v-model="editForm.start_station" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="终点站" prop="end_station">
          <el-input v-model="editForm.end_station" autocomplete="off" placeholder="请输入终点站"></el-input>
        </el-form-item>
        <el-form-item label="票价" prop="price">
          <el-input v-model="editForm.price" autocomplete="off" placeholder="请输入票价"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateStu">保 存</el-button>
      </div>
    </el-dialog>
    <!-- 车票信息表格   -->
    <el-table
      :data="tableData"
      border
      style="width:fit-content; margin-left:170px;margin-right:100px;border-radius:20px">
      <el-table-column
        prop="tid"
        label="编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="start_station"
        label="火车始发站"
        width="100">
      </el-table-column>
      <el-table-column
        prop="end_station"
        label="火车终点站"
        width="100">
      </el-table-column>
      <el-table-column
        prop="residual_ticket"
        label="剩余票数"
        width="200">
      </el-table-column>
      <!-- <el-table-column
        prop="total_ticket"
        label="总票数"
        width="200">
      </el-table-column> -->
      <el-table-column
        prop="price"
        label="票价(元)"
        width="150">
      </el-table-column>
      <el-table-column
        prop="start_time"
        label="发车时间"
        width="150">
      </el-table-column>
      <el-table-column
        prop="end_time"
        label="到达时间"
        width="150">
      </el-table-column>
      <el-table-column
        label="操作"
        width="200">
        <template slot-scope="scope">
          <el-button @click="handleUpdate(scope.row)" type="info" size="small">修改</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row.tid, scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios'
import baseApiUrl from '../../config.default'

export default {
  name: 'DormManagement',
  data: function () {
    // const validateage = (rule, value, cb) => {
    //   console.log('进入验证终点站')
    //   const regularVal = /^(\d{1,2}-\d{3})$/
    //   if (value === '') {
    //     cb(new Error('请输入终点站'))
    //   } else if (!regularVal.test(value)) {
    //     cb(new Error('终点站必须是xxx-xxx的形式'))
    //   } else {
    //     cb()
    //   }
    // }
    return {
      addDialogFormVisible: false,
      updateDialogFormVisible: false,
      tableData: [],
      StartAdd: '',
      EndAdd: '',
      newStuForm: {
        TicketNum: '',
        start_station: '',
        end_station: ''
      },
      editForm: {
        tid: '',
        start_station: '',
        end_station: '',
        price: ''
      },
      queryMethod: 'id',
      queryCol: 'TicketNum',
      rules: {
        start_station: [
          {
            required: true,
            message: '请输入始发站',
            trigger: 'blur'
          }
        ],
        end_station: [
          {
            // validator: validateage,
            required: true,
            trigger: 'blur',
            message: '请输入终点站'
          }
        ]
      }
    }
  },
  mounted () {
    // this.selectAllStudent()
  },
  created () {
    this.selectAllStudent()
  },
  methods: {
    handleCommand (command) {
      this.queryCol = command
      switch (command) {
        case 'TicketNum':
          this.queryMethod = 'id'
          break
        case 'start_station':
          this.queryMethod = '始发站'
          break
        case 'end_station':
          this.queryMethod = '终点站'
          break
        case 'phone':
          this.queryMethod = '电话'
          break
      }
    },
    // 查询 表格数据 1
    selectAllStudent () {
      this.tableData = []
      const url = `${baseApiUrl}/selectTicket`
      // 获取数据库全部车票列表信息
      axios.get(url).then(rs => {
        if (rs.status === 200) {
          console.log(rs)
          rs.data.rows.forEach(item => { this.tableData.push(item) })
        }
      }).catch(error => {
        if (error.response.status === 401) {
          this.$message.error(error.response.data.message)
          this.$router.push('/')
        }
      })
    },
    // 添加车票 1
    addNewStudent: function () {
      let formData = 'start_station=' + this.newStuForm.start_station
      formData = formData.concat('&end_station=' + this.newStuForm.end_station)
      const url = `${baseApiUrl}/addTicketController`
      axios.post(url, formData, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded;charset=GBK'
        }
      }
      ).then(rs => {
        this.$message.success('添加成功')
        this.selectAllStudent()
      }).catch(() => {
        this.$message.error('添加失败')
      })
      this.addDialogFormVisible = false
      this.newStuForm = {}
    },
    // 更新表格数据
    updateStu: function () {
      if (this.editForm.start_station && this.editForm.end_station) {
        this.$confirm('该操作将修改一个车票信息，是否确定？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let formData = 'start_station=' + this.editForm.start_station
          formData = formData.concat('&end_station=' + this.editForm.end_station)
          formData = formData.concat('&price=' + this.editForm.price)
          formData = formData.concat('&tid=' + this.editForm.tid)
          const url = `${baseApiUrl}/updateTicket?` + formData
          axios.post(url, formData, {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded;charset=GBK'
            }
          }).then(rs => {
            console.log('添加车票结果:', rs)
            this.$message.success('修改成功')
            this.selectAllStudent()
          }).catch(() => {
            this.$message.error('修改失败')
          })
          this.updateDialogFormVisible = false
        })
      } else {
        this.$message.warning('信息不全，请完善后再添加')
      }
    },
    // 搜索表格数据 1
    queryInfo () {
      const url = `${baseApiUrl}/ticketsearch`
      axios.get(url, {
        params: {
          start_station_name: this.StartAdd,
          end_station_name: this.EndAdd
        }
      }).then((rs) => {
        this.tableData = []
        console.log(rs.data)
        rs.data.rows.forEach(item => { this.tableData.push(item) })
      })
    },
    handleUpdate (row) {
      console.log('修改：' + row.tid)
      this.editForm.tid = row.tid
      this.updateDialogFormVisible = true
      this.editForm.start_station = row.start_station
      this.editForm.end_station = row.end_station
      this.editForm.price = row.price
    },
    // 删除 1
    handleDelete: function (row, i) {
      console.log(1)
      this.$confirm('该操作将删除一个车票信息，是否确定？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const url = `${baseApiUrl}/deleteTicket`
        // const url = 'http://127.0.0.1:3030/api/deleteTicket'
        axios.get(url, {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            Authentication: localStorage.getItem('token')
          },
          params: {
            tid: row
          }
        }).then(rs => {
          console.log('删除结果:', rs)
          this.tableData.splice(i, 1)
          this.$message.success('删除成功')
        }).catch(() => {
          this.$message.error('删除失败')
        })
      })
    }
  }
}
</script>

<style>

</style>
