<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="contatiner m-5">
	<div class="row">
	<div class="col-3">
	</div>
	<div class="col">
	<table class="table border">
		<tr>
			<td class="p-3">
				<h3>TITLE : ${noticeDetail['noticeTitle']}</h3>
			</td>
		</tr>
		<tr>
			<td class="p-3">
				AUTHOR : ${noticeDetail['author']}
			</td>
		</tr>
		<tr height="500 px">
			<td class="p-3">
				${noticeDetail['noticeContent']}
			</td>
		</tr>
		<c:forEach var="map" items="${commentList}">
			<tr>
			 	<td>
			 		${map['commentContent']}
			 		<br>
			 		by: ${map['author']} 
			 	</td>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<form action="${pageContext.request.contextPath}/schedule?date=${date}" method="post">
				 <div class="row">
			  		<div class="col pl-3">
			  			 <input name="newMemo" type="text" class="form-control" placeholder="댓글추가" >
				  		</div>
				  		<div class="col-2" align="right">
				  		<input class="btn btn-light" type="submit" value="➕">
			  		</div>
				</div>
				  	
		  	</form>
			</td>
		</tr>
	</table>	
	</div>
	<div class="col-3">
	</div>
	</div>
</div>
</body>
</html>

</body>
</html>