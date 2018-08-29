<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
</head>
<body>
	<%
		if(request.getAttribute("message")==null){
			request.setAttribute("message", "");
		}
	%>
	<h1 align="center">修改用户密码</h1>
	<h2 style="text-align: center;color:red;" ><%=request.getAttribute("message") %></h2>

	<center>
		<form action = "/DXLibrary/ResetPasswordServlet" method = "post">
			<table>
				<tr>
					<td>输入原密码</td>
					<td><input type = "password" name = "oldPassword"/></td>
				</tr>
				<tr>
					<td>输入新密码</td>
					<td><input type = "password" name = "newPassword"/></td>
				</tr>
				<tr>
					<td>确认新密码</td>
					<td><input type = "password" name = "surePassword"/></td>
				</tr>
				<tr>
					<td colspan="2" align = "center"><input type = "submit" name = "submit" value = "确认"/></td> 
				</tr>
			</table>
		</form>
	</center>
</body>
</html>