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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/myfavoritestore.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>收藏的店铺</title>
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
                <li class="dropdown active"><a href="###" class="dropdown" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-star active"></span>收藏夹
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
                <li><h3>收藏夹</h3></li>
                <li><div style="margin-left: 100px; "><a href="${APP_PATH}/jsp/myfavoritebaby.jsp">宝贝收藏</a></div></li>
                <li><div style="margin-left: 50px;background-color: rgba(0,0,0,0.1);"><a href="${APP_PATH}/jsp/myfavoritestore.jsp">店铺收藏</a></div></li>
        </ul>
</div>
<div class="box">
        <div class="container">
                <div class="row" style="height: 30px;">
                        <div class="col-md-1"></div>
                        <div class="col-md-6"><h5>全部店铺</h5></div>
                        <div class="col-md-4">
                                <div class="input-group" id="two" style="width: 400px;height: 30px; ">
                                        <input type="text" name="search" class="form-control" style=" border: 2px solid rgba(244,128,50,1.00)">
                                        <div class="input-group-btn">
                                                <button class="btn btn-danger">搜索</button>
                                        </div>
                                </div>
                        </div>
                        <div class="col-md-1"></div>
                </div>
                <hr size="1px">
                <div class="row">
                        <div class="col-md-3">
                                <div class="col-md-4">
                                        <img src="${APP_PATH}/static/images/myfavoritestore/img1.jpg" class="icons" alt="">
                                </div>
                                <div class="col-md-8">
                                        <h4><strong>XXX店铺</strong></h4>
                                        <h5>男孩别哭复检合格否更丰厚价格发货快</h5>
                                        <div class="des">
                                                <p>如实描述：<strong>4.47</strong></p>
                                                <p>服务态度：<strong>3.27</strong></p>
                                                <p>发货速度：<strong>4.56</strong></p>
                                        </div>
                                        <div><button class="btn btn-danger">删除店铺</button></div>
                                </div>
                        </div>
                        <div class="col-md-9 disp">
                                <div class="row" style="height: 20px;"></div>
                                <div class="row">
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/4 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥40.00/kg</strong></h6>
                                        </div>
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/3 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥60.00/kg</strong></h6>
                                        </div>
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/2 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥30.00/kg</strong></h6>
                                        </div>
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/1 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥40.00/kg</strong></h6>
                                        </div>
                                </div>
                        </div>
                </div>
                <hr size="1px">
                <div class="row">
                        <div class="col-md-3">
                                <div class="col-md-4">
                                        <img src="${APP_PATH}/static/images/myfavoritestore/img2.jpg" class="icons" alt="">
                                </div>
                                <div class="col-md-8">
                                        <h4><strong>XXX店铺</strong></h4>
                                        <h5>男孩别哭复检合格否更丰厚价格发货快</h5>
                                        <div class="des">
                                                <p>如实描述：<strong>4.47</strong></p>
                                                <p>服务态度：<strong>3.27</strong></p>
                                                <p>发货速度：<strong>4.56</strong></p>
                                        </div>
                                        <div><button class="btn btn-danger">删除店铺</button></div>
                                </div>
                        </div>
                        <div class="col-md-9 disp">
                                <div class="row" style="height: 20px;"></div>
                                <div class="row">
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/2 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥40.00/kg</strong></h6>
                                        </div>
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/3 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥60.00/kg</strong></h6>
                                        </div>
                                        <div class="col-md-3">
                                        </div>
                                        <div class="col-md-3">
                                        </div>
                                </div>
                        </div>
                </div>
                <hr size="1px">
                <div class="row">
                        <div class="col-md-3">
                                <div class="col-md-4">
                                        <img src="${APP_PATH}/static/images/myfavoritestore/img3.jpg" class="icons" alt="">
                                </div>
                                <div class="col-md-8">
                                        <h4><strong>XXX店铺</strong></h4>
                                        <h5>男孩别哭复检合格否更丰厚价格发货快</h5>
                                        <div class="des">
                                                <p>如实描述：<strong>4.47</strong></p>
                                                <p>服务态度：<strong>3.27</strong></p>
                                                <p>发货速度：<strong>4.56</strong></p>
                                        </div>
                                        <div><button class="btn btn-danger">删除店铺</button></div>
                                </div>
                        </div>
                        <div class="col-md-9 disp">
                                <div class="row" style="height: 20px;"></div>
                                <div class="row">
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/3 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥40.00/kg</strong></h6>
                                        </div>
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/1 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥60.00/kg</strong></h6>
                                        </div>
                                        <div class="col-md-3">
                                                <a href="#"><img src="${APP_PATH}/static/images/first-page/2 figure.jpg" alt="" class="img-thumbnail"></a>
                                                <h6><strong>￥30.00/kg</strong></h6>
                                        </div>
                                        <div class="col-md-3">
                                        </div>
                                </div>
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
