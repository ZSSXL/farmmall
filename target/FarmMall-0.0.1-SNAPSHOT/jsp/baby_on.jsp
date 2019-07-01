<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
        <%
                pageContext.setAttribute("APP_PATH", request.getContextPath());
        %>
        <title>上传商品</title>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/base.css">
        <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/baby_on.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
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
                <li class="dropdown">
                        <a href="###" class="dropdown" data-toggle="dropdown">
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
                <li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">订单查询</a></div>
                </li>
                <li><div style="margin-left: 50px;"><a href="${APP_PATH}/jsp/sellingbaby.jsp">发货</a></div></li>
                <li><div style="margin-left: 50px;background-color: rgba(0,0,0,0.1);  "><a href="${APP_PATH}/jsp/baby_on.jsp">发布宝贝</a></div></li>
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
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/selledbaby.jsp">订单管理</a></div>
                </div>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img3.png" alt="">&nbsp;</span><a href="#"><strong>物流管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/send.jsp" >发货</a></div>
                </div>
                <div class="row" style="padding-left: 10px;padding-top: 20px;">
                        <div><span><img src="${APP_PATH}/static/images/free_opening/img4.png" alt="">&nbsp;</span><a href="#"><strong>宝贝管理</strong></a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_on.jsp" style="color: rgba(236,82,60,1.00);">发布宝贝</a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/baby_off.jsp">下架宝贝</a></div>
                        <div style="padding-left: 28px;padding-top: 10px;"><a href="${APP_PATH}/jsp/sellingbaby.jsp">出售中的宝贝</a></div>
                </div>
        </div>
        <div class="col-md-10 middle">
                <div><h4>商品基本信息</h4></div>
                <hr size="2px">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                        <div class="row">
                                <div class="col-md-2">
                                        <strong><p>产品分类:</p></strong>
                                </div>
                                <div class="col-md-4" >
                                        <select class="form-control" name="categoryId" id="categoryName">
                                        </select>
                                </div>
                                <div class="col-md-2">
                                        <strong><p>牲畜种类:</p></strong>
                                </div>
                                <div class="col-md-4"  >
                                        <select class="form-control" name="livestockLabel" id="livestockLabel">
                                        </select>
                                </div>
                        </div>
                        <div class="row">
                                <div class="col-md-2">
                                        <strong><p>产品名称:</p></strong>
                                </div>
                                <div class="col-md-4">
                                        <input type="text" id="title" name="title">
                                </div>
                                <div class="col-md-2">
                                        <strong><p>产品副标题:</p></strong>
                                </div>
                                <div class="col-md-4">
                                        <input type="text" id="subTitle" name="subtitle">
                                </div>
                        </div>
                        <div class="row">
                                <div class="col-md-2">
                                        <strong><p>商品详情:</p></strong>
                                </div>
                                <div class="col-md-4">
                                        <input type="text" id="detail" name="datail">
                                </div>
                                <div class="col-md-2">
                                        <strong><p>价格:</p></strong>
                                </div>
                                <div class="col-md-4">
                                        <input type="text" id="price" name="price">
                                </div>
                        </div>
                        <div class="row">
                                <div class="col-md-2">
                                        <strong><p>库存:</p></strong>
                                </div>
                                <div class="col-md-4">
                                        <input type="text" id="stock" name="stock">
                                </div>
                                <div class="col-md-2">
                                        <strong><p>产品状态:</p></strong>
                                </div>
                                <div class="col-md-4">
                                        <div>
                                                <select id="status" name="status">
                                                        <option value="1">上架</option>
                                                        <option value="2">下架</option>
                                                </select>
                                        </div>
                                </div>
                        </div>
                        <div class="row">
                                <div class="col-md-2">
                                        <strong><p>产品主图:</p></strong>
                                </div>
                                <div class="col-md-10">
                                        <form enctype="multipart/form-data" name="form1">
                                                <input id="f" type="file" name="mainImage" onchange="change()"  multiple accept="image/png,image/jpg,image/gif,image/JPEG">
                                               <%-- <div class="upload">上传图片</div>--%>
                                                <p><img id="preview" alt="" name="pic" style="width: 200px;height: 180px;" /></p>
                                        </form>
                                </div>
                        </div>
                        <div class="row">
                                <div class="col-md-2">
                                        <strong><p>其他图片:</p></strong>
                                </div>
                                <div class="container col-md-10">
                                        <label>请选择一个图像文件：</label>
                                        <button id="select">(重新)选择图片</button>
                                        <button id="add">(继续添加)图片</button>
                                        <button class="btn btn-danger" id="uploadBtn">立刻发布</button>
                                        <button class="btn btn-info">重新填写</button>
                                        <form  enctype="multipart/form-data" name="form2">
                                                <div><input id="file_input" type="file" name="subImageFiles" multiple accept="image/png,image/jpg,image/gif,image/JPEG"></div>
                                        </form>
                                </div>
                        </div>
                </div>
        </div>
</div>
</body>
<script>
    function change() {
        var pic = document.getElementById("preview"),
            file = document.getElementById("f");
        var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();
        if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
            alert("图片的格式必须为png或者jpg或者jpeg格式！");
            return;
        }
        var isIE = navigator.userAgent.match(/MSIE/)!= null,
            isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;
        if(isIE) {
            file.select();
            var reallocalpath = document.selection.createRange().text;
            if (isIE6) {
                pic.src = reallocalpath;
            }else {
                pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
                pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
            }
        }else {
            html5Reader(file);
        }
    }
    function html5Reader(file){
        var file = file.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            var pic = document.getElementById("preview");
            pic.src=this.result;
        }
    }
</script>
<script type="text/javascript">
    var fd = new FormData();
    window.onload = function(){
        var input = document.getElementById("file_input");
        var result;
        var dataArr = [];
        var oSelect = document.getElementById("select");
        var oAdd = document.getElementById("add");
        var oSubmit = document.getElementById("submit");
        var oInput = document.getElementById("file_input");
        if(typeof FileReader==="undefined"){
            alert("抱歉，你的浏览器不支持 FileReader");
            input.setAttribute("disabled","disabled");
        }else{
            input.addEventListener("change",readFile,false);
        }

        function readFile(){

            var iLen = this.files.length;
            var fileList = this.files;
            for(var i=0;i<iLen;i++){
                var reader = new FileReader();
                /*------*/
                fd.append("subImageFiles",$("#file_input")[0].files[i]);
                /*------*/
                reader.readAsDataURL(this.files[i]);
                reader.fileName = this.files[i].name;
                reader.onload = function(e){
                    var imgMsg = {
                        name : this.fileName,
                        base64 : this.result
                    }
                    dataArr.push(imgMsg);
                    result = '<div class="delete">delete</div><div class="result"><img class="subPic" src="'+this.result+'" alt="'+this.fileName+'"/></div>';
                    var div = document.createElement('div');
                    div.innerHTML = result;
                    div["className"] = "float";
                    document.getElementsByTagName("body")[0].appendChild(div);
                    var img = div.getElementsByTagName("img")[0];
                    img.onload = function(){
                        var nowHeight = ReSizePic(this);
                        this.parentNode.style.display = "block";
                        var oParent = this.parentNode;
                        if(nowHeight){
                            oParent.style.paddingTop = (oParent.offsetHeight - nowHeight)/2 + "px";
                        }
                    }
                    div.onclick = function(){
                        $(this).remove();
                    }
                }
            }
        }

        oSelect.onclick=function(){
            oInput.value = "";
            $('.float').remove();
            oInput.click();
        }
        oAdd.onclick=function(){
            oInput.value = "";
            oInput.click();
        }
        oSubmit.onclick=function(){
            if(!dataArr.length){
                return alert("请先选择文件");
            }
            send();
        }
    }

    function ReSizePic(ThisPic) {
        var RePicWidth = 200;
        var TrueWidth = ThisPic.width;
        var TrueHeight = ThisPic.height;
        if(TrueWidth>TrueHeight){
            var reWidth = RePicWidth;
            ThisPic.width = reWidth;
            var nowHeight = TrueHeight * (reWidth/TrueWidth);
            return nowHeight;
        }else{
            var reHeight = RePicWidth;
            ThisPic.height = reHeight;
        }
    }

        $(function () {
            showLivestock("#livestockLabel");
            showCategory("#categoryName");
        });

        function showLivestock(ele){
            $.ajax({
                url:"${APP_PATH}/livestock/get_all_livestock.do",
                type:"get",
                success:function(result){
                    // console.log(result);
                    $.each(result.data,function(){
                        var optionEle = $("<option></option>").append(this.label+" "+this.varieties).attr("value",this.label);
                        optionEle.appendTo(ele);
                    });
                }
            });
        }

        function showCategory(ele){
            $.ajax({
                url:"${APP_PATH}/category/get_all_category.do",
                type:"get",
                success:function(result){
                    // console.log(result);
                    $.each(result.data,function(){
                        var optionEle = $("<option></option>").append(this.name).attr("value",this.id);
                        optionEle.appendTo(ele);
                    });
                }
            });
        }

        var click = $("#uploadBtn").click(function(){

            var categoryId = $("#categoryName").val();
            var livestockLabel = $("#livestockLabel").val();
            var productTitle = $("#title").val();
            var productSubTitle = $("#subTitle").val();
            var file = $("#f")[0].files[0];
            var detail = $("#detail").val();
            var price = $("#price").val();
            if(price.length == 0){
                alert("价格不能为空！")
                return;
            }
            var stock = $("#stock").val();
            var status = $("#status").val();

            fd.append("categoryId",categoryId);
            fd.append("livestock",livestockLabel);
            fd.append("name",productTitle);
            fd.append("subtitle",productSubTitle);
            fd.append("detail",detail);
            fd.append("price",price);
            fd.append("file",file);
            fd.append("stock",stock);
            fd.append("status",status);

            $.ajax({
                url:"${APP_PATH}/product/release_product.do",
                type:"POST",
                cache : false,
                processData : false,
                contentType : false,
                dataType:"json",
                mimeType:"multipart/form-data",
                data:fd,
                success:function(result){
                    console.log(result);
                    if(result.status == 0){
                        alert(result.msg);
                        window.location.reload();
                    }else{
                        alert(result.msg);
                    }
                }
            });
            // alert("wait");
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
</html>