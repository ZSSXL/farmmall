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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/xiangqing1.css">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css"/>
        <script src="https://webapi.amap.com/maps?v=1.4.15&key=7b4b13243e3bb0c9cfef5cee91268600&plugin=AMap.PolyEditor"></script>
        <script src="https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
        <title>商品详情页</title>
</head>
<body>
<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="enviroment_modal">
        <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                        <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title">生长环境详情</h4>
                        </div>
                        <div class="modal-body">
                                <div id="enviroment_detail_page" class="enviroment_detail_page_box">

                                </div>
                        </div>
                        <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                        </div>
                </div>
        </div>
</div>
<div>
        <ul class="nav nav-tabs">
                <c:if test="${sessionScope.currentUser == null}">
                        <li style="padding-left: 100px"><a href="${APP_PATH}/jsp/login.jsp">亲，请登录</a></li>
                        <li><a href="${APP_PATH}/jsp/register.jsp">免费注册</a></li>
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
                <li style="padding-left: 250px"><a href="${APP_PATH}/index.jsp"><span
                        class="glyphicon glyphicon-home"></span>首页</a></li>
                <li class="dropdown"><a href="###" class="dropdown" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-th-list"></span>我的订单
                        <span class="caret"></span>
                </a>
                        <ul class="dropdown-menu">
                                <li><a href="${APP_PATH}/jsp/myorders.jsp">全部订单</a></li>
                                <li><a href="${APP_PATH}/jsp/goods_received.jsp">待收货</a></li>
                        </ul>
                </li>
                <li><a href="${APP_PATH}/jsp/shoppingcart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>购物车</a>
                </li>
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
                <li>
                        <div><img src="${APP_PATH}/static/images/myfavoritestore/img3.jpg" alt=""></div>
                </li>
                <li>
                        <h3 id="shop_name"></h3>
                </li>
        </ul>
</div>
<hr size="2px">
<div class="box">
        <div class="row">
                <div class="col-md-4 firstcol">
                        <img src="${sessionScope.currentProduct.mainImage}" alt="" class="img-thumbnail">
                </div>
                <div class="col-md-5">
                        <div>
                                <h4 style="text-align: center;"><strong>${sessionScope.currentProduct.name}</strong>
                                </h4>
                                <p>${sessionScope.currentProduct.subtitle}</p>
                                <p>${sessionScope.currentProduct.detail}</p>
                        </div>
                        <div class="price">价格<span><strong>￥${sessionScope.currentProduct.price}/kg</strong></span>
                        </div>
                        <div style="padding-top: 20px;">数量
                                <div class="btn-group" style="text-align: center;padding-left: 100px;">
                                        <button type="button" value="+" class="btn btn-default" onClick="numDec()">-
                                        </button>
                                        <input value="1" id="quantity" type="text" class="btn btn-default" id="quantity"
                                               style="width: 50px;"/>
                                        <button type="button" value="-" class="btn btn-default" onClick="numAdd()">+
                                        </button>
                                </div>
                        </div>
                        <div style="padding-top: 20px;">
                                <button id="add_cart_btn" class="btn btn-danger"
                                        style="margin-left: 80px;margin-top30px;width: 200px;">
                                        <span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;加入购物车
                                </button>
                                <button id="add_collection_btn" class="btn btn-success"
                                        style="margin-left: 80px;margin-top:20px;width: 200px;">
                                        <span class="glyphicon glyphicon-heart"></span> 加入收藏
                                </button>
                        </div>
                </div>
                <div class="col-md-3">
                        <div class="col-md-8 rig">
                                <div class="row"><h4><strong>金牌卖家</strong></h4></div>
                                <div class="row"><h5><strong>我的金牌，你的信赖</strong></h5></div>
                                <div class="row" style="padding:10px 0;">信誉：100%</div>
                                <div class="row" style="padding:10px 0;">掌柜：<span id="shop_owner"></span></div>
                        </div>
                        <div class="col-md-4">
                                <img src="${APP_PATH}/static/images/first-page/xiangqing/img1.jpg" alt="">
                        </div>
                </div>
        </div>
        <h3>其他图片：</h3>
        <div class="row enviroment" id="show_subimages1">
        </div>
        <div class="row enviroment" id="show_subimages2">
        </div>
        <div class="row enviroment" id="show_subimages3">
        </div>
        <h3>产品溯源：
                <button class="btn btn-info" id="enviroment_btn">查看生长详情</button>
        </h3>
        <div class="relation">
                <div class="col-md-6">
                        <form class="form-horizontal">
                                <div class="form-group">
                                        <label class="col-md-3 control-label">牲畜品种：</label>
                                        <div class="col-md-7"><input class="form-control" id="vatrieties" type="text"
                                                                     placeholder="暂无数据" disabled></div>
                                </div>
                                <div class="form-group">
                                        <label class="col-md-3 control-label">身体状况：</label>
                                        <div class="col-md-7"><input class="form-control" id="health" type="text"
                                                                     placeholder="暂无数据" disabled></div>
                                </div>
                                <div class="form-group">
                                        <label class="col-md-3 control-label">年龄：</label>
                                        <div class="col-md-7">
                                                <input class="form-control" id="age" type="text" placeholder="暂无数据"
                                                       disabled></div>
                                </div>
                                <div class="form-group">
                                        <label class="col-md-3 control-label">主食：</label>
                                        <div class="col-md-7"><input class="form-control" id="stapleFood" type="text"
                                                                     placeholder="暂无数据" disabled></div>
                                </div>
                                <div class="form-group">
                                        <label class="col-md-3 control-label">病历：</label>
                                        <div class="col-md-7"><input class="form-control" id="medicalRecord" type="text"
                                                                     placeholder="暂无数据" disabled></div>
                                </div>
                        </form>
                </div>
                <div class="col-md-6">
                        <form class="form-horizontal">
                                <div class="form-group">
                                        <label class="col-md-3 control-label">生产地：</label>
                                        <div class="col-md-7"><input class="form-control" id="origin" type="text"
                                                                     placeholder="暂无数据" disabled></div>
                                </div>
                                <div class="form-group">
                                        <label class="col-md-3 control-label">粪便：</label>
                                        <div class="col-md-7">
                                                <input class="form-control" id="faces" type="text" placeholder="暂无数据"
                                                       disabled></div>
                                </div>
                                <div class="form-group">
                                        <label class="col-md-3 control-label">疫苗：</label>
                                        <div class="col-md-7">
                                                <input class="form-control" id="vaccine" type="text" placeholder="暂无数据"
                                                       disabled></div>
                                </div>
                                <label class="col-md-3 control-label">照片：</label>
                                <div class="col-md-7">
                                        <img class="form-control" id="photo"
                                             src="${APP_PATH}/static/images/other/piky.jpg"
                                             style="width: 40px;height: 40px;" align="照片" alt="">
                                </div>
                        </form>
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
        <div class="bottom">Copyright © 版权所有</div>
</footer>
</body>
<script type="text/javascript">
    /*或者不用jquery*/
    /*商品数量框输入*/
    function keyup() {
        var quantity = document.getElementById("quantity").value;
        if (isNaN(quantity) || parseInt(quantity) != quantity || parseInt(quantity) < 1) {
            quantity = 1;
            return;
        }
    }

    /*商品数量+1*/
    function numAdd() {
        var quantity = document.getElementById("quantity").value;
        var num_add = parseInt(quantity) + 1;
        /*var price=document.getElementById("price").value;*/
        if (quantity == "") {
            num_add = 1;
        }
        {
            document.getElementById("quantity").value = num_add;
        }
    }

    /*商品数量-1*/
    function numDec() {
        var quantity = document.getElementById("quantity").value;
        /*var price=document.getElementById("price").value;*/
        var num_dec = parseInt(quantity) - 1;
        if (num_dec > 0) {
            document.getElementById("quantity").value = num_dec;
        }
    }

    // 生成订单
    $("#add_cart_btn").click(function () {
        var quantity = $("#quantity").val();
        $.ajax({
            url: "${APP_PATH}/cart/add_cart.do",
            data: "quantity=" + quantity + "&productId=" +${sessionScope.currentProduct.id},
            type: "POST",
            datatype: "json",
            success: function (result) {
                // console.log(result);
                if (result.status == 0) {
                    alert(result.msg);
                    window.location.reload();
                } else {
                    alert(result.msg);
                }
            }
        });
    });

    $("#add_collection_btn").click(function () {
        var productId = "${sessionScope.currentProduct.id}";
        $.ajax({
            url: "${APP_PATH}/product/add_collection.do",
            data: "productId=" + productId,
            type: "POST",
            datatype: "json",
            success: function (result) {
                if (result.status == 0) {
                    alert(result.msg);
                    window.location.reload();
                } else {
                    alert(result.msg);
                }
            }
        });
    });

    // 生成订单
    $("#create_order_btn").click(function () {
        var quantity = $("#quantity").val();
        var productId = "${sessionScope.currentProduct.id}";
        // 目前还没有收货地址 先弄个假数据
        var shippingId = 1;
        $.ajax({
            url: "${APP_PATH}/order/create.do",
            data: "productId=" + productId + "&quantity=" + quantity + "&shippingId=" + shippingId,
            type: "POST",
            datatype: "json",
            success: function (result) {
                // console.log(result);
                if (result.status == 0) {
                    alert("订单已经提交,请到'我的订单'确认收货地址后付款吧！");
                    window.location.href = "${APP_PATH}/index.jsp";
                } else {
                    alert(result.data);
                    window.location.reload();
                }
            }
        });
    });

    /**
     * 切割子图
     */
    $(function () {
        var subImages = "${sessionScope.currentProduct.subImages}";
        var subImageList = new Array();
        subImageList = subImages.split(",");
        var count = 1;

        $("#show_subimages1").empty();
        $("#show_subimages2").empty();
        $("#show_subimages3").empty();

        for (i = 0; i < subImageList.length - 1; i++) {
            // console.log(subImageList[i]);
            var subImageShow = $("<img alt='#' class='img-thumbnail'>").attr("src", subImageList[i]);
            var subImageDiv = $("<div class='col-md-3'></div>").append(subImageShow);//.appendTo("#show_subimages");

            if (count >= 1 && count <= 4) {
                subImageDiv.appendTo("#show_subimages1");
            } else if (count > 4 && count <= 8) {
                subImageDiv.appendTo("#show_subimages2");
            } else if (count > 8 && count <= 12) {
                subImageDiv.appendTo("#show_subimages3");
            }
            count++;
        }
    });

    $(function () {
        //alert("${sessionScope.currentProduct.id}");
        $.ajax({
            url: "${APP_PATH}/user/get_seller_info.do",
            data: "userId=" +${sessionScope.currentProduct.userId},
            type: "GET",
            datatype: "json",
            success: function (result) {
                // console.log(result);
                $("#shop_name").html(result.data.shopName + "的店铺");
                $("#shop_owner").html(result.data.username);
            }
        });
    });

    $(function () {
        $.ajax({
            url: "${APP_PATH}/product/show_livestock.do",
            data: "livestock=" +${sessionScope.currentProduct.livestock},
            type: "GET",
            datatype: "json",
            success: function (result) {
                // console.log(result);
                showLivestock(result);
            }
        });
    });

    function showLivestock(result) {
        var livestock = result.data;
        $("#vatrieties").val(livestock.varieties);
        $("#health").val(livestock.health);
        $("#age").val(livestock.age);
        $("#medicalRecord").val(livestock.medicalRecord);
        $("#stapleFood").val(livestock.stapleFood);
        $("#origin").val(livestock.origin);
        $("#vaccine").val(livestock.vaccine);
        $("#faces").val(livestock.faces);
    }

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

    $("#enviroment_btn").click(function () {
        $("#enviroment_modal").modal("show");
        $("#enviroment_detail_page").load("${APP_PATH}/jsp/enviromentDetail.jsp");
    });
</script>
</html>
