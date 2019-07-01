<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
        <%
                pageContext.setAttribute("APP_PATH", request.getContextPath());
        %>
        <meta charset="utf-8">
        <title>农场品在线商城</title>
        <meta name="viewport" content="width=device-width,initial-scale=1,maxium-scale=1,user-scalable=no">
        <link  rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/forget.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${APP_PATH}/static/css/style.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>找回密码</title>
</head>
<body>
<div class="box" style="background: rgba(255,255,255,0.2);">
        <div class="row">
                <div style="height: 100px;"><div class="lock"><span class="glyphicon glyphicon-lock"><strong>找回密码</strong></span></div>
                </div>
        </div>
        <hr size="2px" color="rgba(31,31,31,1.00)">
        <div class="row">
                <div class="col-md-2 tail" style="background-color: #BECEE8;">
                        <span>1.确认账号</span></div>
                <div class="to_right" style="border-left: 20px solid #BECEE8;"></div>
                <div class="tran">
                        <div class="col-md-2 tail1"  style="background-color: #5595FF;">
                                <span>2.重置密码</span>
                        </div>
                        <div class="to_right1" style="border-left: 20px solid #5595FF;"></div>
                </div>
        </div>
        <div class="row">
                <form class="form-horizontal">
                        <div class="form-group">
                                <label class="col-md-3 control-label">您的用户名：</label>
                                <div class="col-md-5">
                                        <input id="username" class="form-control" placeholder="请输入用户名" type="text">
                                </div>
                        </div>
                        <div class="form-group">
                                <label class="col-md-3 control-label">请输入密码：</label>
                                <div class="col-md-5">
                                        <input id="password" class="form-control" placeholder="请输入新密码" type="password">
                                </div>
                        </div>
                        <div class="form-group">
                                <label class="col-md-3 control-label">请确认密码：</label>
                                <div class="col-md-5">
                                        <input id="passwordConfirm" class="form-control" placeholder="请确认密码" type="password">
                                </div>
                        </div>
                </form>
        </div>
        <div>
                <button id="resetPasswordBtn" class="btn btn-default" style="background-color:#5595FF;margin-left: 300px;margin-top: 30px;width: 200px">提交</button>
        </div>
</div>
<script type="text/javascript">
        $("#resetPasswordBtn").click(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            var passwordConfirm = $("#passwordConfirm").val();
            $.ajax({
                url:"${APP_PATH}/user/forget_reset_password.do",
                data:"username="+username+"&passwordNew="+password,
                type:"POST",
                success:function(result){
                    judgeMsg(result);
                }
            });
        });

        function judgeMsg(result){
            if(result.status == 1){
                alert(result.msg);
            }else if(result.status == 0){
                alert(result.msg);
                window.location.href = "${APP_PATH}/jsp/login.jsp";
            }
        }
</script>
</body>
</html>
