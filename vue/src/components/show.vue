<template>
  <div>
    <div id="tonji">数据统计</div>
    <div id="table1" ref="echarts1" style="height: 300px ;width: 300px;"></div>
    <div ref="echarts2" style="height: 300px ;width: 300px;"></div>
  </div>
</template>
<script>
// eslint-disable-next-line no-unused-vars
import * as echarts from 'echarts'

export default {
  mounted () {
    // 第一张表
    var myChart1 = echarts.init(this.$refs.echarts1)
    var option1 = {
      title: {
        text: '用户性别比例'
      },
      tooltip: {},
      legend: {
        data: ['男']
      },
      xAxis: {
        data: ['男', '女']
      },
      yAxis: {},
      series: [
        {
          name: '人数',
          type: 'bar',
          data: [5, 20]
        }
      ]
    }

    // 使用指定的配置项和数据
    myChart1.setOption(option1)
  },
  methods: {
    changeOption () {
      // 修改图表内容的逻辑代码
    }
  }
  // 第二张表
}
</script>
<style >
#tonji {
  font-size: 30px;
}
#table1{
  margin-top:30px;
  margin-right:30px;
}
</style>
