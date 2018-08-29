<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改管理员密码</title>
</head>
<%		
		String message = request.getParameter("message");
		if(message==null){
			message = "";
		}
%>
<body>
	<h1 align="center">修改管理员密码</h1>
	
	<h2 style="text-align: center;color:red;" ><%=message %></h2>

	<center>
		<form action = "/DXLibrary/ResetManagerPasswordServlet" method = "post">
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