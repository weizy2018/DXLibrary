<%@page import="database.Database"%>
<%@page import="bean.library.Message"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统消息</title>
<style type="text/css">
		.v1{
			margin-left:auto;
			margin-right:auto;
			height: 400px;
			width: 360px;
		}
		.v2{
			padding: 10px;
			margin-left:auto;
			margin-right:auto;
			margin: 10px;
			background-color: #C0C0C0;
			width: 340px;
			height: 180px;
		}
	</style>

</head>
<%
	List<Message> msglist = Database.getSystemMessage((String)session.getAttribute("userId"));
%>

<body>
	<div class="v1">
	<h1 align = "center">系统消息</h1>
	<%
		if(msglist.size()==0){
	%>
			<h2>没有任何系统消息</h2>
	<%
		}else{
			for(int i=0;i < msglist.size();i++){
	%>			
			<div class = "v2">
				<h5>时间:<%=msglist.get(i).getTime()%></h5>
				内容：<br/>
				<%=msglist.get(i).getMessage() %>
			</div>
	<%
			}
		}
	%>
	</div>
	

</body>
</html>
