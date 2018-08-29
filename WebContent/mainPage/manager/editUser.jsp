<%@page import="java.util.ArrayList"%>
<%@page import="bean.library.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑用户信息</title>
<style type = "text/css">
	.inputType{
		width: 200px;
		height:25px;
	}
	
</style>

</head>

<script type="text/javascript">
function checkForSubmit(){
	if(form1.userName.value!="请输入读者编号/姓名" && form1.userName.value!=""){
		
		form1.submit();
	}
	if(form1.userName.value ==""){
		
		form1.userName.value = "请输入读者编号/姓名";
	}
}
function ensure(uid){
	if(confirm("确定要删除该读者？")){
		window.location.href = "/DXLibrary/DeleteUserServlet?userId=" + uid;
	}
}
</script>

<%
	String message = request.getParameter("message");
	if(message==null){
		message = "";
	}
%>

<body style="padding: 0px;margin: 0px;">
	<div style ="height:90px;width:100%;float:left;background-color: gray;padding: 5px;margin: 0px;">

		<input type = "button" style = "height: 30px;width: 200px;" onclick="window.location.href('/DXLibrary/mainPage/manager/addUser.jsp?first=1')" value = "添加读者"/>&nbsp;&nbsp;&nbsp;
		<input type = "button" style = "height: 30px;width: 100px;" onclick="window.location.href('/DXLibrary/AllUserServlet')" value = "全部读者"/>
		<br/>
		<br/>
		<form action = "/DXLibrary/FindUserServlet" method = "post" name = "form1">
			<input type = "text" name = "userName" class="inputType" value = "请输入读者编号/姓名" onfocus="javascript:if(this.value=='请输入读者编号/姓名') this.value=''"/>&nbsp;&nbsp;&nbsp;
			<input type = "button" style = "height: 30px;width: 100px;"value = "查找读者" onclick = "checkForSubmit()"/>
		</form>
	</div>
	
	<div style = "height:400px;width:100%;">
		<h2 align = "center">读者列表</h2>
		<h3 style = "text-align: center;color:red;"><%=message %></h3>
		<%
			@SuppressWarnings("unchecked")
			List<User> userList = (ArrayList<User>)request.getAttribute("userList");
			if(userList.size()==0){
		%>
			<h3 align = "center" style="color:red">没有读者</h3>
		<%
			}
			else{
		%>
		
			<center>
				<table cellspacing="8" style="text-align: center;border-collapse:collapse;" border="1px;">
					<tr>
						<th>编号</th>
						<th>姓名</th>
						<th>电话</th>
						<th>住址</th>
						<th>邮箱</th>
						<th>编辑</th>
						<th>删除</th>
					</tr>
					<%
						for(int i=0;i<userList.size();i++){
							User user = userList.get(i);
					%>
							
							<tr>
								<td><%=user.getId() %></td>
								<td><%=user.getName() %></td>
								<td><%=user.getPhone() %></td>
								<td><%=user.getAddress() %></td>
								<td><%=user.getMail() %></td>
								<td><input type = "button" value = "编辑" onclick="window.location.href('/DXLibrary/mainPage/manager/editUserInfo.jsp?userId=<%=user.getId()%>')"/></td>
								<td><input type = "button" value = "删除" onclick = "ensure('<%=user.getId()%>')"></td>
							</tr>
					<%
						}
					%>
					
				</table>
				
			</center>
		<%
			}
		%>
	</div>

</body>
</html>



