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
        <link  rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/register.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${APP_PATH}/static/css/style.css">
        <title>注册页面</title>
</head>

<body>
<div class="box" style="background: rgba(255,255,255,0.2);">
        <div class="row">
                <div class="col-md-7">
                        <div class="row">
                                <div class="col-md-1"></div>
                                <div class="col-md-11"><h3><strong>用户注册</strong></h3></div>
                        </div>
                        <hr size="2px" style="color: rgba(195,195,195,1.00);">
                        <div class="row">
                                <form class="form-horizontal" id="registerForm">
                                        <div class="form-group">
                                                <label class="col-md-4 control-label">用户名：</label>
                                                <div class="col-md-6">
                                                        <input class="form-control" name="username" placeholder="中英文结合" type="text">
                                                </div>
                                        </div>
                                        <div class="form-group">
                                                <label class="col-md-4 control-label">密码：</label>
                                                <div class="col-md-6">
                                                        <input class="form-control" type="password" name="password" placeholder="请输入密码">
                                                </div>
                                        </div>
                                        <div class="form-group">
                                                <label class="col-md-4 control-label">确认密码：</label>
                                                <div class="col-md-6">
                                                        <input class="form-control" type="password" name="passwordConfirm" placeholder="请确认密码">
                                                </div>
                                        </div>
                                        <div class="form-group">
                                                <label class="col-md-4 control-label">手机号：</label>
                                                <div class="col-md-6">
                                                        <input class="form-control" type="tel" name="phone" placeholder="请输入手机号">
                                                </div>
                                        </div>
                                        <div class="form-group">
                                                <label class="col-md-4 control-label">邮箱：</label>
                                                <div class="col-md-6">
                                                        <input class="form-control" type="email" name="email" placeholder="请输入邮箱">
                                                </div>
                                        </div>
                                        <div class="form-group">
                                                <label class="col-md-4 control-label">密保问题:</label>
                                                <div class="col-md-6">
                                                        <select class="form-control" name="question" style="height: 40px;">
                                                                <option>问题</option>
                                                                <option>你的出生日期?</option>
                                                                <option>你配偶的出身日期?</option>
                                                                <option>你的小学班主任是谁?</option>
                                                                <option>你的好哥们叫什么?</option>
                                                        </select>
                                                </div>
                                        </div>s
                                        <div class="form-group">
                                                <label class="col-md-4 control-label">答案：</label>
                                                <div class="col-md-6">
                                                        <input class="form-control" name="answer" type="email" placeholder="请输入密保答案">
                                                </div>
                                        </div>
                                        <div class="single">
                                                <label>性别：</label>
                                                <input type="radio" name="sex" style="margin-left: 20px;" value="1" checked="checked">男
                                                <input type="radio" name="sex" style="margin-left: 10px;" value="0">女
                                        </div>
                                        <div>
                                                <input type="button"  class="btn btn-danger register"  id="registerBtn" value="立即注册">
                                                <input type="reset"  class="btn fill" value="重置">
                                        </div>

                                </form>
                        </div>
                </div>

                <div class="col-md-3">
                        <div class="row">
                                <div class="line"></div>
                                <div class="col-md-2"></div>
                                <div class="col-md-10" style="transform: translateY(-545px);"><h3><strong>我已注册账号</strong></h3></div>
                        </div>
                        <a href="${APP_PATH}/jsp/login.jsp"><button class="btn log">立即登录</button></a>
                </div>
        </div>
</div>
<script type="text/javascript">
        $("#registerBtn").click(function () {
                getRegister();
        });

        function getRegister(){
            $.ajax({
                type:"post",
                url:"${APP_PATH}/user/register.do",
                data:$("#registerForm").serialize(),
                success:function(result){
                    judgeMsg(result);
                }
            });
        };

        function judgeMsg(result){
            if(result.status == 0){
                alert(result.msg);
            }else if(result.status == 1){
                alert(result.msg);
            }
        }
</script>
</body>
</html>
