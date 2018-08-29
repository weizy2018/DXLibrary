<%@page import="database.Database"%>
<%@page import= "bean.library.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更改联系方式</title>
<style type = "text/css">
	h1.{
		text-align: center;
		color:red;
	}
</style>

</head>
<body>
	<%
		User user = Database.getUserById((String)session.getAttribute("userId"));
		if(request.getParameter("message")==null){
			request.setAttribute("message", "");
		}
	%>
	<h1><%=request.getAttribute("message") %></h1>
	<center>
		<form action = "/DXLibrary/resetContactInformationServlet" method = "post">
			<table>
				<tr>
					<td>电话号码</td>
					<td><input type = "text" name = "phone" value = "<%=user.getPhone()%>"/></td>
				</tr>
				<tr>
					<td>电子邮箱</td>
					<td><input type = "text" name = "mail" value = "<%=user.getMail()%>"/></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input type = "text" name = "address" value = "<%=user.getAddress()%>"/></td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td colspan = "2"><input type = "submit" name = "submit" value = "修改"/></td>
			</table>
		</form>
	</center>
	
</body>
</html>