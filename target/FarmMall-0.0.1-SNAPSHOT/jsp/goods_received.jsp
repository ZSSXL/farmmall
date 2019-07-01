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
	<link  rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css">
	<link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/myorders.css">
	<link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
	<script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
	<title>待收货</title>
</head>
<body>
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
			<li  style="padding-left: 250px" >
				<a href="${APP_PATH}/index.jsp"><span class="glyphicon glyphicon-home"></span>首页</a>
			</li>
			<li class="dropdown active"><a href="###" class="dropdown" data-toggle="dropdown">
				<span class="glyphicon glyphicon-th-list"></span>我的订单
				<span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${APP_PATH}/jsp/myorders.jsp">全部订单</a></li>
					<li class="active"><a href="${APP_PATH}/jsp/goods_received.jsp">待收货</a></li>
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
			<li class="dropdown">
				<a href="###" class="dropdown" data-toggle="dropdown">卖家中心
					<span class="caret"></span>
				</a>
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
	<div class="input-group" id="two" style="width: 400px;height: 30px; padding-right: 80px; padding-top: 20px; padding-bottom: 10px; float: right">
		   <input type="text" name="search" class="form-control" style=" border: 2px solid rgba(244,128,50,1.00)">
			<div class="input-group-btn">
			    <button class="btn btn-danger">搜索</button>
			</div>		
	</div>
	<hr size="2px">
	<div class="box">
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
			  <tbody style="height: 100px;line-height: 100px;" id="order_list">

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
	$(function () {
		var pn = 1;
		var status = 40;
		showOrderByStatus(status,pn);
	});

	function showOrderByStatus(status,pn){
		$.ajax({
			url:"${APP_PATH}/order/show_order.do",
			type:"GET",
			data:"pn="+pn+"&status="+status,
			success:function(result){
				showOrder(result);
			}
		});
	}

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
			var statusTd = $("<td style='line-height: 137px;'></td>").append("待收货");
			var orderDetailBtn = $("<td style='line-height: 137px;'></td>").append($("<a href='#' class='show_order_detail'>订单详情</a>").attr("order_no",orderItem.orderNo));

			$("<tr></tr>").append(imageTd)
				.append(productNameTd)
				.append(quantityTd)
				.append(priceTd)
				.append(totalPriceTd)
				.append(statusTd)
				.append(orderDetailBtn)
				.appendTo("#order_list");
		});
	}

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

	$(document).on("click",".show_order_detail",function () {
		var orderNo = $(this).attr("order_no");
		$.ajax({
			url: "${APP_PATH}/order/show_order_detail.do",
			type: "post",
			data:"orderNo="+orderNo,
			success: function (result) {
				console.log(result);
				if(result.status == 0){
					window.location.href = "${APP_PATH}/jsp/orderdetails1.jsp";
				}else{
					alert("哪里出错了");
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
