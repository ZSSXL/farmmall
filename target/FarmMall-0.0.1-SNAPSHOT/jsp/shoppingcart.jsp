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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/shoppingcart.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/js/info.js"></script>
</head>

<body onload="setup();preselect('江西省');promptinfo();">
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
                <li style="padding-left: 250px"><a href="${APP_PATH}/index.jsp">首页</a></li>
                <li class="dropdown"><a href="###" class="dropdown" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-th-list"></span>我的订单
                        <span class="caret"></span>
                </a>
                        <ul class="dropdown-menu">
                                <li><a href="${APP_PATH}/jsp/myorders.jsp">全部订单</a></li>
                                <li><a href="${APP_PATH}/jsp/goods_received.jsp">待收货</a></li>
                        </ul>
                </li>
                <li class="active"><a href="${APP_PATH}/jsp/shoppingcart.jsp"><span
                        class="glyphicon glyphicon-shopping-cart"></span>购物车</a></li>
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
                                <li><a href="${APP_PATH}/jsp/modifypsw.jsp">修改密码</a></li>
                        </ul>
                </li>
        </ul>
</div>
<div class="box">
        <div class="row">
                <div class="col-md-1"><a href="#">
                        <button class="btn btn1" id="showModel" style="margin-bottom: 10px;">新增地址</button>
                </a></div>
                <div id="modal" class="modal">
                        <div class="modal-content">
                                <header class="modal-header">
                                        <h4><strong>新增收货地址</strong></h4>
                                        <span class="close1">×</span></header>
                                <div class="modal-body">
                                        <form class="form-horizontal">
                                                <div  class="form-group" style="margin-left: 47px;">
                                                        <select class="select" name="province" id="s1">
                                                                <option></option>
                                                        </select>
                                                        <select class="select" name="city" id="s2">
                                                                <option></option>
                                                        </select>
                                                        <select class="select" name="town" id="s3">
                                                                <option></option>
                                                        </select>
                                                </div>
                                                <div class="form-group">
                                                        <label class="col-md-3 control-label">详细地址：</label>
                                                        <div class="col-md-5">
                                                                <input class="form-control" type="text"
                                                                       id="shipping_address">
                                                        </div>
                                                </div>
                                                <div class="form-group">
                                                        <label class="col-md-3 control-label">邮政编码：</label>
                                                        <div class="col-md-5">
                                                                <input class="form-control" type="text"
                                                                       id="shipping_zip">
                                                        </div>
                                                </div>
                                                <div class="form-group">
                                                        <label class="col-md-3 control-label">收货人：</label>
                                                        <div class="col-md-5">
                                                                <input class="form-control" type="text"
                                                                       id="shipping_name">
                                                        </div>
                                                </div>
                                                <div class="form-group">
                                                        <label class="col-md-3 control-label">手机号：</label>
                                                        <div class="col-md-5">
                                                                <input class="form-control" type="text"
                                                                       id="shipping_phone">
                                                        </div>
                                                </div>
                                        </form>
                                </div>
                                <footer class="modal-footer">
                                        <button id="cancel_btn">取消</button>
                                        <button id="sure_btn">确定</button>
                                </footer>
                        </div>
                </div>
                <div class="col-md-7"></div>
                <div class="col-md-4">
                        <div class="input-group">
                                <input type="text" name="search" class="form-control"
                                       style=" border: 2px solid rgba(244,128,50,1.00)">
                                <div class="input-group-btn">
                                        <button class="btn btn-danger">搜索</button>
                                </div>
                        </div>
                </div>
        </div>
        <div class="address row" id="address-list">
        </div>
        <div style="padding-top: 20px;">
                <table class="table table-bordered text-center">
                        <thead>
                        <tr>
                                <th><label id="allInner">全(不)选:</label>
                                        <input type="checkbox" class="selectAll"></th>
                                <th>商品图片</th>
                                <th>商品名称</th>
                                <th>数量</th>
                                <th>单价(/kg)</th>
                                <th>小计</th>
                                <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="cart_list">
                        </tbody>
                </table>
        </div>
        <div class="row">
                <div class="col-lg-6 col-md-offset-6">
                        <div class="input-group">
                                <input id="total_price" type="text" class="form-control col-md-offset-7" style="width: 160px;" disabled><h4><strong> 元</strong></h4>
                                <span class="input-group-btn">
                                        <button class="btn btn-info" type="button" id="create_order_checked">下单</button>
                                </span>
                        </div><!-- /input-group -->
                </div><!-- /.col-lg-6 -->
        </div><!-- /.row -->
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
<script>
    var btn = document.getElementById('showModel');
    var close = document.getElementsByClassName('close1')[0];
    var cancel = document.getElementById('cancel');
    var modal = document.getElementById('modal');
    btn.addEventListener('click', function () {
        modal.style.display = "block";
    });
    close.addEventListener('click', function () {
        modal.style.display = "none";
    });
    cancel.addEventListener('click', function () {
        modal.style.display = "none";
    });
</script>
<script>
    $(".selectAll").on("click", function () {
        if (this.checked) {
            $(".check").prop("checked", true);
            var checked = 1;
            checkAllCart(checked);
        } else {
            $(".check").prop("checked", false);
            var checked = 0;
            checkAllCart(checked);
        }
    })

    function checkAllCart(checked){
        $.ajax({
            url: "${APP_PATH}/cart/select_check_all.do",
            type: "post",
            data:"checked="+checked,
            success:function () {
                console.log(result);
            }
        });
        alert("wait");
    };
</script>
<script>
    var checkS = $(".check");
    for (var i = 0; i < checkS.length; i++) {
        checkS[i].onclick = function () {
            var isSelectAll = true;//假设全选是选中状态的自定义变量
            for (var i = 0; i < checkS.length; i++) {
                if (checkS[i].checked == false) {
                    isSelectAll = false;
                    break;
                }
            }
            if (isSelectAll) {
                $(".selectAll").prop("checked", true);
                $("#allInner").html("全不选:");
            }
            if (this.checked == false) {
                $(".selectAll").prop("checked", false);
                $("#allInner").html("全选:");
            }
        }
    }
</script>
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

    $(document).on("click", ".addBtn", function () {
        var cartId = $(this).parents("tr").find("td:eq(6)").find("div").find("button").attr("cart_id")
        numAdd(this,cartId);
    });

    $(document).on("click", ".decBtn", function () {
        var cartId = $(this).parents("tr").find("td:eq(6)").find("div").find("button").attr("cart_id")
        numDec(this,cartId);
    });

    /*商品数量+1*/
    function numAdd(ele,cartId) {
        var quantity = $(ele).prev("input").val();
        var num_add = parseInt(quantity) + 1;
        var price = $(ele).parents("tr").find("td:eq(4)").find("div").find("input").val();
        if (quantity == "") {
            num_add = 1;
        }
        $(ele).prev("input").val(num_add);
        {
             var Num=price*num_add;
             var totalPrice = (Num.toFixed(2));
             $(ele).parents("tr").find("td:eq(5)").find("div").find("span").text(totalPrice);
         }

        $.ajax({
            url: "${APP_PATH}/cart/add_quantity.do",
            type: "post",
            data:"cartId="+cartId,
        });
    }

    /*商品数量-1*/
    function numDec(ele,cartId) {
        var quantity = $(ele).next("input").val();
        var price = $(ele).parents("tr").find("td:eq(4)").find("div").find("input").val();
        var num_dec = parseInt(quantity) - 1;
        if(num_dec >= 1){
            $(ele).next("input").val(num_dec);
        }
        if(num_dec>0){
            var Num=price*num_dec;
            var totalPrice = (Num.toFixed(2));
            $(ele).parents("tr").find("td:eq(5)").find("div").find("span").text(totalPrice);

            $.ajax({
                url: "${APP_PATH}/cart/dec_quantity.do",
                type: "post",
                data:"cartId="+cartId,
            });
        }
    }
</script>
<script type="text/javascript">
        var shippingId = 1;

        // 给本标签设置active,删除其他标签的active
        $(document).on("click",".address-item",function () {
            shippingId = $(this).attr("shipping_id");
            $(this).addClass("active");
            $(this).parents().siblings().find("a").removeClass("active");
            $(this).parent().attr("style","border:2px solid #A0C0FF;text-align: center;background-color: #A5DEE5;");
            $(this).parent().siblings().attr("style","border:1px dashed red;text-align: center;");
        });

    // 显示总价
    $(function () {
        showTotalPrice();
    });

    function showTotalPrice(){
        $.ajax({
            url: "${APP_PATH}/cart/calcu_total_price.do",
            type: "get",
            success: function (result) {
                // console.log(result);
                $("#total_price").val(result.msg);
            }
        });
    }

    $("#sure_btn").click(function () {
        var formData = new FormData();

        var name = $("#shipping_name").val();
        var phone = $("#shipping_phone").val();
        var zip = $("#shipping_zip").val();
        var address = $("#shipping_address").val();
        var provice = $("#s1").val();
        var city = $("#s2").val();
        var district = $("#s3").val();

        formData.append("receiverName", name);
        formData.append("receiverPhone", phone);
        formData.append("receiverZip", zip);
        formData.append("receiverAddress", address);
        formData.append("receiverProvince", provice);
        formData.append("receiverCity", city);
        formData.append("receiverDistrict", district);

        $.ajax({
            url: "${APP_PATH}/shipping/add_shipping.do",
            type: "post",
            processData: false,
            contentType: false,
            dataType: "json",
            data: formData,
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

    $(function () {
        var pn = 1;
        $.ajax({
            url: "${APP_PATH}/cart/show_cart.do",
            type: "get",
            data: "pn=" + pn,
            success: function (result) {
                if (result.status == 1) {
                    alert(result.msg);
                } else {
                    showMyCartList(result);
                }
            }
        });
    });

    function showMyCartList(result) {
        $("#cart_list").empty();
        var cartList = result.data.list;
        $.each(cartList, function (index, cartItem) {
            var choiceTd = $("<td style='line-height: 137px;'></td>").append($("<label></label>").append("选择")).attr("cart_id", cartItem.id);
            if (cartItem.checked == 1) {
                var checkBoxInput = $("<input type='checkbox' class='check' checked='checked'>");
            } else if (cartItem.checked == 0) {
                var checkBoxInput = $("<input type='checkbox' class='check'>");
            }
            choiceTd.append(checkBoxInput);
            var imageTd = $("<td style='line-height: 137px;'></td>").append($("<a href='#'></a>").append($("<img alt='#' class='img-thumbnail' style='width:100px;height: 120px;'>").attr("src", cartItem.mainImage)));
            var productNameTd = $("<td style='line-height: 137px;'></td>").append($("<div></div>").append($("<a href='#' class='show_product_detail'></a>").append(cartItem.name).attr("product_id", cartItem.productId)));
            var quantityDec = $("<button type='button' value='+' class='btn btn-default decBtn'>-</button>");
            var quantityInput = $("<input type='text' class='btn btn-default' id='quantity' style='width:50px;'/>").attr("value", cartItem.quantity);
            var quantityAdd = $("<button type='button' value='-' class='btn btn-default addBtn'>+</button>");
            var quantityTd = $("<td style='line-height: 137px;'></td>").append($("<div class='btn-group' style='text-align:center;'></div>").append(quantityDec).append(quantityInput).append(quantityAdd));
            // quantityTd.append(quantityDec).append(quantityInput).append(quantityAdd);
            var priceInput = $("<input type='text' id='price' style='width: 50px;border: hidden;'/>").attr("value", cartItem.price);
            var priceTd = $("<td style='line-height: 137px;'></td>").append($("<div>单价：</div>").append(priceInput));
            var totalPriceTd = $("<td style='line-height: 137px;'></td>").append($("<div>小记：</div>").append($("<span id='totalPrice'></span>").append(cartItem.price * cartItem.quantity)));

            var deleteBtn = $("<button class='btn btn-danger'>删除</button>").attr("cart_id", cartItem.id);
            var orderBtn = $("<button class='btn btn-info to_pay'>下单</button>").attr("product_id", cartItem.productId);
            var operaTd = $("<td style='line-height: 137px;'></td>").append($("<div></div>").append(deleteBtn).append(orderBtn));

            $("<tr></tr>").append(choiceTd)
                .append(imageTd)
                .append(productNameTd)
                .append(quantityTd)
                .append(priceTd)
                .append(totalPriceTd)
                .append(operaTd)
                .appendTo("#cart_list");
        });
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

    $(document).on("click", ".address-delete", function () {
        var shippingId = $(this).attr("shippingid");
        $.ajax({
            url: "${APP_PATH}/shipping/delete_shipping.do",
            type: "post",
            data: "shippingId=" + shippingId,
            success: function (result) {
                console.log(result);
                if (result.status == 0) {
                    alert(result.msg);
                    window.location.reload();
                } else {
                    alert(result.msg);
                    window.location.reload();
                }
            }
        });
    });

    $(document).on("change", ".check", function () {
        var cartId = $(this).parents("td").attr("cart_id");
        var type = 1;
        if ($(this).prop('checked')) {
            // 选择勾选
            type = 1;
            selectCheck(type, cartId);
        } else {
            // 取消勾选
            type = 0;
            selectCheck(type, cartId);
        }
    });

     $(document).on("click", ".to_pay", function () {
         if(parseInt(shippingId) == 1){
             alert("请选择收货地址");
             window.location.reload();
             return;
         }
         var productId = $(this).attr("product_id");
         var quantity =  $(this).parent().parent().siblings("td:eq(3)").find("div").find("input").val();

         $.ajax({
             url: "${APP_PATH}/order/create_from_cart.do",
             data:"productId="+productId+"&quantity="+quantity+"&shippingId="+shippingId,
             type: "POST",
             datatype: "json",
             success: function (result) {
                 console.log(result);
                 if(result.status == 0){
                     alert("订单已经提交,请到《我的订单》确认收货地址后付款吧！");
                     window.location.reload();
                 }else{
                     alert(result.msg);
                     window.location.reload();
                 }
             }
         });
     });

    // 删除购物车商品
    $(document).on("click", ".btn-danger", function () {
        var cartId = $(this).attr("cart_id");
        $.ajax({
            url: "${APP_PATH}/cart/delete_cart_by_cartId.do",
            type: "post",
            data: "cartId=" + cartId,
            success: function (result) {
                // console.log(result);
                if (result.status == 0) {
                    alert(result.msg);
                    window.location.reload();
                } else {
                    alert(result.msg);
                    window.location.reload();
                }
            }
        });
    });

    function selectCheck(type, cartId) {
        $.ajax({
            url: "${APP_PATH}/cart/select_checked.do",
            type: "post",
            data: "cartId=" + cartId + "&type=" + type,
        });
        showTotalPrice();
    }

    $(document).on("click", ".address-updata", function () {
        alert("编辑：" + $(this).attr("shippingid")+"  这个功能先放着");
    });

    // 显示个人收货地址
    $(function () {
        var pn = 1;
        $.ajax({
            url: "${APP_PATH}/shipping/show_shipping.do",
            type: "get",
            data: "pn=" + pn,
            success: function (result) {
                showShipping(result);
            }
        });
    });

    $("#create_order_checked").click(function () {

        if(parseInt(shippingId) == 1){
            alert("请选择收货地址");
            window.location.reload();
            return;
        }
        $.ajax({
            url: "${APP_PATH}/order/create_order_from_cart.do",
            type: "post",
            data: "shippingId=" + shippingId,
            success: function (result) {
                console.log(result);
                if(result.status == 0){
                    alert(result.msg);
                    window.location.reload();
                }else{
                    alert(result.msg);
                }
            }
        });
    });

    function showShipping(result) {
        $("#address-list").empty();
        var shippingList = result.data.list;
        $.each(shippingList, function (index, shippingItem) {
            var boxDiv = $("<div class='col-md-3' style='border:1px dashed red;text-align: center;'></div>");
            var hahaha = $("<a class='address-item'></a>").attr("shipping_id", shippingItem.id);
            var shippingTitle = $("<div class='address-title'></div>");
            var shippingPC = $("<span class='city' style='border-buttom:1px dashed #ccc;line-height: 36px;'></span>").append(shippingItem.receiverProvince + " " + shippingItem.receiverCity + " " + shippingItem.receiverDistrict);
            var receiverName = $("<span class='name' style='display: block;'></span>").append("(" + shippingItem.receiverName + ")收");
            shippingTitle.append(shippingPC).append(receiverName);
            var shippingAddress = $("<div class='address-detail'></div>").append(shippingItem.receiverAddress);
            var shippingOpera = $("<div class='address-opera'><div>");
            var shippingUpdate = $("<span class='link address-updata'>编辑  </span>").attr("shippingId", shippingItem.id);
            var shippingDelete = $("<span class='link address-delete'>删除</span>").attr("shippingId", shippingItem.id);
            boxDiv.append(hahaha.append(shippingTitle)
                .append(shippingAddress)
                .append(shippingOpera.append(shippingUpdate).append(shippingDelete)))
                .appendTo("#address-list");
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

</script>
</html>