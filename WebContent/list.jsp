<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*,com.bbu.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息管理系统</title>
	<link rel="stylesheet" type="text/css" href="bootstrap_plugins/css/bootstrap.css"> 
	<script src="bootstrap_plugins/js/jquery-1.11.3.min.js"></script>
	<script src="bootstrap_plugins/js/bootstrap.js"></script>
	<style >
		
			#mybody{
				width: 980px;	
				margin-top:50px;			
			}			
	</style>
	<script type="text/javascript">
		function del(){
			return window.confirm("删除用户？");
		}
		function select_all(){
			var myselect = document.getElementsByName("myselect");
			var mycheck = document.getElementById("all");  //id
			if(mycheck.checked == false){
				for(var i = 0;i< myselect.length;i++){
					myselect[i].checked = false;
				}
			}else{
				for(var i = 0;i< myselect.length;i++){
					myselect[i].checked = true;
				}
			}
		}
// 		function deleteBySelected(){
// 			  // 获取input标签下type类型为checkbox的所有选中的checked框 
// 			     var option = $("input:checkbox:checked"); 
// 			  //取出checked框中的值
// 			     var checkedId = ""; 
// 			     var boo=true;  
// 			     for (var i = 0, len = option.length; i < len; i++) { 
// 			         if (boo) {   
// 			                 boo=false; 
// 			                 checkedId += option[i].value; 
// 			            } 
// 			         else
// 			             checkedId += ","+option[i].value;  
// 			     } 
// 			  var flag = window.confirm("你确定要删除这条记录吗");
// 			   //alert(checkedId);
// 			  if(flag){
// 			  window.location.href ="UserServlet?request.getRequestDispatcher("list.jsp").forward(request, response)s";
// 			  }
// 			 }
$(function(){
	$("#mybtn").click(function(){
		var statu=confirm("确认删除选中项？");
		if(!statu){
			return false;
		}else{
			$("#doSubmit").click();
		}
	});
})
</script>
<script> window.history.go(1);</script>
</head>
<body>
<%  ArrayList<User> allUsers = (ArrayList<User>)request.getAttribute("allUsers");
	User myuser = (User)request.getSession().getAttribute("user");
%>
<div class="container-fluid">
		<div class="row">
		
			<div class="col-md-12" style="background:#4072B1;color:white">
			<h1 style="text-align:center">用户信息管理系统</h1>			
			</div>
		<div class="col-md-2 col-md-offset-10" >			
			<h4>欢迎您，<%=myuser.getUsername() %>&nbsp;&nbsp;&nbsp;&nbsp;<a href="UserServlet?type=destory">安全退出</a></h4>
		</div>	
		</div>
			<div class="row">
			<!--12列栅格系统，div占用3，向右偏移3 -->
				<div class="col-md-2 col-md-offset-2" id="mybody">
					<hr/>
					<div style="float:left;margin-right:550px">
					<button type="button" class="btn btn-primary"  onclick="window.location.href='userAdd.jsp'">添加用户</button>
					<button type="button" class="btn btn-primary" id="mybtn" onclick="deleteBySelected();">批量删除</button>
<!-- 					 href="UserServlet?type=deleteUserBySelected" -->
					</div>					
					<form id="form" class="form-inline">
  						<div class="form-group">
    					<input type="text" class="form-control" placeholder="关键字">
    					<button type="button" class="btn btn-primary" onclick="window.location.href='#'">查询</button>
  						</div>
  					</form>
  					<hr/> 
  					<form action="UserServlet?type=delBySelected" method="post"> 					
					<table class="table table-hover">
						<tr class="info">
						<th><input type="checkbox" id="all" onclick="select_all()"/>全选</th>
						<th>id</th><th>用户名</th><th>密码</th><th>等级</th><th>邮箱</th><th>操作</th>
						<c:forEach items="${pageBean.recordList }" var="user">
						<%-- ${allUsers } --%>
						<tr>
						<td><input type="checkbox" name="myselect" value="${user.id}"></td>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.password}</td>
						<td>${user.grade}</td>
						<td>${user.email}</td>
						<td><a  onclick="return del()" href="UserServlet?type=delete&id=${user.id }" >删除</a>&nbsp;&nbsp;<a href="UserServlet?type=modifyUi&id=${user.id }">编辑</a></td>
						</tr>
						</c:forEach>
<!-- 						</tr> -->
<%-- 						<%for(int i = 0;i <allUsers.size();i++){ --%>
<!-- 							User user = allUsers.get(i); -->
<%-- <%-- 						%>
<!-- 						<tr > -->
<!-- 							<td><input type="checkbox" name="myselect"/></td> -->
<%-- 							<td><%=user.getId() %></td> --%>
<%-- 							<td><%=user.getUsername() %></td> --%>
<%-- 							<td><%=user.getPassword() %></td> --%>
<%-- 							<td><%=user.getGrade() %></td> --%>
<%-- 							<td><%=user.getEmail() %></td> --%>
<%-- 							<td><a href="UserServlet?type=delete&id=<%=user.getId() %>" onclick="return del();">删除</a>&nbsp;&nbsp;<a href="#">编辑</a></td> --%>
<!-- 						</tr> -->
<%-- 						<% } %> --%>
</table>
<input type="submit" value="aaa" id="doSubmit" style="display:none">
</form>
		<div class="row">
			<div style="text-align:center">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
							<c:forEach begin="1" end="${pageBean.pageCount}" var="i">
								<li><a href="PageServlet?currentPage=${i }">${i }</a></li>
							</c:forEach>
							<li><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
</div>
</body>
</html>