<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
        <%
                pageContext.setAttribute("APP_PATH", request.getContextPath());
        %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <title>地图</title>
        <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css"/>
        <style>
                html,body,#container{
                        height:100%;
                        width:100%;
                }
        </style>
</head>
<body>
<div id="container">

</div>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.15&key=7b4b13243e3bb0c9cfef5cee91268600&plugin=AMap.Geocoder"></script>
<script type="text/javascript">
    var path = new Array();

   $(function () {
        $.ajax({
            /*url:"${APP_PATH}/show_enviromen.do",
            type:"get",
            data:"label="+1000101,*/
            url:"${APP_PATH}/show_logistic.do",
            type:"get",
            data:"boxId="+531651109,
            success:function(result){
                console.log(result);
                analysisResult(result);
            }
        });
    });

   // 地图显示
   function analysisResult(result){
        var logisticList = result.data;
        $.each(logisticList,function (index,item) {
            path.push(new AMap.LngLat(item.latitude,item.longitude));
        });

        var map = new AMap.Map("container", {
            resizeEnable: true,
            center: [114.93 , 25.83],
            zoom: 14
        });

        // 坐标转换
        AMap.convertFrom(path, 'gps', function (status, result) {
            // 坐标转换
            AMap.convertFrom(path, 'gps', function (status, result) {
                if (result.info === 'ok') {
                    var path2 = result.locations;
                    polyline = new AMap.Polyline({
                        path: path2,
                        borderWeight: 2, // 线条宽度，默认为 1
                        strokeColor: "orange", // 线条颜色
                        lineJoin: 'round' // 折线拐点连接处样式
                    });
                    map.setFitView([ polyline ]);
                    map.add(polyline);
                    text2 = new AMap.Text({
                        position: result.locations[0],
                        offset: new AMap.Pixel(-20, -20)
                    })
                }
            });
        });
    }
</script>
</body>
</html>