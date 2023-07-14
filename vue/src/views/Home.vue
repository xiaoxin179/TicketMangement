<template>
  <el-container style="height: 100%">
    <!--  头部  -->
    <el-header style="background-color: #cad1da">
      <div id="head_top">
        <div id="img">
          <img alt="" src="../assets/train.svg" height="54">
        </div>
        <div id="wenzi">
          <span>火车票管理系统</span>
        </div>
        <div id="logout">
          <el-button id="logout_button" type="primary" @click="logOut();" style="border-radius: 10px;" >退出登录</el-button>
        </div>
      </div>
    </el-header>
    <el-container>
      <!--  侧边栏   -->
      <el-aside width="200px" style="background-color: #D3DCE6">
        <el-menu class="el-menu-demo" style="background-color: #D3DCE6" :default-active="this.$route.path" router>
          <!-- <el-menu-item index="/home/show"><i class="el-icon-s-home" style="margin-right: 10px"></i>首页</el-menu-item> -->
          <el-menu-item index="/home/dormManagement"><i class="el-icon-monitor" style="margin-right: 10px"></i>用户信息管理</el-menu-item>
          <el-menu-item index="/home/TicketMangement"><i class="el-icon-message" style="margin-right: 10px"></i>车票信息信息</el-menu-item>
          <!-- <el-menu-item index="/home/systemConfig"><i class="el-icon-setting" style="margin-right: 10px"></i>设置</el-menu-item> -->
        </el-menu>
      </el-aside>
      <!--   主题区域   -->
      <el-main style="background-color: #eee">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: 'Home',
  methods: {
    logOut () {
      this.$confirm('该操作将退出登录，是否确定？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        localStorage.removeItem('token')
        this.$router.push('/')
      })
    }
  }
}
</script>

<style>
#head_top{
  display:flex;
  justify-content: space-between;
}
#img{
  margin-left: 50px;
}
#wenzi{
  margin-top:15px;
  font-family: 宋体;
  font-weight:bolder;
  font-size:30px;
}
#logout{
  margin-top: 10px;
}
</style>
