        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.querySelector('.day .pie'));
  
        // 指定图表的配置项和数据
        var option = {
          title: {},
          color:['rgba(0, 58, 140, 1)','rgba(9, 109, 217, 1)','rgba(64, 169, 255, 1)','rgba(145, 196, 237, 1)'],
          tooltip: {},
          series: [
              {
                  type: 'pie',
                  label: {
                      show: false
                  },
                  data: [
                      {
                          value: 5,
                          name: '30天以上'
                      },
                      {
                          value: 24,
                          name: '20天以上'
                      },
                      {
                          value: 44,
                          name: '10天以上'
                      },
                      {
                          value: 52,
                          name: '10天以内'
                      }
                  ],
                  radius: '90px'
              }
          ]
        };
  
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);