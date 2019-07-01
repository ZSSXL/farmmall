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
        <link  rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/forget.css">
        <link rel="stylesheet" href="${APP_PATH}/static/bootstrap3.3.7/css/bootstrap.min.css">
        <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${APP_PATH}/static/css/style.css">
        <script type="text/javascript" src="${APP_PATH}/static/js/jquery3.4.1.min.js"></script>
        <script type="text/javascript" src="${APP_PATH}/static/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <title>忘记密码</title>
</head>
<body>
<div class="box" style="background: rgba(255,255,255,0.2);">
        <div class="row">
                <div style="height: 100px;"><div class="lock"><span class="glyphicon glyphicon-lock"><strong>找回密码</strong></span></div></div>
        </div>
        <hr size="2px" color="rgba(31,31,31,1.00)">
        <div class="row">
                <div class="col-md-2 tail" style="background-color: #5595FF;"><span>1.确认账号</span></div>
                <div class="to_right" style="border-left: 20px solid #5595FF;"></div>
                <div class="tran">
                        <div class="col-md-2 tail1" style="background-color: #BECEE8;"><span>2.重置密码</span></div>
                        <div class="to_right1" style="border-left: 20px solid #BECEE8;"></div>
                </div>
        </div>
        <div><h4>请填写需要找回的账号信息：（以注册时为准）</h4></div>
        <div class="row">
                <form class="form-horizontal">
                        <div class="form-group">
                                <label class="col-md-3 control-label">您的用户名：</label>
                                <div class="col-md-5">
                                        <input id="username" class="form-control" placeholder="请输入姓名" type="text">
                                </div>
                        </div>
                        <div class="form-group">
                                <label class="col-md-3 control-label">密保问题：</label>
                                <div class="col-md-5">
                                        <input class="form-control" id="question" disabled="disabled" type="test">
                                </div>
                        </div>
                        <div class="form-group">
                                <label class="col-md-3 control-label">你的答案：</label>
                                <div class="col-md-5">
                                        <input id="answer" class="form-control" type="text">
                                </div>
                        </div>
                </form>
        </div>
        <div>
              <button class="btn btn-default" id="toNext" style="background-color: #5595FF;margin-left: 280px;margin-top: 20px; width: 200px;">下一步</button>
        </div>
</div>

<script type="text/javascript">

        $("#username").change(function () {
            // 发送ajax请求，通过用户名查询问题
            var username = this.value;
            $.ajax({
                url:"${APP_PATH}/user/forget_get_question.do",
                data:"username="+username,
                type:"GET",
                success:function(result){
                    //console.log(result);
                    $("#question").val(result.msg);
                }
            });
        });

        // 校验密保问题答案是否匹配
        $("#toNext").click(function () {
                var username = $("#username").val();
                var question = $("#question").val();
                var answer = $("#answer").val();
                $.ajax({
                    url:"${APP_PATH}/user/forget_check_answer.do",
                    data:"username="+username+"&question="+question+"&answer="+answer,
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
                window.location.href = "${APP_PATH}/jsp/resetpsw.jsp";
            }
        }
</script>
</body>
</html>
