<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<title>상세일정</title>
</head>
<body>
<div class="container my-5">
	<div align="center">
		<h3> ${date} SCHEDULE</h3>
		<form action="${pageContext.request.contextPath}/index" method="post">
			<input name="control" value="request" hidden="true" readonly="readonly">
			<input name="requestYear" value="${requestYear}" hidden="true" readonly="readonly">
			<input name="requestMonth" value="${requestMonth}" hidden="true" readonly="readonly">
			<button class="btn btn-light" type="submit">CALENDAR </button>
		</form>
	</div>
	<table class="table table-strigh">
		<tr>
			<th></th>
			<th>MEMO</th>
			<th></th>
		</tr>
		<c:forEach var="entry" items="${map}">
		    <tr>
		        <td></td>
		        <td>
				  	<form action="${pageContext.request.contextPath}/schedule?date=${date}" method="post">
				  		<input name="control" value="edit" hidden="true" readonly="readonly">
				  		<input name="scheduleNo" value="${entry.key}" hidden="true" readonly="readonly">
				  		<div class="row">
				  			<div class="col">
				  				<input name="editMemo" class="form-control" value="${entry.value}" >
				  			</div>
				  			<div class="col col-1">
				  				<input class="btn btn-light" type="submit" value="✏️">
				  			</div>
				  		</div>
				  	</form>
				</td>
		        <td>
				  	<form action="${pageContext.request.contextPath}/schedule?date=${date}" method="post">
				  		<input name="scheduleNo" value="${entry.key}" hidden="true" readonly="readonly">
				  		<input name="control" value="remove" hidden="true" readonly="readonly">
				  		<input class="btn btn-light" type="submit" value="🗑️">
				  	</form>
		        </td>
		    </tr>
		</c:forEach>
		<tr>
		  	<form action="${pageContext.request.contextPath}/schedule?date=${date}" method="post">
				<td width="80 px">
			  		<input name="control" value="add" hidden="true" readonly="readonly">
	  				<select class="form-select form-select-sm mt-1" name="emoticon">
						<option value=" "> </option>
						<option value="😊">😊</option>
						<option value="💎">💎</option>
						<option value="📰">📰</option>
						<option value="🎁">🎁</option>
						<option value="👟">👟</option>
						<option value="🎸">🎸</option>
					</select>
				</td>
				<td>
				  	<div class="row">
				  		<div class="col-12">
				  			 <input name="newMemo" type="text" class="form-control" placeholder="일정추가" >
				  		</div>
				  	</div>
				</td>
				<td>
				  	<input class="btn btn-light" type="submit" value="➕">
				</td>
		  	</form>
		</tr>
	</table>
	
</div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
</html>