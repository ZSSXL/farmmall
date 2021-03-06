<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
        <%
                pageContext.setAttribute("APP_PATH", request.getContextPath());
        %>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maxium-scale=1,user-scalable=no">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/orderdetails1.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css"/>
        <script src="https://webapi.amap.com/maps?v=1.4.15&key=7b4b13243e3bb0c9cfef5cee91268600&plugin=AMap.PolyEditor"></script>
        <script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>订单详情</title>
</head>
<body>
<div>
        <ul class="nav nav-tabs">
                <c:if test="${sessionScope.currentUser == null}">
                        <li style="padding-left: 100px"><a href="${APP_PATH}/jsp/login.jsp">亲，请登录</a></li>
                        <li style="padding-left: 100px"><a href="${APP_PATH}/jsp/register.jsp">免费注册</a></li>
                </c:if>
                <c:if test="${sessionScope.currentUser != null}">
                        <li style="padding-left: 100px">
                                <div style="margin-top: 7px;"><span
                                        class="label label-info">欢迎:${sessionScope.currentUser.username}</span></div>
                        </li>
                        <li>
                                <div style="margin-top: 7px;margin-left: 10px;"><span class="label label-info"
                                                                                      id="logout"
                                                                                      style="cursor: pointer;">退出登录</span>
                                </div>
                        </li>
                </c:if>
                <li style="padding-left: 250px">
                        <a href="${APP_PATH}/index.jsp"><span class="glyphicon glyphicon-home"></span>首页</a>
                </li>
                <li class="dropdown active"><a href="###" class="dropdown" data-toggle="dropdown"> <span
                        class="glyphicon glyphicon-th-list"></span>我的订单 <span class="caret"></span> </a>
                        <ul class="dropdown-menu">
                                <li><a href="${APP_PATH}/jsp/myorders.jsp">全部订单</a></li>
                                <li class="active"><a href="${APP_PATH}/jsp/goods_received.jsp">待收货</a></li>
                        </ul>
                </li>
                <li><a href="${APP_PATH}/jsp/shoppingcart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a>
                </li>
                <li class="dropdown"><a href="###" class="dropdown" data-toggle="dropdown"> <span
                        class="glyphicon glyphicon-star"></span>收藏夹 <span class="caret"></span> </a>
                        <ul class="dropdown-menu">
                                <li><a href="${APP_PATH}/jsp/myfavoritebaby.jsp">收藏的宝贝</a></li>
                        </ul>
                </li>
                <li class="dropdown"><a href="###" class="dropdown" data-toggle="dropdown">卖家中心 <span
                        class="caret"></span> </a>
                        <ul class="dropdown-menu">
                                <c:if test="${sessionScope.currentUser.role == 2 || sessionScope.currentUser == null}">
                                        <li><a href="${APP_PATH}/jsp/free_opening.jsp">免费开店</a></li>
                                </c:if>
                                <c:if test="${sessionScope.currentUser.role == 1}">
                                        <li><a href="${APP_PATH}/jsp/selledbaby.jsp">已卖出的宝贝</a></li>
                                        <li><a href="${APP_PATH}/jsp/sellingbaby.jsp">出售中的宝贝</a></li>
                                        <li><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></li>
                                        <li><a href="">下架宝贝</a></li>
                                </c:if>
                        </ul>
                </li>
                <li class="dropdown">
                        <a href="###" class="dropdown" data-toggle="dropdown">
                                <span class="glyphicon glyphicon-user"></span>个人中心
                                <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                                <li><a href="${APP_PATH}/jsp/personcenter.jsp">地址管理</a></li>
                                <li class="active"><a href="${APP_PATH}/jsp/modifypsw.jsp">修改密码</a></li>
                        </ul>
                </li>
        </ul>
</div>
<div class="box">
        <div>
                <h4>您的位置：我的订单&gt;待收货&gt;订单详情</h4>
        </div>
        <div class="row col-md-12">
                <h2>订单信息</h2>
                <div class="input-group col-md-12">
                        <span class="input-group-addon col-md-3 glyphicon glyphicon-user" id="basic-addon1"> 收货人</span>
                        <input type="text" value="${sessionScope.currentOrder.receiverName}" class="form-control col-md-6"
                               placeholder="暂无信息" aria-describedby="basic-addon1" disabled>
                </div>
                <div class="input-group col-md-12">
                        <span class="input-group-addon col-md-3 glyphicon glyphicon-phone" id="basic-addon2"> 电话</span>
                        <input type="text" value="${sessionScope.currentOrder.receiverPhone}" class="form-control col-md-6"
                               placeholder="暂无信息" aria-describedby="basic-addon1"  disabled>
                </div>
                <div class="input-group col-md-12">
                        <span class="input-group-addon col-md-3 glyphicon glyphicon-globe" id="basic-addon3"> 收货地址</span>
                        <input type="text"
                               value="${sessionScope.currentOrder.receiverProvince} ${sessionScope.currentOrder.receiverCity} ${sessionScope.currentOrder.receiverDistrict} ${sessionScope.currentOrder.receiverAddress}"
                               class="form-control col-md-6" placeholder="暂无信息" aria-describedby="basic-addon1" disabled>
                </div>
                <div class="input-group col-md-12">
                        <span class="input-group-addon col-md-3 glyphicon glyphicon-barcode" id="basic-addon4"> 订单号</span>
                        <input type="text" value="${sessionScope.currentOrder.orderNo}" class="form-control col-md-6"
                               placeholder="暂无信息" aria-describedby="basic-addon1" disabled>
                </div>
                <br>
                <hr size="2px;">
        </div>
        <table class="table table-striped">
                <tbody>
                <tr>
                        <td><h3><strong>商品信息：</strong></h3></td>
                </tr>
                </tbody>
        </table>
        <table class="table table-striped table-bordered goods">
                <thead>
                <tr>
                        <th>商品</th>
                        <th>单价</th>
                        <th>数量</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                        <td style="width: 400px;">
                                <img src="${sessionScope.currentOrder.productImage}" alt="商品照片" id="productImage">
                                <a id="show_product" href="#" style="width: 50px;margin-left: 20px;" id="productName"
                                   product_id="${sessionScope.currentOrder.productId}">${sessionScope.currentOrder.productName}</a>
                        </td>
                        <td><span id="price">${sessionScope.currentOrder.currentUnitPrice}</span></td>
                        <td><span id="quantity">${sessionScope.currentOrder.quantity}</span></td>
                </tr>
                </tbody>
        </table>
        <%--<h3><strong>生长环境温湿度：</strong></h3>--%>
        <h3><strong>保鲜箱温湿度：</strong></h3>
        <div class="freshbox">
                <div class="row" style="margin-bottom: 10px;">
                        <div class="col-md-12">
                                <button class="btn btn-danger" type="button" id="showEchar">刷新折线图</button>
                        </div>
                </div>
                <div class="row">
                        <div class="col-md-12" style="border: 1px solid rgba(167,167,167,0.6);height: 400px;"
                             id="containers"></div>
                </div>
        </div>
        <%--<h3><strong>牲畜位置活动信息：</strong></h3>--%>
        <h3><strong>物流信息：</strong></h3>
        <table class="table table-striped">
                <tbody>
                <tr>
                        <td>
                                <p class="glyphicon glyphicon-send"> <strong>物流:</strong> 智能冷链物流</p>
                        </td>
                </tr>
                <tr>
                        <td>
                                <p class=" glyphicon glyphicon-barcode"> <strong>物流单号:</strong> ${sessionScope.currentOrder.orderNo}</p>
                        </td>
                </tr>
                <tr>
                        <td>
                                <h2 class="glyphicon glyphicon-circle-arrow-down"> 物流跟踪</h2>
                                <div id="map"></div>
                        </td>
                </tr>
                </tbody>
        </table>
</div>
<footer id="footer">
        <div class="top">
                <div class="block left">
                        <h2>购物指南</h2>
                        <hr>
                        <ul>
                                <li>上门自提</li>
                                <li>在线支付</li>
                                <li>退款说明</li>
                                <li>联系客服</li>
                        </ul>
                </div>
                <div class="block center">
                        <h2>配送方式</h2>
                        <hr>
                        <ul>
                                <li>货到付款</li>
                                <li>价格保护</li>
                                <li>延保服务</li>
                        </ul>
                </div>
                <div class="block right">
                        <h2>特色服务</h2>
                        <hr>
                        <ul>
                                <li>会员介绍</li>
                                <li>配送服务查询</li>
                                <li>取消订单</li>
                        </ul>
                </div>
        </div>
        <div class="bottom">Copyright © 版权所有</div>
</footer>
</body>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script src="https://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById("containers"));

    myChart.setOption({
        title: {
            text: '保鲜箱湿度与温度'
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
        url: "${APP_PATH}/echar.do",
        type: "get",
        data: "boxId=" +${sessionScope.currentOrder.boxId},
        /*url:"${APP_PATH}/enviroment.do",
        type:"get",
        data:"annimalId="+532135131,*/
        success: function (data) {
            console.log(data);
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
            url: "${APP_PATH}/show_logistic.do",
            type: "get",
            data: "boxId=" +${sessionScope.currentOrder.boxId},
            success: function (result) {
                console.log(result);
                analisisResult(result);
            }
        });
    });

    // 地图显示 1
    function analisisResult(result) {
        var logisticList = result.data;
        $.each(logisticList, function (index, item) {
            path.push(new AMap.LngLat(item.latitude, item.longitude));
        })

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
<script type="text/javascript">
    $(document).on("click", "#show_product", function () {
        var productId = $(this).attr("product_id");
        $.ajax({
            url: "${APP_PATH}/product/show_product_by_id.do",
            type: "get",
            data: "productId=" + productId,
            success: function (result) {
                if (result.status == 0) {
                    window.location.href = "xiangqing.jsp";
                } else {
                    alert(result.msg);
                }
            }
        });
    });

    $("#logout").click(function () {
        $.ajax({
            url: "${APP_PATH}/user/logout.do",
            type: "post",
            success: function (result) {
                if (result.status == 0) {
                    window.location.href = "${APP_PATH}/index.jsp";
                } else {
                    alert(result.msg);
                }
            }
        });
    });

    // todo 获取boxId ${sessionScope.currentOrder.boxId}
</script>
<script>
    $("#showEchar").click(
        function () {
            window.location.reload(true);
        }
    );
</script>
</html>
