<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
        <%
                pageContext.setAttribute("APP_PATH", request.getContextPath());
        %>
        <meta charset="utf-8">
        <title>生长环境详情</title>
</head>
<body>
<div class="enviroment_box">
        <h3><strong>生长环境温湿度：</strong></h3>
        <div class="freshbox">
                <div class="row">
                        <div id="containers" style="width:878px;height: 400px;"></div>
                </div>
        </div>
        <h3><strong>牲畜位置活动信息：</strong></h3>
        <table class="table table-striped">
                <tbody>
                <tr>
                        <td>
                                <h2 class="glyphicon glyphicon-circle-arrow-down"> 牲畜活动信息</h2>
                                <div id="map"></div>
                        </td>
                </tr>
                </tbody>
        </table>
</div>
</body>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById("containers"));

    myChart.resize();

    myChart.setOption({
        title: {
            // text: '生长环境温度与湿度'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['温度', '湿度']
        },
        xAxis: {
            type: 'category',
            data: []
        },
        yAxis: {type: 'value'},
        series: [{
            name: '温度',
            type: 'line',
            data: []
        }, {
            name: '湿度',
            type: 'line',
            data: []
        }],
    });

    $.ajax({
        url:"${APP_PATH}/enviroment.do",
        type:"get",
        data:"annimalId="+532135131,
        success: function (data) {
            myChart.setOption({
                xAxis: {data: data.time},
                series: [{name: '温度', data: data.temperature}, {name: '湿度', data: data.humidity}]
            });
        },
        error: function (error) {
            console.log(error);
        }
    });

</script>

<script type="text/javascript">

    var path = new Array();

    $(function () {
        $.ajax({
            url: "${APP_PATH}/show_enviroment.do",
            type: "get",
            data: "label="+1000101,
            success: function (result) {
                // console.log(result);
                analisisResult(result);
            }
        });
    });

    // 地图显示 1
    function analisisResult(result) {
        var logisticList = result.data;
        $.each(logisticList, function (index, item) {
            path.push(new AMap.LngLat(item.latitude, item.longitude));
        });

        var map = new AMap.Map("map", {
            resizeEnable: true,
            center: [114.93, 25.83],
            zoom: 14
        });

        // 坐标转换
        AMap.convertFrom(path, 'gps', function (status, result) {
            if (result.info === 'ok') {
                var path2 = result.locations;
                polyline2 = new AMap.Polyline({
                    path: path2,
                    borderWeight: 2, // 线条宽度，默认为 1
                    strokeColor: "green", // 线条颜色
                    lineJoin: 'round' // 折线拐点连接处样式
                });
                map.setFitView([polyline2]);
                map.add(polyline2);
                text2 = new AMap.Text({
                    position: result.locations[0],
                    offset: new AMap.Pixel(-20, -20)
                })
            }
        });
    }
</script>
</html>
