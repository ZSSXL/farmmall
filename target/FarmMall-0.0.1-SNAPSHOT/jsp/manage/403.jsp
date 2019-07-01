<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
</head>
<body>
<h1>您还没有登录，请以管理员身份登录</h1>
<a href="" target="_blank" onclick="toLogin()">点击登录</a>
</body>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    function toLogin() {
        window.open("${pageContext.request.contextPath}/manage/admin.do",'_new');
        window.close();
    }
</script>
</html>