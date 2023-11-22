<%@page import="vo.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
      height: 150px; /* 각 셀의 높이 조절 */
    }
    .calendar-saturday {
      border: 1px solid #ddd;
      padding: 10px;
      height: 150px; /* 각 셀의 높이 조절 */
      color: #0000ff;
    }
    .calendar-sunday {
      border: 1px solid #ddd;
      padding: 10px;
      height: 150px; /* 각 셀의 높이 조절 */
      color: #ff0000;
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
	<H1>${member.memberId} 어서오세요^^</H1>
	<div>
		<br>requestYear ${requestYear}
		<br>requestMonth ${requestMonth}
		<br>lengthOfMonth ${lengthOfMonth}
		<br>dayOfWeek ${dayOfWeek}
		<br>test ${test}
		<br>cnt ${cnt}
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/member/create">/member/create</a><br>
		<a href="${pageContext.request.contextPath}/member/login">/member/login</a><br>
		<a href="${pageContext.request.contextPath}/member/logout">/member/logout</a><br>
		<a href="${pageContext.request.contextPath}/member/info">/member/info</a><br>
		<a href="${pageContext.request.contextPath}/member/info">/member/delete</a><br>
	</div >
	<div class="row my-5">
		<div class="col">
		</div>
		<div class="col md-10">
			<h1>${requestYear}.${requestMonth} CALENDAR</h1>
		</div>
		<div class="col"></div>
	</div>
	<div class="row mb-2 p-3">
	  <form method="get">
    <label for="year">Year:</label>
    <select name="requestYear" >
      <option value="2023">2023</option>
      <option value="2024">2024</option>
      <option value="2025">2025</option>
    </select>

    <label for="month">Month:</label>
    <select name="requestMonth">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
      <option value="6">6</option>
      <option value="7">7</option>
      <option value="8">8</option>
      <option value="9">9</option>
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
    </select>

    <input class="btn btn-light" type="submit" value="go">
  </form>
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
			<div class="${colorOfday}">${i}</div>
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