<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page language="java" import="java.util.*,com.bbu.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户信息界面</title>
	<link rel="stylesheet" type="text/css" href="bootstrap_plugins/css/bootstrap.css">
	<script src="bootstrap_plugins/js/jquery-1.11.3.min.js"></script>
	<script src="bootstrap_plugins/js/bootstrap.js"></script>
</head>
<body>
<% User user1 = (User)request.getSession().getAttribute("user"); %>
	<div class="container">
<!-- container-fluid -->
	<div class="col-md-12" style="background:#4072B1;color:white">
	<h1 style="text-align:center">用户信息管理系统</h1>
	</div>
	<div class="col-md-2 col-md-offset-10">
	<h4>欢迎您，<%=user1.getUsername() %>&nbsp;&nbsp;&nbsp;&nbsp;<a href="UserServlet?type=quit">退出编辑</a></h4>
	</div>
	<h3 style="text-align:center">修改用户信息</h3>
	<hr>
	<form class="form-horizontal" action="UserServlet?type=modify" method="post">
<!-- 	<div class="row"> -->
	<!-- <div class="col-md-2 col-md-offset-2" id="mybody"> -->
	<div class="form-group">
	<input type="hidden" value=${user.id} name="id"/>
	<label for="inputUserName" class="col-sm-2 control-label">姓名：</label>
	<div class="col-sm-10">
	<input type="text" name="username" id="inputUserName" placeholder="姓名" class="form-control" value=${user.username}></div></div>
	<div class="form-group">
	<label for="inputUserName" class="col-sm-2 control-label">密码：</label>
	<div class="col-sm-10">
	<input type="text" name="password" id="inputPassword" placeholder="密码" class="form-control" value=${user.password}></div></div>
	<div class="form-group">
	<label for="inputGrade" class="col-sm-2 control-label">等级：</label>
	<div class="col-sm-10">
	<input type="text" name="grade" id="inputGrade" placeholder="等级" class="form-control" value=${user.grade}></div></div>
	<div class="form-group">
	<label for="inputUserName" class="col-sm-2 control-label">邮箱：</label>
	<div class="col-sm-10">
	<input type="text" name="email" id="inputEmail" placeholder="邮箱" class="form-control" value=${user.email}></div></div>
	<div class="form-group">
	<div class="col-sm-10 col-md-offset-2">
	<button type="submit" class="btn btn-success">确定</button>
	<button type="reset" class="btn btn-warning">取消</button>
	</div>
	</div>
	</div>
	</form>
	</div>
</body>
</html>