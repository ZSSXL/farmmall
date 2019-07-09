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
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/firstpage.css">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>首页</title>
</head>
<body>
<div class="progress" id="processBox" style="display: none;">
        <div class="progress-bar progress-bar-success" role="progressbar"
             aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;" id="progressBar">
        </div>
</div>
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
                <li style="padding-left: 250px" class="active"><a href="${APP_PATH}/index.jsp"><span
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
                <li class="dropdown">
                        <a href="###" class="dropdown" data-toggle="dropdown">
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
                <li>
                       <a href="${pageContext.request.contextPath}/manage/admin.do">网站维护</a>
                </li>
        </ul>
</div>
<div style="height: 150px;text-align: center;line-height: 150px;background-image: url(${APP_PATH}/static/images/first-page/back1.jpg);background-repeat: no-repeat;background-size: 100% 100%;" class="title">
        <img src="${APP_PATH}/static/images/first-page/title.png" alt="农产品在线商场">
</div>
<div class="input-group" style="padding:30px 400px 10px 400px;" id="one">
        <input type="text" name="search" class="form-control" style=" border: 2px solid rgba(244,128,50,1.00)">
        <div class="input-group-btn">
                <button class="btn btn-danger">搜索</button>
        </div>
</div>
<hr size="2px">
<div class="container bigbox">
        <div class="row">
                <div class="col-md-3 aside" id="categoryArea">
                </div>
                <div class="col-md-9 aright">
                        <div class="banner">
                                <ul class="img">
                                        <li>
                                                <a href="#">
                                                        <img src="${APP_PATH}/static/images/first-page/livestock1.jpg"
                                                             alt="第1张图片">
                                                 </a>
                                        </li>
                                        <li>
                                                <a href="#">
                                                        <img  src="${APP_PATH}/static/images/first-page/livestock2.jpg"
                                                             alt="第2张图片">
                                                </a>
                                        </li>
                                        <li>
                                                <a href="#">
                                                        <img src="${APP_PATH}/static/images/first-page/livestock3.jpg"
                                                             alt="第3张图片">
                                                </a>
                                        </li>
                                        <li>
                                                <a href="#">
                                                        <img  src="${APP_PATH}/static/images/first-page/livestock4.jpg"
                                                             alt="第4张图片">
                                                </a>
                                        </li>
                                        <li>
                                                <a href="#">
                                                        <img  src="${APP_PATH}/static/images/first-page/livestock5.jpg"
                                                             alt="第5张图片">
                                                </a>
                                        </li>
                                        <li>
                                                <a href="#">
                                                        <img src="${APP_PATH}/static/images/first-page/livestock6.jpg"
                                                             alt="第6张图片">
                                                </a>
                                        </li>
                                </ul>
                                <ul class="num"></ul>
                                <div class="btn btn1">
                                        <span class="prev">&lt;</span>
                                        <span class="next">&gt;</span>
                                </div>
                        </div>
                </div>
        </div>
        <hr size="1px">
        <h2>限时特价</h2>
        <div class="endbox">
                <div class="row" id="rowOne">
                        <hr size="1px">
                </div>
                <hr size="1px">
                <div class="row" id="rowTwo">
                        <hr size="1px">
                </div>
                <hr size="1px">
                <div class="row" id="rowThree">
                        <hr size="1px">
                </div>
                <hr size="1px">
                <div class="row" id="rowFour">
                        <hr size="1px">
                </div>
        </div>
</div>
<!-- 分页信息 -->
<hr size="1px">
<div class="row col-lg-offset-2">
        <!-- 分页文字信息 -->
        <div class="col-md-6 " id="page_info_area">

        </div>
        <!-- 分页条信息 -->
        <div class="col-md-6" id="page_nav_area">

        </div>
</div>
<hr size="1px">
<footer id="footer" style="margin-top: 10px;">
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
    var type = 1; // 默认查询所有商品
    var pn = 1; // 默认第一页
    var categoryId = "0"; // 设置一个空的分类id
    var totalRecord,currentPage;	// 保存分页总记录数

    $(function () {
        var i = 0;
        var timer = null;
        for (var j = 0; j < $(".img li").length; j++) {
            $('.num').append("<li></li>");
        }
        $(".num li").first().addClass("active");
        $(".banner").hover(function () {
            $(".btn1").show();
        }, function () {
            $(".btn1").hide();
        });
        var firstimg = $('.img li').first().clone();
        $(".img").append(firstimg).width($(".img li").length * ($(".img img").width()));
        timer = setInterval(function () {
            i++;
            if (i == $(".img li").length) {
                i = 1;
                $(".img").css({left: 0});
            }
            $(".img").stop().animate({left: -i * 1024}, 500);
            if (i == $(".img li").length - 1) {
                $(".num li").eq(0).addClass("active").siblings().removeClass("active");
            } else {
                $(".num li").eq(i).addClass("active").siblings().removeClass("active");
            }
        }, 3000);
        $(".banner").hover(function () {
            clearInterval(timer);
        }, function () {
            timer = setInterval(function () {
                i++;
                if (i == $(".img li").length) {
                    i = 1;
                    $(".img").css({left: 0});
                }
                ;
                $(".img").stop().animate({left: -i * 1024}, 500);
                if (i == $(".img li").length - 1) {
                    $(".num li").eq(0).addClass("active").siblings().removeClass("active");
                } else {
                    $(".num li").eq(i).addClass("active").siblings().removeClass("active");
                }
            }, 3000)
        });
        $(".prev").click(function () {
            i--;
            if (i == -1) {
                i = $(".img li").length - 2;
                $(".img").css({left: -($(".img li").length - 1) * 1024});
            }
            $(".img").stop().animate({left: -i * 1024}, 500);
            $(".num li").eq(i).addClass("active").siblings().removeClass("active");
        });
        $(".next").click(function () {
            i++;
            if (i == $(".img li").length) {
                i = 1;
                $(".img").css({left: 0});
            }
            ;
            $(".img").stop().animate({left: -i * 1024}, 500);
            if (i == $(".img li").length - 1) { //设置小圆点指示
                $(".num li").eq(0).addClass("active").siblings().removeClass("active");
            } else {
                $(".num li").eq(i).addClass("active").siblings().removeClass("active");
            }
            ;

        });
        $(".num li").mouseover(function () {
            var _index = $(this).index();
            i = _index;
            $(".img").stop().animate({left: -_index * 1024}, 500);
            $(".num li").eq(_index).addClass("active").siblings().removeClass("active");
        });
    })

    // 打开页面加载商品数据
    $(function () {
        showProductBySelective(type, categoryId, pn);
        showCategory("#categoryArea");
    });


    /* 进度条 */
    var xhr=new XMLHttpRequest();
        xhr.upload.onprogress=function(e){}
    var xhrOnProgress=function(fun) {
        xhrOnProgress.onprogress = fun; //绑定监听
        //使用闭包实现监听绑
        return function() {
            //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
            var xhr = $.ajaxSettings.xhr();
            //判断监听函数是否为函数
            if (typeof xhrOnProgress.onprogress !== 'function')
                return xhr;
            //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
            if (xhrOnProgress.onprogress && xhr.upload) {
                xhr.upload.onprogress = xhrOnProgress.onprogress;
            }
            return xhr;
        }
    }

    /**
     * 有选择的展示商品//
     * @param type 展示商品的类型，1是不分类，2是按猪肉种类分类
     * @param categoryId  type选择了2，那catetoryId就一定要输入值
     * @param pn 输入页数，默认第一页
     */
    function showProductBySelective(type, categoryId, pn) {
        //console.log("type:"+type+"categoryId:"+categoryId+"pn:"+pn);
        $.ajax({
            url: "${APP_PATH}/product/show_product.do",
            data: "type=" + type + "&pn=" + pn + "&categoryId=" + categoryId,
            type: "GET",
            datatype: "json",
            xhr:xhrOnProgress(function(e){
                $("#processBox").removeAttr("style");
                var per=100 * e.loaded / e.total;//计算百分比
                var son =  document.getElementById("progressBar");
                son.innerHTML = per + "%";
                son.style.width = per + "%";
            }),
            success: function (result) {
                //console.log(result);
                // 1、展示商品信息
                showOnWeb(result);
                // 2、解析显示分页信息
                build_page_info(result);
                // 3、解析显示分页条数据
                build_page_nav(result);

            }
        });
    };

    function showOnWeb(result){
        // 清空div中的内容
        $("#rowOne").empty();
        $("#rowTwo").empty();
        $("#rowThree").empty();
        $("#rowFour").empty();
        var productItems = result.data.list;
        var count = 1;
        $.each(productItems,function (index,productItem) {
            var productName = $("<h4 style='text-align: center'></h4>").append($("<strong></strong>").append(productItem.name));
            var productImage = $("<img alt='#' class='img-thumbnail'></img>").attr("src",productItem.mainImage);
            var productDetail = $("<h4></h4>").append(productItem.detail);
            var productPrice = $("<span class='price'></span>").append($("<strong></strong>").append("￥"+productItem.price+"元/kg"));
            var productButton = $("<button class='btn btn-info buyBtn' style='margin-left: 180px;'></button>").append("去选购").attr("product_id",productItem.id);

            var productShow = $("<div class='col-md-4 tejia'></div>").append(productName)
                .append(productImage)
                .append(productDetail)
                .append(productDetail)
                .append(productPrice)
                .append(productButton);

            if(count <= 3){
                productShow.appendTo("#rowOne");
            }else if(count > 3 && count <= 6){
                productShow.appendTo("#rowTwo");
            }else if(count > 6 && count <= 9){
                productShow.appendTo("#rowThree");
            }else if(count > 9 && count <= 12){
                productShow.appendTo("#rowFour");
            }
            count++;
        });
    }

    function showCategory(ele) {
        $.ajax({
            url: "${APP_PATH}/category/get_all_category.do",
            type: "get",
            success: function (result) {
                // console.log(result);
                var categorys = result.data;
                $.each(categorys,function (index,category) {
                    var categoryName = $("<a href='#none' class='aclick'></a>").append(category.name);
                    categoryName.attr("category_id",category.id);

                    var categoryItem = $("<div></div>").addClass("col_md_3")
                        .append($("<div style='height: 41.6px;'></div>").addClass("row clasi")
                        .append($("<div></div>").append(categoryName)));

                    $("<div></div>").addClass("row").append(categoryItem).appendTo(ele);
                })
            }
        });
    }

    // 根据商品分类查询显示
    $(document).on("click",".aclick",function () {
        categoryId = $(this).attr("category_id");
        pn = 1;
        type = 2;
        showProductBySelective(type, categoryId, pn);
    });

    $(document).on("click",".buyBtn",function () {
        //alert("商品id："+$(this).attr("product_id"));
        var productId = $(this).attr("product_id");
        $.ajax({
            url: "${APP_PATH}/product/show_product_by_id.do",
            type: "get",
            data:"productId="+productId,
            success: function (result) {
                //console.log(result);
                //alert(result.msg);
                if(result.status == 0){
                    window.location.href = "jsp/xiangqing.jsp";
                }else{
                    alert(result.msg);
                }
            }
        });
    });

    // 解析显示分页记录信息
    function build_page_info(result){
        // 有则清空
        $("#page_info_area").empty();

        var pageinfo = result.data;
        $("#page_info_area").append(" 当前"+pageinfo.pageNum+"页,总共"+pageinfo.pages+"页,共"+pageinfo.total+"条记录");
        totalRecord = pageinfo.total;
        currentPage = pageinfo.pageNum;
    }

    // 解析显示分页条
    function build_page_nav(result){

        // 有则清空
        $("#page_nav_area").empty();

        var ul = $("<ul></ul>").addClass("pagination");

        // 第一页
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));

        // 前一页
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));

        // 判断时候还有上一页，没有则disable
        if(result.data.hasPreviousPage == false){

            firstPageLi.addClass("disable");
            prePageLi.addClass("disable");

        }else{
            // 跳转第一页点击事件
            firstPageLi.click(function(){
                showProductBySelective(type, categoryId, 1);
            });
            // 跳转上一页点击事件
            prePageLi.click(function(){
                showProductBySelective(type, categoryId, result.data.pageNum - 1);
            });
        }''

        // 下一页
        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));

        // 最后一页
        var LastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));

        // 判断时候还有下一页，没有则disable
        if(result.data.hasNextPage == false){
            nextPageLi.addClass("disable");
            LastPageLi.addClass("disable");
        }else{
            // 跳转下一页点击事件
            nextPageLi.click(function(){
                showProductBySelective(type, categoryId, result.data.pageNum + 1);
            });
            // 跳转最后一页点击事件
            LastPageLi.click(function(){
                alert("点击最后一页："+result.data.pages);
                showProductBySelective(type, categoryId, result.data.pages);
            });
        }


        ul.append(firstPageLi).append(prePageLi);

        $.each(result.data.navigatepageNums,function(index,item){
            var numLi =  $("<li></li>").append($("<a></a>").append(item));
            if(result.data.pageNum == item){
                numLi.addClass("active");
            }
            numLi.click(function(){
                showProductBySelective(type, categoryId, item);
            })
            ul.append(numLi);
        })
        // 添加下一页和尾页的提示
        ul.append(nextPageLi).append(LastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }


    $("#logout").click(function () {
        $.ajax({
            url:"${APP_PATH}/user/logout.do",
            type:"post",
            success:function (result) {
                if(result.status == 0){
                    window.location.reload();
                }else{
                    alert(result.msg);
                }
            }
        });
    });
</script>
</html>


