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
                <li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">已卖出的宝贝</a></div>
                </li>
                <li><div style="margin-left: 50px;background-color: rgba(0,0,0,0.1); "><a href="${APP_PATH}/jsp/sellingbaby.jsp">出售中的宝贝</a></div></li>
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
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">已卖出的宝贝</a></div>
                </div>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img3.png" alt="">&nbsp;</span><a href="#"><strong>物流管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/send.jsp">发货</a></div>
                </div>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img4.png" alt="">&nbsp;</span><a href="#"><strong>宝贝管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_off.jsp">下架宝贝</a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/sellingbaby.jsp" style="color: rgba(247,91,93,1.00);">出售中的宝贝</a></div>
                </div>
        </div>
        <div class="col-md-10 middle">
                <div class="row">
                        <div class="seller">我是卖家&nbsp;&gt;&nbsp;宝贝管理&nbsp;&gt;&nbsp;出售中的宝贝</div>
                </div>
                <div class="row" style="height: 180px;">
                        <hr size="1px">
                        <div class="col-md-4">
                                <form class="form-horizontal">
                                        <div class="form-group">
                                                <label class="col-md-5 control-label">商品ID：</label>
                                                <div class="col-md-7"><input class="form-control"></div>
                                        </div>
                                        <div class="form-group">
                                                <label class="col-md-5 control-label">宝贝标题：</label>
                                                <div class="col-md-7"><input class="form-control"></div>
                                        </div>
                                </form>
                        </div>
                        <div class="col-md-4">
                                <form class="form-horizontal">
                                        <div class="form-group">
                                                <label class="col-md-5 control-label">店铺分类：</label>
                                                <div class="col-md-7"><input class="form-control"></div>
                                        </div>
                                        <div class="form-group">
                                                <label class="col-md-5 control-label">宝贝类型：</label>
                                                <div class="col-md-7"><input class="form-control"></div>
                                        </div>
                                </form>
                        </div>
                        <div class="col-md-4">
                                <form class="form-horizontal">
                                        <div class="form-group">
                                                <label class="col-md-5 control-label">价格：</label>
                                                <div class="col-md-7"><input class="form-control"></div>
                                        </div>
                                        <div class="form-group">
                                                <label class="col-md-5 control-label">销量：</label>
                                                <div class="col-md-7"><input class="form-control"></div>
                                        </div>
                                </form>
                        </div>
                        <button class="btn btn-danger" style="margin-left: 50px;">查询</button>
                        <button class="btn btn-default" style="margin-left: 47px;">重置</button>
                </div>
                <hr size="1px">
                <div class="row">
                        <table class="table table-striped text-center">
                                <thead>
                                <tr>
                                        <th>商品名称</th>
                                        <th>价格</th>
                                        <th>库存(/kg)</th>
                                        <th>销量</th>
                                        <th>创建时间</th>
                                        <th>发布时间</th>
                                        <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                        <td><div><a href="#">就给个合计合计将黑寡妇黑寡妇感觉好工费合计高呼口</a></div></td>
                                        <td>45.50</td>
                                        <td>50</td>
                                        <td>20</td>
                                        <td>2019.01.09</td>
                                        <td>2019.06.07</td>
                                        <td><button class="btn btn-default">删除</button></td>
                                </tr>
                                <tr>
                                        <td><div><a href="#">就给个合计合计将黑寡妇黑寡妇感觉好工费合计高呼口</a></div></td>
                                        <td>45.50</td>
                                        <td>50</td>
                                        <td>20</td>
                                        <td>2019.01.09</td>
                                        <td>2019.06.07</td>
                                        <td><button class="btn btn-default">删除</button></td>
                                </tr>
                                <tr>
                                        <td><div><a href="#">就给个合计合计将黑寡妇黑寡妇感觉好工费合计高呼口</a></div></td>
                                        <td>45.50</td>
                                        <td>50</td>
                                        <td>20</td>
                                        <td>2019.01.09</td>
                                        <td>2019.06.07</td>
                                        <td><button class="btn btn-default">删除</button></td>
                                </tr>
                                <tr>
                                        <td><div><a href="#">就给个合计合计将黑寡妇黑寡妇感觉好工费合计高呼口</a></div></td>
                                        <td>45.50</td>
                                        <td>50</td>
                                        <td>20</td>
                                        <td>2019.01.09</td>
                                        <td>2019.06.07</td>
                                        <td><button class="btn btn-default">删除</button></td>
                                </tr>
                                </tbody>
                        </table>
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

