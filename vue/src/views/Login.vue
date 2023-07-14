<template>
  <div
    style="width: 100vw;height: 100vh; background-color: #B3C0D1"
  >
    <el-row
      :gutter="10"
      style="width: 100%;height: 100%;"
    >
      <el-col :span="24">
        <div class="login-wrapper" style="width: 50%; margin: 100px auto">
          <h1 style="text-align: center">登录</h1>
          <el-form :model="loginForm" label-width="100px" ref="loginForm" style="width: 60%; margin: 20px auto">
            <el-form-item label="用户名">
              <el-input v-model="loginForm.userName" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="loginForm.phone" placeholder="电话"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="loginForm.password" placeholder="请输入密码" type="password"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="login()" style="width: 100%">登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios'
import baseApiUrl from '../../config.default'
export default {
  name: 'LoginPage',
  data () {
    // let vm = this;
    return {
      loginForm: {
        userName: '',
        password: '',
        phone: ''
      }
    }
  },
  methods: {
    login () {
      if (this.loginForm) {
        const url = `${baseApiUrl}/login`
        // const json = { userName: this.loginForm.userName, password: this.loginForm.password, phone: this.loginForm.phone }
        axios.post(url, 'userName=' + this.loginForm.userName + '&password=' + this.loginForm.password + '&phone=' + this.loginForm.phone).then(rs => {
          if (rs.data === 'loginSuccess') {
            this.$message.success('登陆成功')
            // localStorage.setItem('token', rs.data.token)
            this.$router.push('/home')
          } if (rs.data === '手机号错误') {
            this.$message.error('手机号错误')
          } if (rs.data === '密码错误') {
            this.$message.error('密码错误')
          } if (rs.data === '用户不存在') {
            this.$message.error('用户不存在')
          }
        }).catch(() => {
          this.$message.error('登录失败')
        })
      }
    }
  }
}
</script>

<style>
body {
  margin: 0;
}
</style>
