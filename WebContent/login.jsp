<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="bootstrap_plugins/css/bootstrap.css">
	<script src="bootstrap_plugins/js/jquery-1.11.3.min.js"></script>
	<script src="bootstrap_plugins/js/bootstrap.js"></script>
	<style>
	#loginbody{
		border:solid 1px grey;
		width:400px;
		height:300px;
		margin-top:100px;
		background:url("lm1.jpg");
		background-size:500px 400px;
	}
	.class{
		background:#1a38ca;
		text-align:center;
		font-size:35px;
	}
	</style>
</head>
<body>
<form action="LoginServlet" method="post">
<div class="container">
	<div class="class"  class="text-center" >用户信息管理系统</div>
	<div class="row">
	<div class="col-md-offset-4" id="loginbody">
	<p class="text-center" style="font-size:30px">登录</p>
	<label for="exampleInputEmail1">用户名</label>
	<input type="text" class="form-control" id="username" name="username" placeholder="用户名">
	<label for="exampleInputEmail1">密码</label>
	<input type="password" class="form-control" id="password" name="password" placeholder="密码">
	<input type="checkbox">记住用户名
	<br>
	<form action="${pageContext.request.contextPath}/checkCode" method="post">
         请输入验证码：<input type="text" name="code" style="width: 80px;" /> <img id="imgObj" alt="***"
             src="${pageContext.request.contextPath}/getCode"><a href="#" onclick="changeImg()">换一张</a><br/> 
     </form>
<!-- 	<tr> <td>验证码：</td> <td><input type="text" name="verifyCode" /></td> <td>  -->
<!-- 	<img id="code" src="verify/code.action">  -->
<!-- 	<a id="refresh">看不清，换一张</a> </td> </tr> -->
<!--     </form> -->
	</br></br>
	<button type="submit" class="btn btn-success">登录</button>
	<button type="button" class="btn btn-info">重置</button>
	</div>
	</div>
	</div>
	</form>
</body>
</html>