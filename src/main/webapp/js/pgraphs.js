var pregraph=echarts.init(document.querySelector('.pre .graph'));
var data=[6, 32, 70, 86, 68.7, 100.7, 125.6, 112.2, 78.7, 48.8, 36.0, 19.3];
for(var i=0;i<data.length;i++){
    data[i]=data[i].toFixed(2);
}
function turn(a){
    var k=new Array();
    for(var i=0;i<a.length;i++){
        k[i]=(a[i]*0.1).toFixed(2);
    }
    return k;
}
var option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: { type: 'cross' }
    },
    xAxis: [
      {
        type: 'category',
        axisTick: {
          alignWithLabel: true,
          show: false
        },
        axisLine: {
          show: false,
        },
        data: [
          '1:00',
          '2:00',
          '3:00',
          '4:00',
          '5:00',
          '6:00',
          '7:00',
          '8:00',
          '9:00',
          '10:00',
          '11:00',
          '12:00',
          '13:00',
          '14:00',
          '15:00',
          '16:00',
          '17:00',
          '18:00',
          '19:00',
          '20:00',
          '21:00',
          '22:00',
          '23:00',
          '24:00',
        ]
      }
    ],
    yAxis: [
      {
        type: 'value',
        name: '千帕/kPa',
        min: 0,
        max: 250,
        position: 'left',
        axisLabel: {
          formatter: '{value}'
        }
      },
      {
        type: 'value',
        name: '毫米汞柱/mmHg',
        min: 0,
        max: 25,
        position: 'right',
        axisLabel: {
          formatter: '{value}'
        }
      }
    ],
    series: [
      {
        name: '血压/kPa',
        type: 'line',
        showSymbol: false,
        lineStyle: {color: 'transparent'},
        areaStyle: {color:{
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                    offset: 0, color: 'rgba(211, 232, 242, 1)' // 0% 处的颜色
                }, {
                    offset: 1, color: 'rgba(161, 196, 253, 1)' // 100% 处的颜色
                }],
                global: false // 缺省为 false
            }
        },
        smooth: true,
        yAxisIndex: 0,
        data: data
      },
      {
        name: '血压/mmHg',
        type: 'line',
        symbol: 'none',
        lineStyle: {color: 'transparent'},
        smooth: true,
        yAxisIndex: 1,
        data: turn(data)
      }
    ]
  };
pregraph.setOption(option);