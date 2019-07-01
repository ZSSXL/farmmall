<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">
    <title>FarmMall后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/xadmin/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/xadmin/css/xadmin.css">
    <script src="${pageContext.request.contextPath}/static/xadmin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/xadmin/js/xadmin.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body>
<div class="x-nav">
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">认证管理</a>
            <a>
              <cite>申请列表</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="location.reload()" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="username" placeholder="请输入用户名" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn"><i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>店铺名</th>
                            <th>申请人</th>
                            <th>性别</th>
                            <th>手机</th>
                            <th>邮箱</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageInfo.list}" var="buyer">
                            <tr>
                                <td>${buyer.shopName}</td>
                                <td>${buyer.username}</td>
                                <td>${buyer.sex == 1?'男':'女'}</td>
                                <td>${buyer.phone}</td>
                                <td>${buyer.email}</td>
                                <td class="td-status">
                                    <span class="layui-btn-danger layui-btn-normal layui-btn-xs" style="padding: 10px">申请中</span>
                                </td>
                                <td class="td-manage">
                                    <a onclick="member_pass(this,'${buyer.id}')" href="javascript:;" title="申请中">
                                        <i class="layui-btn layui-btn-normal layui-btn-mini">同意</i>
                                    </a>
                                    <a onclick="member_stop(this,'${buyer.id}')" href="javascript:;" title="申请中">
                                        <i class="layui-btn layui-btn-danger layui-btn-mini">拒绝</i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="layui-card-body ">
                    <div class="page">
                        <div>
                            <a class="prev"
                               href="${pageContext.request.contextPath}/manage/queryAllBuyer.do?page=1&size=${pageInfo.pageSize}">&lt;&lt;</a>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                <a class="num active"
                                   href="${pageContext.request.contextPath}/manage/queryAllBuyer.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>

                            </c:forEach>
                            <a class="next"
                               href="${pageContext.request.contextPath}/manage/queryAllBuyer.do?page=${pageInfo.total}&size=${pageInfo.pageSize}">&gt;&gt;</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    layui.use(['laydate', 'form'], function () {
        var laydate = layui.laydate;
        var form = layui.form;


        // 监听全选
        form.on('checkbox(checkall)', function (data) {

            if (data.elem.checked) {
                $('tbody input').prop('checked', true);
            } else {
                $('tbody input').prop('checked', false);
            }
            form.render('checkbox');
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });


    });

    /*同意申请*/
    function member_pass(obj, id) {
        layer.confirm('确认执行该操作吗？', function (index) {

            if ($(obj).attr('title') == '申请中') {

                $(obj).attr('title', '同意');
                $(obj).find('i').html('已同意');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已同意');
                layer.msg('已同意!', {icon: 6, time: 1000});
                //发异步把用户状态进行更改
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/manage/applyById.do",
                    data: {
                        "id": id,
                    },
                    success: function () {
                        window.location.reload();
                    }

                });
            }
        });
    }
    /*拒绝申请*/
    function member_stop(obj, id) {
        layer.confirm('确认执行该操作吗？', function (index) {

            if ($(obj).attr('title') == '申请中') {

                $(obj).attr('title', '拒绝');
                $(obj).find('i').html('已拒绝');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已拒绝');
                layer.msg('已拒绝!', {icon: 5, time: 1000});
                //发异步把用户状态进行更改
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/manage/ReApplyById.do",
                    data: {
                        "id": id,
                    },
                    success: function () {
                        window.location.reload();
                    }

                });
            }
        });
    }
    /*用户-删除*/
    function member_del(obj, id) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!', {icon: 1, time: 1000});
        });
    }


    function delAll(argument) {
        var ids = [];

        // 获取选中的id
        $('tbody input').each(function (index, el) {
            if ($(this).prop('checked')) {
                ids.push($(this).val())
            }
        });

        layer.confirm('确认要删除吗？' + ids.toString(), function (index) {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }

</script>
</html>