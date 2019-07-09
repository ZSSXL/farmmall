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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/selledbaby.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>出售中的宝贝</title>
</head>

<body>
<div class="modal fade" tabindex="-1" role="dialog" id="send_modal">
        <div class="modal-dialog" role="document">
                <div class="modal-content">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title">填写发货信息</h4>
                        </div>
                        <div class="modal-body">
                                <div class="input-group">
                                        <span class="input-group-addon" id="basic-addon1">请输入物流箱设备号</span>
                                        <input type="text" id="box_id" class="form-control" id="order" placeholder="BOX-ID" aria-describedby="basic-addon1">
                                </div>
                        </div>
                        <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-primary" id="sure_send">确认发货</button>
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
                                <li><a href="${APP_PATH}/jsp/myorders.jsp">全部订单</a></li>
                                <li><a href="${APP_PATH}/jsp/goods_received.jsp">待收货</a></li>
                        </ul>
                </li>
                <li><a href="${APP_PATH}/jsp/shoppingcart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
                <li class="dropdown"><a href="###" class="dropdown" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-star"></span>收藏夹
                        <span class="caret"></span>
                </a>
                        <ul class="dropdown-menu">
                                <li><a href="${APP_PATH}/jsp/myfavoritebaby.jsp">收藏的宝贝</a></li>
                        </ul>
                </li>
                <li class="dropdown active"><a href="###" class="dropdown" data-toggle="dropdown">卖家中心
                        <span class="caret"></span>
                </a>
                        <ul class="dropdown-menu">
                                <c:if test="${sessionScope.currentUser.role == 2 || sessionScope.currentUser == null}">
                                        <li><a href="${APP_PATH}/jsp/free_opening.jsp">免费开店</a></li>
                                </c:if>
                                <c:if test="${sessionScope.currentUser.role == 1}">
                                        <li><a href="${APP_PATH}/jsp/selledbaby.jsp">已卖出的宝贝</a></li>
                                        <li class="active"><a href="${APP_PATH}/jsp/send.jsp">发货</a></li>
                                        <li><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></li>
                                        <li><a href="${APP_PATH}/jsp/baby_off.jsp">下架宝贝</a></li>
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
<div class="head">
        <ul>
                <li><h3>卖家工作台</h3></li>
                <c:if test="${sessionScope.currentUser.role == 2}">
                        <li><div style="margin-left: 100px; "><a href="${APP_PATH}/jsp/free_opening.jsp">免费开店</a></div></li>
                </c:if>
                <li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">订单查询</a></div>
                </li>
                <li><div style="margin-left: 50px;background-color: rgba(0,0,0,0.1); "><a href="${APP_PATH}/jsp/sellingbaby.jsp">发货</a></div></li>
                <li><div style="margin-left: 50px; "><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></div></li>
                <li><div style="margin-left: 50px; "><a href="${APP_PATH}/jsp/baby_off.jsp">下架宝贝</a></div></li>
        </ul>
</div>
<div class="box">
        <div class="col-md-2 aside">
                <c:if test="${sessionScope.currentUser.role == 2}">
                        <div class="row" style="padding-left: 10px;padding-top: 20px;">
                                <div><span><img src="${APP_PATH}/static/images/free_opening/img1.png" alt="">&nbsp;</span><a href="#"><strong>店铺管理</strong></a></div>
                                <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/free_opening.jsp">我要开店</a></div>
                        </div>
                </c:if>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img2.png" alt="">&nbsp;</span><a href="#"><strong>交易管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">订单查询</a></div>
                </div>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img3.png" alt="">&nbsp;</span><a href="#"><strong>物流管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/send.jsp"  style="color: rgba(247,91,93,1.00);">发货</a></div>
                </div>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img4.png" alt="">&nbsp;</span><a href="#"><strong>宝贝管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_off.jsp">下架宝贝</a></div>
                </div>
        </div>
        <div class="col-md-10 middle">
                <div class="row">
                        <div class="seller">我是卖家&nbsp;&gt;&nbsp;物流管理&nbsp;&gt;&nbsp;发货</div>
                </div>
                <hr size="1px">
                <div class="row">
                        <table class="table table-bordered text-center">
                                <thead>
                                <tr>
                                        <th>商品图片</th>
                                        <th>商品链接</th>
                                        <th>数量</th>
                                        <th>单价(/kg)</th>
                                        <th>买家</th>
                                        <th>交易状态</th>
                                        <th>实收款</th>
                                        <th>操作</th>
                                </tr>
                                </thead>
                                <tbody style="height: 100px;line-height: 100px;" id="buyyerOrderList">
                                </tbody>
                        </table>
                </div>
        </div>
<script type="text/javascript">

    $(document).on("click",".to-send",function () {
        var orderNo = $(this).attr("order_no");
        $("#sure_send").attr("order_no",orderNo);
        $("#send_modal").modal("show");
    });

    $("#sure_send").click(function () {
        var orderNo = $(this).attr("order_no");
        var boxId = $("#box_id").val();
        if(boxId.length > 11 || boxId.length == 0){
            alert("设备号输入错误");
            return;
        }
       $.ajax({
            url:"${APP_PATH}/order/send_order.do",
            type:"post",
            data:"orderNo="+orderNo+"&boxId="+boxId,
            success:function (result) {
                if(result.status == 0){
                    alert(result.msg);
                }else if(result.status == 1){
                    alert(result.msg);
                }
            }
        });
        $("#send_modal").modal("hide");
        window.location.reload();
    });
    
    $(function () {
        var status = 20;
        var pn = 1;
        showOrder(status,pn);
    });

    function analysisInfo(result) {
        $("#buyyerOrderList").empty();
        var orderList = result.data.list;
        $.each(orderList,function (index,orderItem) {
            var imageTd = $("<td></td>").append($("<div></div>").append($("<img alt='#' class='img-thumbnail' style='width: 120px;height: 140px'>").attr("src",orderItem.productImage)));
            var productNameTd = $("<td></td>").append($("<div></div>").append($("<a href='#' class='show_product_detail'></a>").append(orderItem.productName).attr("product_id",orderItem.productId)));
            var quantityTd = $("<td></td>").append(orderItem.quantity);
            var priceTd = $("<td></td>").append(orderItem.currentUnitPrice);
            var buyyerNameTd = $("<td></td>").append(orderItem.username);
            var totalPriceTd = $("<td></td>").append(orderItem.totalPrice);
            var btnTd = $("<td></td>").append($("<div style='width: 60px'></div>"));
            var toSendBtn = $("<button class='btn btn-info to-send' style='margin-left:5px'>去发货</button>").attr("order_no",orderItem.orderNo);
            var statusTd = $("<td></td>");

            statusTd.append("已付款，等待发货");
            btnTd.append(toSendBtn);

            $("<tr></tr>").append(imageTd)
                .append(productNameTd)
                .append(quantityTd)
                .append(priceTd)
                .append(buyyerNameTd)
                .append(statusTd)
                .append(totalPriceTd)
                .append(btnTd)
                .appendTo("#buyyerOrderList");
        });
    }

    function showOrder(status,pn){
        $.ajax({
            url:"${APP_PATH}/order/select_order_seller_id_type.do",
            type:"get",
            data:"status="+status+"&pn="+pn,
            success:function (result) {
                if(result.status == 0){
                    analysisInfo(result);
                }else if(result.status == 10){
                    alert(result.msg+"请登录");
                    window.location.href = "${APP_PATH}/jsp/login.jsp";
                }else if(result.status == 1){
                    alert(result.msg);
                }
            }
        })
    }

    $(document).on("click", ".show_product_detail", function () {
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

