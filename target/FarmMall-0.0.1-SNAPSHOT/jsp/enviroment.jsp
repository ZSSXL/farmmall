<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html style="height: 100%">
<%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<head>
        <meta charset="utf-8">
</head>
<body style="height: 100%; margin: 0">
<div id="containers" style="height: 100%"></div>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script src="https://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById("containers"));

    myChart.setOption({
        title: {
            text: '牲畜生长环境温度与湿度'
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
        }]
    });
    $.ajax({
        url: "${APP_PATH}/enviroment.do",
        type: "get",
        data: "annimalId=" + 532135131,
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
</body>
</html>