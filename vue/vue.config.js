// 跨域配置
module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080/TicketMangement6_war_exploded/',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}
