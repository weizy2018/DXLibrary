<%@page import="bean.library.OverdueBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>逾期列表</title>
</head>
<%
	@SuppressWarnings("unchecked")
	List<OverdueBean> overlist = (List<OverdueBean>)session.getAttribute("overlist");

	String message = request.getParameter("message");
	if(message==null){
		message = "";
	}
	
%>
<script type="text/javascript">
function checkForSubmit(){
	if(form1.information.value ==""){
		
		alert("消息不能为空");
	}else{
		form1.submit();
	}
		
}
</script>

<body style = "margin: 6px;padding: 8px;">
	<%
		if(overlist.size()==0){
	%>
			<h2 align = "center">没有逾期的读者</h2>
	<%
		}else{
	%>
			<form action = "/DXLibrary/SendMessageServlet" method = "post" name = "form1">
				在这里输入要发送的信息（不超过100字)<br/>
				<textarea rows="5" cols="50" maxlength="100" placeholder="在这里输入要发送的信息(最多100字)" name = "information"></textarea><br/>
				<input type = "button" value = "催促还书" style="width: 160px; height: 30px" onclick = "checkForSubmit()" />
			</form>
			<p align = "center" style = "color:red;">&nbsp;<%=message %></p>
			<h2 align="center">逾期列表</h2>
			<p>&nbsp;</p>
			<center>
				<table cellspacing="8" style="text-align: center;border-collapse:collapse;" border="1px;">
					<tr>
						<th>读者编号</th>
						<th>读者姓名</th>
						<th>图书编号</th>
						<th>读书名称</th>
						<th>借书日期</th>
						<th>逾期天数</th>
						<th>欠费金额</th>
						<th>总金额</th>
					</tr>
					<%
						for(int i=0;i<overlist.size();i++){
							String id = overlist.get(i).getUserId();
							int j = i,n = 0;
							double total = 0.0d;
							while(j<overlist.size() && overlist.get(j).getUserId().equals(id)){
								System.out.println("overlist.get(j).getMoney():" + overlist.get(j).getMoney() + "   total:" + total);
								total += overlist.get(j).getMoney();
								j++;
								n++;
							}
							System.out.println("overdue.jsp:KKKKKKKKKKKKKKKKKK:" + total);
					%>
							<tr>
								<td rowspan="<%=n%>"><%=overlist.get(i).getUserId() %></td>
								<td rowspan="<%=n%>"><%=overlist.get(i).getUserName() %></td>
								<td><%=overlist.get(i).getBookId() %></td>
								<td><%=overlist.get(i).getBookName() %></td>
								<td><%=overlist.get(i).getBorrowDate() %></td>
								<td><%=overlist.get(i).getDay() %></td>
								<td><%=overlist.get(i).getMoney() %></td>
								<td rowspan="<%=n %>"><%=total %></td>
							</tr>
					<%
							i++;
							while(i<j){
								
					%>
								<tr>
									<td><%=overlist.get(i).getBookId() %></td>
									<td><%=overlist.get(i).getBookName() %></td>
									<td><%=overlist.get(i).getBorrowDate() %></td>
									<td><%=overlist.get(i).getDay() %></td>
									<td><%=overlist.get(i).getMoney() %></td>
								</tr>
					<%
								i++;
							}
							i--;
						}
					%>
		
				</table>
			</center>
	<%
		}
	%>

</body>

</html>