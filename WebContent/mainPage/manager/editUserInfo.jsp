<%@page import="database.Database"%>
<%@page import="bean.library.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑用户信息</title>
<style type = "text/css">
	tr{
		width:160px;
		heght:40px;
	}
</style>



</head>
<%
	String userId = request.getParameter("userId");
	User user = Database.getUserById(userId);
	String message = request.getParameter("message");
	if(message==null){
		message = "";
	}
%>
<script type="text/javascript">
function check(){
	
	if(document.getElementById('userid').value==""){
		alert("编号不能为空");
	}else if(document.getElementById('username').value==""){
		alert("姓名不能为空");
	}else if(document.getElementById('password').value==""){
		alert("密码不能为空");
	}else{
		document.change.submit();
	}

}
</script>

<body>
<center>
	<h1 align = "center">修改用户&nbsp;<%=user.getName() %>&nbsp;的信息</h1>
	<h3 align = "center" style = "color:red"><%=message %></h3>
	<form action = "/DXLibrary/EditUser" method = "post" name = "change" id = "change">
		<input type="hidden" name = "oldID" value = "<%=user.getId() %>" />
		<table style = "padding: 6px;">
			<tr>
				<td>编号</td>
				<td class="s2"><input type = "text" value = "<%=user.getId() %>" name = "userid" id = "userid"/></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input type = "text" value = "<%=user.getName() %>" name = "username" id = "username"/></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type = "text" value = "<%=user.getPassword() %>" name = "password" id = "password"/></td>
			</tr>
			<tr>
				<td>电话</td>
				<td><input type = "text" value = "<%=user.getPhone() %>" name = "phone"/></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type = "text" value = "<%=user.getMail() %>" name = "mail"/></td>
			</tr>
			<tr>
				<td>地址</td>
				<td><input type = "text" value = "<%=user.getAddress() %>" name = "addr"/></td>
			</tr>
			<tr>
				<td colspan="2">
				<input type = "button" value = "修改" style="width: 100px;" onclick="check()" /> &nbsp;
				<input type = "button" style="width: 100px;" value = "返回" onclick = "window.location.href('/DXLibrary/AllUserServlet')"/>
				</td>
			</tr>
				
		</table>
		
	</form>
</center>

</body>
</html>