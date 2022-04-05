var actgraph=echarts.init(document.querySelector('.act .graph'));
var actdata=[100,85,48,80,70,65,70];
var actnum=((new Date).getDay()+6)%7;
actdata[actnum]={value: actdata[actnum],itemStyle: { color: 'rgba(0, 125, 250, 0.54)',}};
option1 = {
    tooltip: {
        trigger: 'axis',
        axisPointer: { type: 'cross' }
      },
    xAxis: {
        type: 'category',
        axisTick: {
        show: false
        },
        axisLine: {
            show: false,
        },
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
        show: false,
        type: 'value',
        axisTick: {
            show: false,
          },
          axisLine: {
            show: false,
          },
          splitLine: {
            show: false,
          },
    },
    series: [
      {
        name: '活跃程度',
        data: actdata,
        type: 'bar',
        itemStyle:{barBorderRadius: 20},
        barWidth : 22,
        color: 'rgba(196, 196, 196, 1)',
        label: {
            show: true,
            position: 'top',
            color: 'rgba(140, 140, 140, 1)',
          },
      }
    ]
  };
  actgraph.setOption(option1);
  window.onresize = function () {
    actgraph.resize();
    pregraph.resize();
};