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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/free_opening.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>我要开店</title>
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
<div class="head">
        <ul>
                <li><h3>卖家工作台</h3></li>
                <c:if test="${sessionScope.currentUser.role == 2}">
                        <li><div style="margin-left: 100px;background-color: rgba(0,0,0,0.1);" ><a href="${APP_PATH}/jsp/free_opening.jsp">免费开店</a></div></li>
                </c:if>
                <c:if test="${sessionScope.currentUser.role == 1}">
                        <li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">已卖出的宝贝</a></div>
                        </li>
                        <li><div style="margin-left: 50px; "><a href="${APP_PATH}/jsp/sellingbaby.jsp">出售中的宝贝</a></div></li>
                        <li><div style="margin-left: 50px; "><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></div></li>
                        <li><div style="margin-left: 50px; "><a href="#">下架宝贝</a></div></li>
                </c:if>
        </ul>
</div>
<div class="box">
        <div class="col-md-2 aside">
                <c:if test="${sessionScope.currentUser.role == 2}">
                        <div class="row" style="padding-left: 10px;padding-top: 20px;">
                                <div><span><img src="${APP_PATH}/static/images/free_opening/img1.png" alt="">&nbsp;</span><a href="#"><strong>店铺管理</strong></a></div>
                                <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/free_opening.jsp" style="color: rgba(247,91,93,1.00);">我要开店</a></div>
                        </div>
                </c:if>
                <c:if test="${sessionScope.currentUser.role == 1}">
                        <div class="row" style="padding-left: 10px;padding-top: 20px;">
                                <div><span><img src="${APP_PATH}/static/images/free_opening/img2.png" alt="">&nbsp;</span><a href="#"><strong>交易管理</strong></a></div>
                                <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">已卖出的宝贝</a></div>
                        </div>
                        <div class="row" style="padding-left: 10px;padding-top: 20px;">
                                <div><span><img src="${APP_PATH}/static/images/free_opening/img3.png" alt="">&nbsp;</span><a href="#"><strong>物流管理</strong></a></div>
                                <div style="padding-left: 28px;padding-top: 10px;"><a href="#">发货</a></div>
                                <div style="padding-left: 28px;padding-top: 10px;"><a href="#">物流工具</a></div>
                        </div>
                        <div class="row" style="padding-left: 10px;padding-top: 20px;">
                                <div><span><img src="${APP_PATH}/static/images/free_opening/img4.png" alt="">&nbsp;</span><a href="#"><strong>宝贝管理</strong></a></div>
                                <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></div>
                                <div style="padding-left: 28px;padding-top: 10px;"><a href="#">下架宝贝</a></div>
                                <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/sellingbaby.jsp">出售中的宝贝</a></div>
                        </div>
                </c:if>

        </div>
        <div class="col-md-10">
                <h3>申请开店认证</h3>
                <div class="prove row">
                        <form class="form-horizontal">
                                <div class="form-group">
                                        <label class="col-md-2 control-label">请输入用户名：</label>
                                        <div class="col-md-6">
                                                <input id="username" class="form-control" type="tel" placeholder="请输入你的用户名">
                                        </div>
                                </div>
                                <div class="form-group">
                                        <label class="col-md-2 control-label">请输入电话：</label>
                                        <div class="col-md-6">
                                                <input id="phone" class="form-control" type="tel" placeholder="请输入你的电话">
                                        </div>
                                </div>
                                <div class="form-group">
                                        <label class="col-md-2 control-label">您申请的店铺名：</label>
                                        <div class="col-md-6">
                                                <input id="shop_name" class="form-control" type="tel" placeholder="请输入店铺名">
                                        </div>
                                </div>
                        </form>
                        <div class="apply">
                                <button class="btn btn-danger" id="apply_open">提交认证申请</button>
                                <button class="btn btn-info" id="cancel" style="margin-left: 180px;">放弃申请</button>
                        </div>
                </div>
        </div>
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
        $("#apply_open").click(function () {
           var username = $("#username").val();
           var phone = $("#phone").val();
           var shopName = $("#shop_name").val();
           if(username.length == 0 && phone.length == 0){
               alert("用户名或电话不能为空!");
           }
           if( shopName.length == 0){
               alert("请输入店名");
           }

            $.ajax({
                url:"${APP_PATH}/user/apply_to_open_shop.do",
                type:"post",
                data:"username="+username+"&phone="+phone+"&shopName="+shopName,
                success:function (result) {
                    console.log(result);
                    if(result.status == 0){
                        alert(result.msg);
                        window.location.href == "${APP_PATH}/index.jsp";
                    }else{
                        alert(result.msg);
                    }
                }
            });
        });

        // 清空
        $("#cancel").click(function () {
            $("#username").val("");
            $("#phone").val("");
            $("#shop_name").val("");
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

        $(function () {
            if(${sessionScope.currentUser == null}){
                alert("请登录");
                window.location.href = "${APP_PATH}/index.jsp";
            }
        });
</script>
</body>
</html>


