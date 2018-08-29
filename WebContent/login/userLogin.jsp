<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生登录</title>
<link rel = "stylesheet" type = "text/css" href = "/DXLibrary/cssStyle/style.css"/>
<style type = "text/css">
	h2{
		text-align: center;
		color:red;
	}
</style>

</head>
<body>
<%
	session.removeAttribute("userId");
	String message = (String)request.getAttribute("error");
	String uid = (String)request.getAttribute("id");
	String ps = (String)request.getAttribute("password");
	if(message == null){
		message = "";
	}
	if(uid==null){
		uid = "";
	}
	if(ps==null){
		ps = "";
	}
%>
	<div class = "head">
		<h1 style = "line-height: 100px;">图书管理系统</h1>
	</div>
	<div class = "left">
		<p>&nbsp;</p>
		<h2 style = "color:#CC1414;">静以修身，学以育德</h2>
		<h2 style = "color:#F0C30E;">一本好书，相伴一生</h2>
		<h2 style ="color:#1DF00E;">学而不思则罔，思而不学则殆</h2>
		<h2 style = "color:#3D45D5;">书山有路勤为径，学海无涯苦做舟</h2>
		<h2 style = "color:#0EF0A9;">踏着进步的阶梯，迈向心灵的彼岸</h2>
		<h2 style = "color:#C50B9D;">浸润书香，为人生奠基；畅游书海，享阅读快乐</h2>
	</div>
	<div class = "right" id = "main">
		<h2>学生登录</h2>
		<h4 style="text-align: center;color:red;" ><%=message %></h4>
		<form action = "/DXLibrary/UserLoginCheck" method = "post">
		<center>
			<table>
				<tr>
					<td style = "width: 40px;height: 30px;">账号</td>
					<td style = "width: 200px;height: 30px;"><input type = "text" name = "id" value = "<%=uid%>" style = "width: 200px;height: 20px;"/>
				</tr>
				<tr>
					<td style = "width: 40px;height: 30px;">密码</td>
					<td style = "width: 200px;height: 30px;"><input type = "password" name = "password" value = "<%=ps%>" style = "width: 200px;height: 20px;"/></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type = "submit" style = "width: 240px;height: 30px;" value = "登录"/></td>

				</tr>
			</table>
		</center>
		</form>
		<p>&nbsp;</p>
		<a href = "/DXLibrary/login/managerLogin.jsp">管理员登录</a>
	</div>
	
	

</body>
</html>