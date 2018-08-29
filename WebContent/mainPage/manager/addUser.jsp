<%@page import="bean.library.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加读者</title>
</head>
<body>
	<h1 align="center">添加读者</h1>
	<%
		String message = (String)request.getAttribute("message");
		String first = (String)request.getParameter("first");
		
		User user = (User)request.getAttribute("user");
		
		if(message==null){
			message = "";
		}
		if(user==null){
			user = new User();
		}
	%>
	<h3 align = "center" style="color:red;"><%=message %></h3>
	<center>
		<form action = "/DXLibrary/AddUserServlet" method = "post">
			<table>
				<tr>
					<td>编号</td>
					<td><input type = "text" name = "userId" value = "<%=user.getId() %>"/></td>
				</tr>
				<tr>
					<td>姓名</td>
					<td><input type = "text" name = "userName" value = "<%=user.getName() %>"/></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type = "password" name = "userPassword" value = "<%=user.getPassword() %>"/></td>
				</tr>
				<tr>
					<td>电话</td>
					<td><input type = "text" name = "phone" value = "<%=user.getPhone() %>"/></td>
				</tr>
				<tr>
					<td>邮箱</td>
					<td><input type = "text" name = "mail" value = "<%=user.getMail() %>"/></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input type = "text" name = "address" value = "<%=user.getAddress() %>"/></td>
				</tr>
				<% 
					if(first!=null && first.equals("1")){
				%>
					<tr><td colspan="2"><input type = "submit" value = "添加"/></td></tr>
				<%
					}
				%>
			</table>
		</form>
	</center>

</body>
</html>