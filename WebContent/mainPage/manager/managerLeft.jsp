<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单栏</title>

<style type = "text/css">
	span{
		padding:5px;
		width:100%;
		height:30px;
		text-align: center;
		background-color: #938585;
		float:left;
		border:1px solid #eee;
	}
	a:link {color:#F2EE07;}
	a:visited{color:#F2EE07;}
	a:hover{color:#F2EE07;}
	a:active{color:#F2EE07;}
</style>

</head>
<body style="padding: 0px;margin: 0px;overflow: hidden;">
	<a href = "/DXLibrary/AllUserServlet" target="right"><span>编辑用户</span></a>
	<a href = "/DXLibrary/OverdueUserServlet" target = "right"><span>欠费读者</span></a>
	<a href = "/DXLibrary/mainPage/manager/resetManagerPassword.jsp" target = "right"><span>修改密码</span></a>
	<a href = "/DXLibrary/login/managerLogin.jsp" target = "_parent"><span>退出登录</span></a>
	
</body>
</html>