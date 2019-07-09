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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/myfavoritebaby.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>收藏的宝贝</title>
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
                        <span class="glyphicon glyphicon-star"></span>收藏夹
                        <span class="caret"></span>
                </a>
                        <ul class="dropdown-menu">
                                <li class="active"><a href="${APP_PATH}/jsp/myfavoritebaby.jsp">收藏的宝贝</a></li>
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
                <li><h3>收藏夹</h3></li>
                <li><div style="margin-left: 100px; background-color: rgba(0,0,0,0.1); "><a href="${APP_PATH}/jsp/myfavoritebaby.jsp">宝贝收藏</a></div></li>
                <%--<li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/myfavoritestore.jsp">店铺收藏</a></div></li>--%>
        </ul>
</div>
<div class="box">
        <div class="container">
                <div class="row" style="height: 30px;">
                        <div class="col-md-1"></div>
                        <div class="col-md-6"><h5>全部宝贝</h5></div>
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
                <div id="row1" clas="row" style="height: 180px; ">
                </div>
                <div id="row2" clas="row" style="height: 180px;">
                </div>
                <div id="row3" clas="row" style="height: 180px;">
                </div>
                <div id="row4" clas="row" style="height: 180px;">
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
        $(function () {
           var pn = 1;
           $.ajax({
               url:"${APP_PATH}/product/show_collection.do",
               type:"get",
               data:"pn="+pn,
               success:function (result) {
                    // console.log(result);
                   if(result.status == 0){
                       if(result.data.list.length == 0){
                           alert("当前没有搜藏的商品，快去首页找吧");
                           window.location.href = "${APP_PATH}/index.jsp";
                       }
                       parsingData(result)
                   }else if(result.status == 10){
                       alert(result.msg);
                       window.location.href = "${APP_PATH}/index.jsp";
                   }else if(result.status == 1){
                       alert(result.msg);

                   }

               }
           })
        });

        /**
         * 解析数据
         */
        function parsingData(result) {
            $("#row1").empty();
            $("#row2").empty();
            $("#row3").empty();
            $("#row4").empty();
            var count = 1;
            var collectionList = result.data.list;
            $.each(collectionList,function (index,collectionItem) {
                var imageLine = $("<a href='#'></a>").append($("<img alt='#' class='img-thumbnail'/>").attr("src",collectionItem.mainImage));
                var enterShopLine = $("<div></div>").append($("<a href='#' class='show_product'></a>").append(collectionItem.name).attr("product_id",collectionItem.id))
                    .append($("<button class='btn btn-danger' style='float: right;'>删除宝贝</button>").attr("product_id",collectionItem.id));
                var detatil = $("<a href='#'></a>").append(collectionItem.detail);
                var detailLine = $("<div></div>").append(detatil);
                var priceLine = $("<div></div>").append($("<strong>￥</strong>").append(collectionItem.price));
                var fatherLine = $("<div class='col-md-3'></div>")
                    .append(imageLine)
                    .append(enterShopLine)
                    .append(detailLine)
                    .append(priceLine);
                if(count >= 1 && count <= 4){
                     fatherLine.appendTo("#row1");
                }else if(count > 4 && count <= 8){
                    fatherLine.appendTo("#row2");
                }else if(count > 8 && count <= 12){
                    fatherLine.appendTo("#row3");
                }else if(count > 12 && count <= 16){
                    fatherLine.appendTo("#row4");
                }
                count++;
            })
        }
        
        $(document).on("click",".btn-danger",function () {
           var productId = $(this).attr("product_id");
           $.ajax({
               url:"${APP_PATH}/product/delete_collection.do",
               type:"post",
               data:"productId="+productId,
               success:function (result) {
                   // console.log(result);
                   if(result.status == 0){
                       alert(result.msg);
                       window.location.reload();
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
