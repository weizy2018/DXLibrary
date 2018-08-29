<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import = "bean.library.Book" %>
<%@page import = "database.Database" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书</title>
</head>
<%	
	String first = request.getParameter("first");
	String message = (String)request.getAttribute("message");
	List<Book> bookList = new ArrayList<Book>();
	if(first!=null && first.equals("1")){
		System.out.println("libraryBooks.jsp" + first);
		bookList = Database.getAllBooks();
	}
	if(request.getAttribute("searchBook")!=null){
		
		bookList = (List<Book>)request.getAttribute("searchBook");
	}
	
	if(message==null){
		message = "";
	}
%>
<script type="text/javascript">
function checkForSubmit(){
	if(form1.keywords.value!="请输入书名/作者")
		form1.submit();
}

</script>


<body style = "margin: 0px;padding: 0px;">
	<div style ="height:90px;width:100%;float:left;background-color: gray;padding: 5px;margin: 0px;">
		&nbsp;<br/>
		<form name = "form1" action = "/DXLibrary/SearchBooks"  method = "post">
		<input type = "text" style = "height: 25px;width: 150px;" name = "keywords" value = "请输入书名/作者" onfocus="javascript:if(this.value=='请输入书名/作者') this.value=''" />&nbsp;&nbsp;&nbsp;
		<input type = "button" style = "height: 30px;width: 80px;" value = "馆藏查询" onclick = "checkForSubmit()"/>&nbsp;&nbsp;&nbsp;
		<input type = "button" style = "height: 30px;width: 80px;" onclick="window.location.href('/DXLibrary/mainPage/user/libraryBooks.jsp?first=1')" value = "图书列表"/>
		</form>
	</div>
	
	<div style = "height:400px;width:100%;">
		<h2 align = "center">藏书列表</h2>
		<h3 align = "center" style = "color:red;"><%=message %></h3>		
			<center>
				<%
					if(bookList.size()==0 && first!=null){
				%>
						<h3>图书馆暂时还没有图书</h3>
				<%
					}else if(bookList.size()==0){
				%>
						<h3>没有要找的图书</h3>
				<%
					}else{
				%>
				<table style="text-align: center;border-collapse:collapse;" border="1px;">
					<tr>
						<th>编号</th>
						<th>书名</th>
						<th>类别</th>
						<th>出版时间</th>
						<th>出版社</th>
						<th>作者</th>
						<th>数量</th>
						<th>借阅</th>
					</tr>
					<%
						for(int i=0;i<bookList.size();i++){
							Book book = bookList.get(i);
							int amount = book.getAmount();
					%>
					<tr>
						<td><%=book.getId() %></td>
						<td><%=book.getName() %></td>
						<td><%=book.getCategory() %></td>
						<td><%=book.getPublishTime() %></td>
						<td><%=book.getPublisher() %></td>
						<td><%=book.getAuthor() %></td>
						<td><%=book.getAmount() %></td>
						<%
							if(amount>0){
						%>
<%-- 							window.location.href('/DXLibrary/BorrowBook?bookId=<%=book.getId()%>') --%>
							<td><input type = "button" value = "借阅" onclick="window.location.href=('/DXLibrary/BorrowBook?bookId=<%=book.getId()%>')"/></td>
						<%
							}
						%>
						
					</tr>
					<%
						}
					%>
					
				</table>
				<%
					}
				%>
			</center>

	</div>
</body>
</html>