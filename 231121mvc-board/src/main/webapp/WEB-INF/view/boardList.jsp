<%@page import="java.util.List"%>
<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	List<Board> boardList = (List<Board>)request.getAttribute("boardList");
	Integer currentPage = (Integer)request.getAttribute("currentPage");
	Boolean isLastPage = (Boolean)request.getAttribute("isLastPage");

%>
<!DOCTYPE html>

<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container m-5">
	<table class="table table-dark table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>수정일</th>
			</tr>
		</thead>
			<%for(Board board : boardList){%>
			<tr>
				<td><%=board.getBoardNo() %></td>
				<td><%=board.getBoardTitle() %></td>
				<td><%=board.getBoardContent() %></td>
				<td><%=board.getBoardWriter() %></td>
				<td><%=board.getCreatedate() %></td>
				<td><%=board.getUpdatedate() %></td>
			</tr>
			<%} %>
		<tbody>
		</tbody>
	</table>
	<%if(currentPage != 1) {%>
	<a href="./boardList?requestPage=<%=currentPage - 1%>">이전페이지</a>
	<%} %>
	<%if(!isLastPage) {%>
	<a href="./boardList?requestPage=<%=currentPage + 1%>">다음페이지</a>
	<%} %>
</div>
</body>
</html>