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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/modifypsw.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>修改密码</title>
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
                <li style="padding-left: 250px"><a href="${APP_PATH}/index.jsp"><span class="glyphicon glyphicon-home"></span>首页</a>
                </li>
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
                                <li><a href="${APP_PATH}/jsp/myfavoritebaby.jsp">收藏的宝贝</a></li>
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
                                        <li><a href="${APP_PATH}/jsp/selledbaby.jsp">已卖出的宝贝</a></li>
                                        <li><a href="${APP_PATH}/jsp/sellingbaby.jsp">出售中的宝贝</a></li>
                                        <li><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></li>
                                        <li><a href="">下架宝贝</a></li>
                                </c:if>
                        </ul>
                </li>
                <li class="dropdown active">
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
                <li><h3>个人中心</h3></li>
                <li>
                        <div style="margin-left: 100px;"><a href="${APP_PATH}/jsp/personcenter.jsp">收货地址</a></div>
                </li>
                <li>
                        <div style="margin-left: 50px; background-color: rgba(0,0,0,0.1); "><a
                                href="${APP_PATH}/jsp/modifypsw.jsp">修改密码</a></div>
                </li>
        </ul>
</div>
<div class="box">
        <div class="col-md-2"></div>
        <div class="col-md-8" style="border: 1px solid black;height: 500px;">
                <div class="row">
                        <div class="modify"><span>密码修改</span></div>
                </div>
                <form>
                <div class="row" style="padding-top: 30px;">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                                <div class="input-group" style="height: 50px;">
                                        <span class="input-group-addon" style="background-color: #95BDFF;color: white;">用户名：</span>
                                        <input id="username" value="${sessionScope.currentUser.username}" disabled type="text" class="form-control" style="height: 50px;background-color: rgba(208,208,208,0.4);">
                                </div>
                        </div>
                        <div class="col-md-2"></div>
                </div>
                <div class="row" style="padding-top: 20px;">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                                <div class="input-group" style="height: 50px;">
                                        <span class="input-group-addon" style="background-color: #95BDFF;color: white;">旧密码：</span>
                                        <input id="old-password" type="text" class="form-control"
                                               style="height: 50px;background-color: rgba(208,208,208,0.4);">
                                </div>
                        </div>
                        <div class="col-md-2"></div>
                </div>
                <div class="row" style="padding-top: 20px;">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                                <div class="input-group" style="height: 50px;">
                                        <span class="input-group-addon" style="background-color: #95BDFF;color: white;">新密码：</span>
                                        <input id="new-password" type="text" class="form-control"
                                               style="height: 50px;background-color: rgba(208,208,208,0.4);">
                                </div>
                        </div>
                        <div class="col-md-2"></div>
                </div>
                <div class="row" style="padding-top: 20px;">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                                <div class="input-group" style="height: 50px;">
                                        <span class="input-group-addon" style="background-color: #95BDFF;color: white;">确认密码：</span>
                                        <input id="password-confirm" type="text" class="form-control"
                                               style="height: 50px;background-color: rgba(208,208,208,0.4);">
                                </div>
                        </div>
                        <div class="col-md-2"></div>
                </div>
                <div class="row" style="padding-top: 50px;">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                                <button id="change_pwd_btn" class="btn btn-default" style="color: #95BDFF;background-color: rgba(208,208,208,0.4);"> 确认 </button>
                                <input type="reset" class="btn btn-default" style="width:70px;color: #95BDFF;background-color: rgba(208,208,208,0.4);margin-left: 335px;">
                        </div>
                </div>
                </form>
        </div>
        <div class="col-md-2"></div>
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
<script type="text/javascript">

        $("#change_pwd_btn").click(function () {
                var username = "${sessionScope.currentUser.username}";
                var oldPassword = $("#old-password").val();
                var newPassword = $("#new-password").val();
                var passwordConfirm = $("#password-confirm").val();
                console.log(username+":"+oldPassword+":"+newPassword+":"+passwordConfirm);
                if(parseInt(newPassword) != parseInt(passwordConfirm)){
                    alert("确认密码不正确");
                    //window.location.reload();
                    return;
                }
                if(parseInt(oldPassword) == parseInt(newPassword) || newPassword.length < 6){
                    alert("新密码不可与旧密码相等，且不能小于六位数");
                    return;
                }
            changePassword(username,oldPassword,newPassword);
        });

        function changePassword(username,oldPassword,newPassword) {
            $.ajax({
                url:"${APP_PATH}/user/reset_password.do",
                type:"post",
                data:"username="+username+"&passwordOld="+oldPassword+"&passwordNew="+newPassword,
                success:function(result){
                    console.log(result);
                    if(result.status == 0){
                        alert("修改密码成功，请重新登录");
                        window.location.href = "${APP_PATH}/jsp/login.jsp";
                    }else if(result.status == 1){
                        alert("失败："+result.msg);
                    }
                }
            });
        }
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
