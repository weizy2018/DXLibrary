<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="bean.library.Borrow"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>借阅信息</title>
</head>
<script type="text/javascript">
	function checkForReturn(flag, index){
		if(flag=="1"){
			if(confirm("此书已经逾期，还书需要交费，是否要继续？")){
				//交费还书
				window.location.href = "/DXLibrary/ReturnBookServlet?flag=1&index=" + index;
			}
		}else{
			//直接还书
			window.location.href = "/DXLibrary/ReturnBookServlet?flag=0&index=" + index;
		}
	}

</script>


<body>
	<%
		@SuppressWarnings("unchecked")
		List<Borrow> list = (List<Borrow>)session.getAttribute("userBorrow");
		String message = (String)request.getAttribute("message");
		if(message==null){
			message = "";
		}
	%>
	<h2 align = "center" style="color:red;"><%=message %></h2>
	<center>
		<table cellspacing="5" style="width:90%;text-align: center;padding: 6px;border-collapse:collapse;"  border="1px;">
			<tr>
				<th>书名</th>
				<th>出版社</th>
				<th>出版时间</th>
				<th>作者</th>
				<th>借出时间</th>
				<th>到期时间</th>
				<th>状态</th>
				<th>续借</th>
				<th>还书</th>
			</tr>
			<%	
				boolean flag = false;
				for(int i=0;i<list.size();i++){
						Borrow b = list.get(i);
						System.out.println(b.getPublishTime());
						String state;
						if(b.getLastTime()>30){
							flag = true;
							int day = (int)Math.ceil(b.getLastTime()-30);
							state = "逾期" + day +"天";
						}
						else{
							state = "未逾期";
						}
			%>
					<tr>
						<td><%=b.getBookName() %></td>
						<td><%=b.getPublisher() %></td>
						<td><%=b.getPublishTime() %></td>
						<td><%=b.getAuthor() %></td>
						<td><%=new Date(b.getBorrowDate().getTime())%><br><%=new Time(b.getBorrowDate().getTime()) %></td>
						<td><%=new Date(b.getDeadline().getTime()) %><br><%=new Time(b.getDeadline().getTime()) %></td>
						<td><%=state %></td>
						<%	
							System.out.println("borrowInfomation.jsp:" + b.getLastTime());
							if(b.getLastTime()>30){
						%>
								<td>欠费</td>
								<td><input type = "button" value = "还书" onclick="checkForReturn('1','<%=i%>')"/></td>
						<%
							}else if(b.getLastTime()>23 && b.getLastTime()<=30){
								
						%>
								<td>不可续借</td>
								<td><input type = "button" value = "还书" onclick="checkForReturn('0','<%=i%>')"/></td>
						<%
							}
							else{
						%>		
								<td><input type = "button" value = "续借" onclick="window.location.href('/DXLibrary/RenewServlet?borrowInfo=<%=i%>')"/></td>
								<td><input type = "button" value = "还书" onclick="checkForReturn('0','<%=i%>')"/></td>
						<%
							}
						%>
					</tr>
<!-- 					<tr> -->
<%-- 						<td style = "width:"><%=new Time(b.getBorrowDate().getTime()) %></td> --%>
<%-- 						<td><%=new Time(b.getDeadline().getTime()) %></td> --%>
<!-- 					</tr> -->
			<%
				}
			%>
		</table>
	</center>
</body>
</html>