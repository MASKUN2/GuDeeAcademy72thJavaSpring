<%@page import="vo.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  <style>
    .calendar-day {
      border: 1px solid #ddd;
      padding: 10px;
      height: 160px; /* 각 셀의 높이 조절 */
      font-size: 18px; /* 원하는 텍스트 크기로 조절 */
	  font-weight: bold; /* 텍스트를 굵게 만듦 */
    }
    .calendar-saturday {
      border: 1px solid #ddd;
      padding: 10px;
      height: 160px; /* 각 셀의 높이 조절 */
      color: #0000ff;
      font-size: 18px; /* 원하는 텍스트 크기로 조절 */
	  font-weight: bold; /* 텍스트를 굵게 만듦 */
    }
    .calendar-sunday {
      border: 1px solid #ddd;
      padding: 10px;
      height: 160px; /* 각 셀의 높이 조절 */
      color: #ff0000;
      font-size: 18px; /* 원하는 텍스트 크기로 조절 */
	  font-weight: bold; /* 텍스트를 굵게 만듦 */
    }
    .calendar-dayofweek {
      border: 1px solid #ddd;
      padding: 10px;
      height: 60px; /* 각 셀의 높이 조절 */
    }
    
  </style>
<title>Insert title here</title>
</head>
<body>
<div class="container my-5">
	<div>
			<form class="form-control" action="<%=request.getContextPath()%>/member/login" method="post">
		<div class="my-3">
			<input id="id" name="id" type="text" placeholder="아이디를 입력하세요" value="admin">
		</div>
		<div class="my-3">
			<input id="pw" name="pw" type="password" placeholder="비밀번호를 입력하세요" value="1234">
		</div>
		<div class="my-3">
			<button id="btn">빠른 test로그인</button>
		</div>
	</form>
	</div>
	<H1>${member.memberId} 어서오세요^^</H1>
	<div class="row">
	<div class="col border d-grid">
		<button type="button" class="btn btn-light btn-block" data-bs-toggle="collapse" data-bs-target="#demo">NOTICE</button>
	</div>
	<div class="col">
  		<div align="right">
  		</div>
	 		<div id="demo" class="collapse border">
	   			<h2>NOTICE</h2>
	   			<table class="table">
	   				<tr>
	   					<td>
	   					</td>
	   					<td>
	   					</td>
	   					<td>
	   					</td>
	   				</tr>
	   			
	   			</table>
	 		</div>
	</div >
	</div>
	<div class="row my-5">
		<div class="col">
		</div>
		<div class="col md-10">
			<h1>${requestYear}.${requestMonth} CALENDAR</h1>
		</div>
		<div class="col" align="right">
	  	<form method="post">
	  		<input name="requestYear" value="${requestYear}" hidden="true" readonly="readonly">
	  		<input name="requestMonth" value="${requestMonth}" hidden="true" readonly="readonly">
	  		<input name="control" value="month-1" hidden="true" readonly="readonly">
	  		<input class="btn btn-light" type="submit" value="Prev.">
	  	</form>
	  	<form method="post">
	  		<input name="requestYear" value="${requestYear}" hidden="true" readonly="readonly">
	  		<input name="requestMonth" value="${requestMonth}" hidden="true" readonly="readonly">
	  		<input name="control" value="month+1" hidden="true" readonly="readonly">
	  		<input class="btn btn-light" type="submit" value="Next">
	  	</form>
	  	</div>
	</div>
	<c:set var="cnt" value="0"/>
	<c:set var="colorOfday" value="col calendar-day"/>
	<div class="row">
		<div class="col calendar-dayofweek"><h3>MON</h3></div>
		<div class="col calendar-dayofweek"><h3>TUS</h3></div>
		<div class="col calendar-dayofweek"><h3>WEN</h3></div>
		<div class="col calendar-dayofweek"><h3>THU</h3></div>
		<div class="col calendar-dayofweek"><h3>FRI</h3></div>
		<div class="col calendar-dayofweek" style="color: blue;"><h3>SAT</h3></div>
		<div class="col calendar-dayofweek" style="color: red;"><h3>SUN</h3></div>
	</div>
	<div class="row">
		<c:forEach begin="1" end="${dayOfWeek-1}">
			<c:set var="cnt" value="${cnt+1}"/>
			<div class="${colorOfday}"></div>
		</c:forEach>
	  	<c:forEach var="i" begin="1" end="${lengthOfMonth}" >
			<c:set var="cnt" value="${cnt+1}"/>
	    	<c:if test="${(cnt+1)%7 == 0 }">
	    		<c:set var="colorOfday" value="col calendar-saturday"/>
	    	</c:if>
	    	<c:if test="${cnt%7 == 0 }">
	    		<c:set var="colorOfday" value="col calendar-sunday"/>
	    	</c:if>
				<div class="${colorOfday}">
					${i}
					<c:if test="${member != null}">
						<a class="btn btn-basic" href="${pageContext.request.contextPath}/schedule?date=${requestYear}-${requestMonth}-${i}"><span style="font-size: 12px;">✏️</span></a>
					</c:if>
					<div>
						<c:forEach var="j" begin="0" end="2">
							<span style="color: black; font-weight: normal; font-size: 14px;">${memoList[i][j]}</span><br>
							<c:if test="${j==2 && memoList[i][j+1] != null }">
								<span class="badge rounded-pill bg-secondary" style="font-size: 8px;">
									<c:out value="${fn:length(memoList[i])-3}+"></c:out>
								</span>
							</c:if>
						</c:forEach>
					</div>
				</div>
			<c:set var="colorOfday" value="col calendar-day"/>
	    	<c:if test="${cnt%7 == 0 }">
	    		</div> <div class="row">
	    	</c:if>
		</c:forEach>
	  	<c:forEach begin="1" end="${7-(lengthOfMonth+dayOfWeek-1)%7}" >
			<c:set var="cnt" value="${cnt+1}"/>
			<div class="${colorOfday}"></div>
		</c:forEach>
	</div>
</div>	
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.7.1.min.js"></script>
</html>