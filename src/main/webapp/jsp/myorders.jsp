<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
        <%
                pageContext.setAttribute("APP_PATH", request.getContextPath());
        %>
        <meta charset="utf-8">
        <title>农场品在线商城</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,maxium-scale=1,user-scalable=no">
        <link  rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/myorders.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>我的订单</title>
</head>

<body>
<div class="modal fade" tabindex="-1" role="dialog" id="payQrModal">
        <div class="modal-dialog" role="document">
                <div class="modal-content">
                        <div class="modal-header">
                                <button id="close_pay_qr_x" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">请用支付宝扫码支付</h4>
                        </div>
                        <div class="modal-body" style="text-align: center">
                                <img id="qr_img" style="width: 256px;height: 256px;"/>
                                <div>
                                        <div class="input-group input-group-lg">
                                                <span class="input-group-addon">
                                                        <strong id="totalPriceToPay"></strong>
                                                </span>
                                        </div>
                                </div>
                        </div>
                        <div class="modal-footer">
                                <button id="close_pay_qr" type="button" class="btn btn-info" data-dismiss="modal">关闭付款</button>
                        </div>
                </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div>
        <ul class="nav nav-tabs">
                <c:if test="${sessionScope.currentUser == null}">
                        <li style="padding-left: 100px"><a href="${APP_PATH}/jsp/login.jsp">亲，请登录</a></li>
                        <li><a href="${APP_PATH}/jsp/register.jsp">免费注册</a></li>
                </c:if>
                <c:if test="${sessionScope.currentUser != null}">
                        <li style="padding-left: 100px"><div style="margin-top: 7px;"><span  class="label label-info">欢迎:${sessionScope.currentUser.username}</span></div></li>
                        <li><div style="margin-top: 7px;margin-left: 10px;"><span  class="label label-info" id="logout" style="cursor: pointer;">退出登录</span></div></li>
                </c:if>
                <li  style="padding-left: 250px" ><a href="${APP_PATH}/index.jsp"><span class="glyphicon glyphicon-home"></span>首页</a></li>
                <li class="dropdown"><a href="###" class="dropdown" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-th-list"></span>我的订单
                        <span class="caret"></span>
                </a>
                        <ul class="dropdown-menu">
                                <li class="active"><a href="${APP_PATH}/jsp/myorders.jsp">全部订单</a></li>
                                <li><a href="${APP_PATH}/jsp/goods_received.jsp">待收货</a></li>
                        </ul>
                </li>
                <li><a href="${APP_PATH}/jsp/shoppingcart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
                <li class="dropdown"><a href="###" class="dropdown" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-star"></span>收藏夹
                        <span class="caret"></span>
                </a>
                        <ul class="dropdown-menu">
                                <li><a href="#">收藏的宝贝</a></li>
                        </ul>
                </li>
                <li class="dropdown"><a href="###" class="dropdown" data-toggle="dropdown">卖家中心
                        <span class="caret"></span>
                </a>
                        <ul class="dropdown-menu">
                                <c:if test="${sessionScope.currentUser.role == 2 || sessionScope.currentUser == null}">
                                        <li><a href="${APP_PATH}/jsp/free_opening.jsp">免费开店</a></li>
                                </c:if>
                                <c:if test="${sessionScope.currentUser.role == 1}">
                                        <li><a href="${APP_PATH}/jsp/selledbaby.jsp">订单管理</a></li>
                                        <li><a href="${APP_PATH}/jsp/send.jsp">发货</a></li>
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
                                <li><a href="${APP_PATH}/jsp/modifypsw.jsp">修改密码</a></li>
                        </ul>
                </li>
        </ul>
</div>
<div class="input-group" id="two" style="width: 400px;height: 30px; padding-right: 80px; padding-top: 20px; padding-bottom: 10px; float: right">
        <input type="text" name="search" class="form-control" style=" border: 2px solid rgba(244,128,50,1.00)">
        <div class="input-group-btn">
                <button class="btn btn-danger">搜索</button>
        </div>
</div>
<hr size="2px">
<div class="box">
        <button class="btn btn-info" id="showAll">全部订单</button>
        <button class="btn btn-info" id="un_pay">未付款</button>
        <button class="btn btn-info" id="payed">已支付</button>
        <button class="btn btn-info" id="sended">已发货</button>
        <button class="btn btn-info" id="ended">交易完成</button>
        <table class="table table-bordered text-center">
                <thead>
                <tr>
                        <th>商品图片</th>
                        <th>商品链接</th>
                        <th>数量</th>
                        <th>单价(/kg)</th>
                        <th>小计</th>
                        <th>订单状态</th>
                        <th>操作</th>
                </tr>
                </thead>
                <tbody style="height: 100px;line-height: 100px;" id="orderList">
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
        <div class="bottom">Copyright ©  版权所有</div>
</footer>
<script type="text/javascript">

        var globalOrder = 1;

        var interval;

        $("#un_pay").click(function () {
                var status = 10;
                var pn = 1;
            showOrderByStatus(status,pn);
        });
        $("#payed").click(function () {
            var status = 20;
            var pn = 1;
            showOrderByStatus(status,pn);
        });
        $("#sended").click(function () {
            var status = 40;
            var pn = 1;
            showOrderByStatus(status,pn);
        });
        $("#ended").click(function () {
            var status = 50;
            var pn = 1;
            showOrderByStatus(status,pn);
        });
        $("#showAll").click(function () {
            var status = 0;
            var pn = 1;
            showOrderByStatus(status,pn);
        });


        function showOrderByStatus(status,pn){
            $.ajax({
                url:"${APP_PATH}/order/show_order.do",
                type:"GET",
                data:"pn="+pn+"&status="+status,
                success:function(result){
                    // console.log(result);
                    showOrder(result);
                }
            });
        }

        $(document).on("click",".pay-btn",function () {
            globalOrder = $(this).prev("button").attr("order_no");
            var totalPrice = $(this).parents("tr").find("td:eq(4)").text();
            $("#totalPriceToPay").html(totalPrice+" 元");
            $.ajax({
                url:"${APP_PATH}/order/pay.do",
                type:"GET",
                data:"orderNo="+globalOrder,
                success:function(result){
                    // console.log(result);
                    analysisResult(result);
                }
            });
        });

        function analysisResult(result){
            $("#qr_img").attr("src",result.data.qrUrl);
            $("#payQrModal").modal("show");
            // 轮询查询订单状态
            interval = setInterval("getResult()",2000);
        }

        function getResult(){
            $.ajax({
                url:"${APP_PATH}/order/query_order_pay_status.do",
                async:true,
                type:"POST",
                data:"orderNo="+globalOrder,
                success:function (result) {
                    // console.log(result);
                    if(result.data == true){
                        alert("付款成功");
                        window.location.reload();
                    }
                }
            });
        }

        $("#close_pay_qr").click(function () {
            clearInterval(interval);
        });

        $("#close_pay_qr_x").click(function () {
            clearInterval(interval);
        });

        $(function () {
            var pn = 1;
            var status = 0;
            showOrderByStatus(status,pn);
        });

        function showOrder(result){
            $("#orderList").empty();
            var orderList = result.data.list;
            $.each(orderList,function (index,orderItem) {
                var img = $("<img alt='#' class='img-thumbnail' style='width:120px;height: 140px;'>").attr("src",orderItem.productImage);
                var imageTd = $("<td style='line-height: 137px;'></td>").append($("<div></div>").append(img));
                var productNameTd = $("<td style='line-height: 137px;'></td>").append($("<div></div>").append($("<a href='#' class='show_product'></a>").append(orderItem.productName).attr("product_id",orderItem.productId)));
                var quantityTd = $("<td style='line-height: 137px;'>数量:</td>").append(orderItem.quantity);
                var priceTd = $("<td style='line-height: 137px;'>单价:</td>").append(orderItem.currentUnitPrice);
                var totalPriceTd = $("<td style='line-height: 137px;'>小记:</td>").append(orderItem.totalPrice);
                var statusTd = $("<td style='line-height: 137px;'></td>");
                if(orderItem.status == 10){
                    statusTd.append("未付款");
                }else if(orderItem.status == 20){
                    statusTd.append("已付款");
                }else if(orderItem.status == 40){
                    statusTd.append("已发货");
                }else if(orderItem.status == 50){
                    statusTd.append("交易完成");
                }else if(orderItem.status == 60){
                    statusTd.append("交易关闭");
                }

                var deleteTd = $("<td style='line-height: 137px;'></td>").append($("<button class='btn btn-danger'>删除</button>").attr("order_no",orderItem.orderNo));
                if(orderItem.status == 10){
                    var payBtn = $("<button class='btn btn-success pay-btn'>去付款</button>");
                    deleteTd.append(payBtn);
                }

                $("<tr></tr>").append(imageTd)
                    .append(productNameTd)
                    .append(quantityTd)
                    .append(priceTd)
                    .append(totalPriceTd)
                    .append(statusTd)
                    .append(deleteTd)
                    .appendTo("#orderList");
            });
        }

        // 删除购物车订单
        $(document).on("click",".btn-danger",function () {
            var orderNo = $(this).attr("order_no");
            //alert(orderNo);
            $.ajax({
                url:"${APP_PATH}/order/delete.do",
                type:"post",
                data:"orderNo="+orderNo,
                dataType:"json",
                success:function(result){
                    console.log(result);
                    //showOrder(result);
                    if(result.status == 0){
                        alert(result.msg);
                        window.location.reload();
                    }else{
                        alert(result.msg);
                    }
                }
            });
        });
        
        $(document).on("click",".show_product",function () {
            var productId = $(this).attr("product_id");
            $.ajax({
                url: "${APP_PATH}/product/show_product_by_id.do",
                type: "get",
                data:"productId="+productId,
                success: function (result) {
                    if(result.status == 0){
                        window.location.href = "xiangqing.jsp";
                    }else{
                        alert(result.msg);
                    }
                }
            });
        });

        $("#logout").click(function () {
            $.ajax({
                url:"${APP_PATH}/user/logout.do",
                type:"post",
                success:function (result) {
                    if(result.status == 0){
                        window.location.href = "${APP_PATH}/index.jsp";
                    }else{
                        alert(result.msg);
                    }
                }
            });
        });

</script>
</body>
</html>

