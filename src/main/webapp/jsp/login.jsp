<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
        <%
                pageContext.setAttribute("APP_PATH", request.getContextPath());
        %>
        <%
                session.removeAttribute("currentUser");
        %>
        <meta charset="utf-8">
        <title>农场品在线商城</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,maxium-scale=1,user-scalable=no">
        <link  rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/login.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${APP_PATH}/static/css/style.css">
        <title>登录页面</title>
</head>

<body>
<div class="container">
        <div class="form row">
                <div class="form-horizontal col-md-offset-3" id="login_form">
                        <h3 class="form-title">LOGIN</h3>
                        <div class="col-md-9">
                                <div class="form-group">
                                        <i class="fa fa-user fa-lg"></i>
                                        <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                                </div>
                                <div class="form-group">
                                        <i class="fa fa-lock fa-lg"></i>
                                        <input class="form-control required" type="password" placeholder="Password" id="password" name="password" maxlength="8"/>
                                </div>
                                <div class="form-group">
                                        <label class="checkbox">
                                                <input type="checkbox" name="remember" value="1"/>记住我
                                                <span style="margin-left: 20px;" class="label label-primary" id="forget_btn">忘记密码</span>
                                        </label>
                                </div>
                                <div class="form-group col-md-offset-9">
                                        <button  align="left" style="float:left" type="button" class="btn btn-info" id="register_btn">注册用户</button>
                                        <button type="submit" class="btn btn-success pull-right"  id="loginBtn" name="submit">登 录</button>
                                </div>
                        </div>
                </div>
        </div>
</div>
<script type="text/javascript">
        $("#register_btn").click(function () {
           window.location.href = "${APP_PATH}/jsp/register.jsp";
        });

        $("#forget_btn").click(function () {
           window.location.href = "${APP_PATH}/jsp/forget.jsp";
        });

        $("#loginBtn").click(function () {
            var username = $("#username").val();
            var password = $("#password").val();
                $.ajax({
                    type:"post",
                    url:"${APP_PATH}/user/login.do",
                    data:"username="+username+"&password="+password,
                    success:function(result){
                        judgeMsg(result);
                    }
                })
        });

        function judgeMsg(result){
            if(result.status == 1){
                alert(result.msg);
            }else if(result.status == 0){
                window.location.href = "${APP_PATH}/index.jsp";
            }
        }
</script>
</body>
</html>

