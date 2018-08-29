<%@page import="bean.library.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
<style type="text/css">
	.td1{
		height:26px;
		width: 140px;
	}
	.td2{
		height: 26px;
		
	}
</style>
</head>
<body>
	<%
		User user = (User)session.getAttribute("userInfo");
		if(user==null){
	%>
			<h1>用户信息为空</h1>
	<%
		}
		else{
	%>
	<h1 align="center">用户信息</h1>
	<center>
		<table>
			<tr>
				<td class = "td1">编号</td>
				<td class = "td2"><%=user.getId() %></td>
			</tr>
			<tr>
				<td class = "td1">姓名</td>
				<td class = "td2"><%=user.getName() %></td>
			</tr>
			<tr>
				<td class = "td1">电话号码</td>
				<td class = "td2"><%=user.getPhone() %>
			</tr>
			<tr>
				<td class = "td1">电子邮箱</td>
				<td class = "td2"><%=user.getMail() %>
			</tr>
			<tr>
				<td class = "td1">用户地址</td>
				<td class = "td1"><%=user.getAddress() %>
			</tr>
			<tr>
				<td class = "td1">账户余额</td>
				<td class = "td2"><%=user.getBalance() %></td>
			</tr>
		</table>
	</center>
	<%} %>
	
</body>
</html>