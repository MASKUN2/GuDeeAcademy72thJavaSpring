<%@page import="vo.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container my-5">
	<H1>${sessionScope.member.memberId} 어서오세요^^</H1>
	<a href="<%=request.getContextPath()%>/member/create">/member/create</a><br>
	<a href="<%=request.getContextPath()%>/member/login">/member/login</a><br>
	<a href="<%=request.getContextPath()%>/member/logout">/member/logout</a><br>
	<a href="<%=request.getContextPath()%>/member/info">/member/info</a><br>
	</div>	

</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
</html>