<template>
  <div>
    <el-button type="primary" @click="addDialogFormVisible = !addDialogFormVisible"
               style="margin-bottom: 10px; margin-left: 170px; background-color: #636e83;border-radius: 10px;">添加乘客
    </el-button>
    <el-dropdown @command="handleCommand">
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="name">姓名</el-dropdown-item>
        <el-dropdown-item command="age">年龄</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-input v-model="name" placeholder="输入姓名" style="width: 20%;margin-left:600px;">
    </el-input>
    <el-button type="primary" slot="append" icon="el-icon-search" @click="queryInfo" style="border-radius: 10px;">查询</el-button>
    <!--  添加乘客信息弹窗  -->
    <el-dialog title="添加乘客" :visible.sync="addDialogFormVisible">
      <el-form :model="newStuForm" label-width="50px" >
        <el-form-item label="姓名" prop="name"  style="margin-right: 30px;">
          <el-input v-model="newStuForm.name" autocomplete="off" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age" style="margin-right: 30px;">
          <el-input v-model="newStuForm.age" autocomplete="off" placeholder="请输入年龄"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender"  style="margin-right: 30px;">
          <el-input v-model="newStuForm.gender" autocomplete="off" placeholder="请输入性别"></el-input>
        </el-form-item>
        <el-form-item label="身份" prop="statue"  style="margin-right: 30px;">
          <el-input v-model="newStuForm.statue" autocomplete="off" placeholder="请输入身份"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone"  style="margin-right: 30px;">
          <el-input v-model="newStuForm.phone" autocomplete="off" placeholder="请输入电话"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addNewUser">添 加</el-button>
      </div>
    </el-dialog>
    <!--  修改乘客信息弹窗  -->
    <el-dialog title="修改乘客信息" :visible.sync="updateDialogFormVisible">
      <el-form :model="editForm" label-width="80px">
        <!--autocomplete:禁止表单元素自动填充  -->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="editForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model="editForm.age" autocomplete="off" placeholder="请输入年龄"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="editForm.gender" autocomplete="off" placeholder="请输入性别"></el-input>
        </el-form-item>
        <el-form-item label="身份" prop="statue">
          <el-input v-model="editForm.statue" autocomplete="off" placeholder="请输入身份"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="editForm.phone" autocomplete="off" placeholder="请输入电话"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateStu">保 存</el-button>
      </div>
    </el-dialog>
    <!-- 乘客信息表格   -->
    <el-table
      :data="tableData"
      border
      style="width:fit-content; margin-left:170px;margin-right:100px;border-radius:20px">
      <el-table-column
        prop="id"
        label="编号"
        width="100">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="100">
      </el-table-column>
      <el-table-column
        prop="age"
        label="年龄"
        width="100">
      </el-table-column>
      <el-table-column
        prop="statue"
        label="属性"
        width="200">
      </el-table-column>
      <el-table-column
        prop="gender"
        label="性别"
        width="200">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="电话"
        width="200">
      </el-table-column>
      <el-table-column
        label="操作"
        width="200">
        <template slot-scope="scope">
          <el-button @click="handleUpdate(scope.row)" type="info" size="small">修改</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope.row.id, scope.$index)">删除</el-button>
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
    //   console.log('进入验证年龄')
    //   const regularVal = /^(\d{1,2}-\d{3})$/
    //   if (value === '') {
    //     cb(new Error('请输入年龄'))
    //   } else if (!regularVal.test(value)) {
    //     cb(new Error('年龄必须是xxx-xxx的形式'))
    //   } else {
    //     cb()
    //   }
    // }
    return {
      addDialogFormVisible: false,
      updateDialogFormVisible: false,
      tableData: [],
      name: '',
      newStuForm: {
        id: '',
        gender: '',
        name: '',
        age: '',
        statue: '',
        phone: ''
      },
      editForm: {
        id: '',
        name: '',
        gender: '',
        age: '',
        phone: '',
        statue: ''
      },
      queryMethod: 'id',
      queryCol: 'id',
      rules: {
        name: [
          {
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          }
        ],
        age: [
          {
            // validator: validateage,
            required: true,
            trigger: 'blur',
            message: '请输入年龄'
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
        case 'id':
          this.queryMethod = 'id'
          break
        case 'name':
          this.queryMethod = '姓名'
          break
        case 'age':
          this.queryMethod = '年龄'
          break
        case 'phone':
          this.queryMethod = '电话'
          break
      }
    },
    // 查询 表格数据 1
    selectAllStudent () {
      this.tableData = []
      const url = `${baseApiUrl}/selectAllUser`
      // 获取数据库全部乘客列表信息
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
    // 添加乘客 1
    addNewUser: function () {
      let formData = 'name=' + this.newStuForm.name
      formData = formData.concat('&age=' + this.newStuForm.age)
      formData = formData.concat('&gender=' + this.newStuForm.gender)
      formData = formData.concat('&statue=' + this.newStuForm.statue)
      formData = formData.concat('&phone=' + this.newStuForm.phone)
      const url = `${baseApiUrl}/addUser`
      axios.post(url, formData, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded;charset=GBK'
        }
      }).then(rs => {
        this.$message.success('添加成功')
        this.selectAllStudent()
      }).catch(() => {
        this.$message.error('添加失败')
      })
      this.addDialogFormVisible = false
      this.newStuForm = {}
    },
    // 更新表格数据 1
    updateStu: function () {
      if (this.editForm.name && this.editForm.age) {
        this.$confirm('该操作将修改一个乘客信息，是否确定？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let formData = 'name=' + this.editForm.name
          formData = formData.concat('&age=' + this.editForm.age)
          formData = formData.concat('&gender=' + this.editForm.gender)
          formData = formData.concat('&statue=' + this.editForm.statue)
          formData = formData.concat('&phone=' + this.editForm.phone)
          formData = formData.concat('&id=' + this.editForm.id)
          const url = `${baseApiUrl}/updateUser?` + formData
          axios.post(url, formData, {
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded;charset=GBK'
            }
          }).then(rs => {
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
    // 条件搜索表格数据 1
    queryInfo () {
      const url = `${baseApiUrl}/searchUser`
      axios.get(url, {
        params: {
          name: this.name
        }
      }).then((rs) => {
        this.tableData = []
        console.log(rs.data)
        rs.data.rows.forEach(item => { this.tableData.push(item) })
      })
    },
    handleUpdate (row) {
      this.updateDialogFormVisible = true
      this.editForm.id = row.id
      this.editForm.name = row.name
      this.editForm.age = row.age
      this.editForm.gender = row.gender
      this.editForm.statue = row.statue
      this.editForm.phone = row.phone
    },
    handleDelete: function (id, i) {
      console.log(1)
      this.$confirm('该操作将删除一个乘客信息，是否确定？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const url = `${baseApiUrl}/deleteUser`
        // const url = 'http://127.0.0.1:3030/api/deleteTicket'
        axios.get(url, {
          params: {
            id: id
          },
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
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
