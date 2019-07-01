<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html  class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>FarmMall后台管理系统</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/xadmin/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/xadmin/css/login.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/xadmin/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/xadmin/lib/layui/layui.all.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">FarmMall后台管理系统</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form"  name="Form1">
        <input name="username" placeholder="用户名"  type="text" class="layui-input" id="username" onkeydown=KeyDown()>
        <hr class="hr15">
        <input name="password"  placeholder="密码"  type="password" class="layui-input" id="password" onkeydown=KeyDown()>
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" id="dologin" type="button" name="btnsubmit">
        <hr class="hr20" >
    </form>
</div>

<script>
    function KeyDown()
    {
        if (event.keyCode == 13)
        {
            event.returnValue=false;
            event.cancel = true;
            Form1.btnsubmit.click();
        }
    }
    $("#dologin").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username == "") {
            layer.msg('请先登录您的账户', {time: 2000, icon: 5, shift: 6}, function () {
            });

            return;
        }
        if (password == "") {

            layer.msg('密码不能为空，请输入密码', {time: 2000, icon: 5, shift: 6}, function () {
            });
            return;
        }


        var loadingIndex = null;

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/manage/doAJAXLogin.do",
            data: {
                "username": username,
                "password": password
            },
            beforeSend: function () {
                loadingIndex = layer.msg('处理中', {time: 1000,icon: 6});
            },
            success: function (result) {
         if (result.status==0) {
                    window.location.href = "${pageContext.request.contextPath}/jsp/manage/main.jsp";
                } else {
               layer.msg('账户或密码错误，请重试', {time: 2000, icon: 5, shift: 6}, function () {
                    });
               }

            }

        });

    });

</script>


</body>
</html>
