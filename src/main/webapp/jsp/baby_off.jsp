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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/baby_on.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>宝贝下架</title>
</head>

<body>
<div>
        <ul class="nav nav-tabs">
                <c:if test="${sessionScope.currentUser == null}">
                        <li style="padding-left: 100px"><a href="${APP_PATH}/jsp/login.jsp">亲，请登录</a></li>
                        <li><a href="${APP_PATH}/jsp/register.jsp">免费注册</a></li>
                </c:if>
                <c:if test="${sessionScope.currentUser != null}">
                        <li style="padding-left: 100px"><span  class="label label-info">欢迎:${sessionScope.currentUser.username}</span></li>
                        <li ><button class="btn btn-block" id="logout">退出登录</button></li>
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
                        <li><div style="margin-left: 100px; "><a href="${APP_PATH}/jsp/free_opening.jsp">免费开店</a></div></li>
                </c:if>
                <li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">订单管理</a></div>
                </li>
                <li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/sellingbaby.jsp">发货</a></div></li>
                <li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></div></li>
                <li><div style="margin-left: 50px;background-color: rgba(0,0,0,0.1);"><a href="${APP_PATH}/jsp/baby_off.jsp">下架宝贝</a></div></li>
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
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">订单管理</a></div>
                </div>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img3.png" alt="">&nbsp;</span><a href="#"><strong>物流管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/send.jsp">发货</a></div>
                </div>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img4.png" alt="">&nbsp;</span><a href="#"><strong>宝贝管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_off.jsp" style="color: rgba(236,82,60,1.00);">下架宝贝</a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/sellingbaby.jsp">出售中的宝贝</a></div>
                </div>
        </div>
        <div class="col-md-10 middle">
                <div class="row">
                        <div class="seller">我是卖家&nbsp;&gt;&nbsp;宝贝管理&nbsp;&gt;&nbsp;下架宝贝</div>
                </div>
                <hr size="1px">
                <div class="row">
                        <table class="table table-striped text-center">
                                <thead style="background-color: rgba(224,215,215,0.60);">
                                <tr>
                                        <th style="text-align: center">商品ID</th>
                                        <th style="text-align: center">商品名称</th>
                                        <th style="text-align: center">商品库存</th>
                                        <th style="text-align: center">商品状态</th>
                                        <th style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody id="product_list">
                                </tbody>
                        </table>
                </div>
        </div>
</div>

<script type="text/javascript">


    $(document).on("click", ".show_product_detail", function () {
        var productId = $(this).attr("product_id");
        $.ajax({
            url: "${APP_PATH}/product/show_product_by_id.do",
            type: "get",
            data: "productId=" + productId,
            success: function (result) {
                if (result.status == 0) {
                    window.location.href = "${APP_PATH}/jsp/xiangping.jsp";
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

    $(function () {
        $.ajax({
            url:"${APP_PATH}/product/show_product_by_seller.do",
            type:"get",
            success:function (result) {
                //  console.log(result);
                analysisResult(result);
            }
        });
    })

    $(document).on("click",".deleteBtn",function () {
        var status = 3;
        var productId = $(this).attr("product_id");
        changProductStatus(status,productId);
    });

    $(document).on("click",".lowerBtn",function () {
        var status = 2;
        var productId = $(this).attr("product_id");
        changProductStatus(status,productId);
    });

    $(document).on("click",".upperBtn",function () {
        var status = 1;
        var productId = $(this).attr("product_id");
        changProductStatus(status,productId);
    });

    function changProductStatus(status,productId) {
        $.ajax({
            url:"${APP_PATH}/product/update_product_status.do",
            type:"post",
            data:"status="+status+"&productId="+productId,
            success:function (result) {
                // console.log(result);
                if(result.status == 0){
                    alert(result.msg);
                    window.location.reload();
                }else{
                    alert(result.msg);
                }
            }
        });
    }

    function analysisResult(result){
        $("#product_list").empty();
        var productList = result.data;
        $.each(productList,function (index,productItem) {
            var imageTd = $("<td></td>").append($("<img style='width: 120px;height: 140px;'>").attr("src",productItem.mainImage));
            var productNameTd = $("<td></td>").append($("<div></div>").append($("<a class='show_product_detail' style='cursor: pointer;'></a>").append(productItem.name).attr("product_id",productItem.id)));
            var statusTd = $("<td></td>");
            var stockTd = $("<td></td>").append(productItem.stock);
            var deleteBtn = $("<button class='btn btn-danger deleteBtn'>删除</button>").attr("product_id",productItem.id);
            var lowerShelfBtn = $("<button class='btn btn-warning lowerBtn'>下架</button>").attr("product_id",productItem.id);
            var upperShelfBtn = $("<button class='btn btn-info upperBtn'>上架</button>").attr("product_id",productItem.id);
            var operationTd = $("<td></td>");
            if(productItem.status == 1){
                statusTd.append("在售");
                operationTd.append(lowerShelfBtn);
                operationTd.append(deleteBtn);
            }else if(productItem.status == 2){
                statusTd.append("下架");
                operationTd.append(upperShelfBtn);
                operationTd.append(deleteBtn);
            }else if(productItem.status == 3){
                statusTd.append("删除");
                operationTd.append(upperShelfBtn);
            }

            $("<tr></tr>").append(imageTd)
                .append(productNameTd)
                .append(stockTd)
                .append(statusTd)
                .append(operationTd)
                .appendTo("#product_list");
        });
    }
</script>
</body>
</html>
