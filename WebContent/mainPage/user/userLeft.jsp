<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<body style="padding: 0px;margin: 0px;overflow: hidden;" >
		<a href = "/DXLibrary/UserInformationServlet" target="right"><span>用户信息</span></a>
		<a href = "/DXLibrary/mainPage/user/libraryBooks.jsp?first=1" target = "right"><span>图书借阅</span></a>
		<a href = "/DXLibrary/BorrowInfoServlet" target = "right"><span>借阅信息</span></a>
		<a href = "/DXLibrary/mainPage/user/resetPassword.jsp"target = "right"><span>修改密码</span></a>
		<a href = "/DXLibrary/mainPage/user/resetContactInformation.jsp" target = "right"><span>联系方式</span></a>
		<a href = "/DXLibrary/mainPage/user/systemMessage.jsp" target = "right"><span>系统消息</span></a>
		<a href = "/DXLibrary/login/userLogin.jsp" target = "_parent"><span>退出登录</span></a>
</body>
</html>