<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>home</title>
    <style>
        .calendar {
            border: 1px solid lavender;
            padding: 10px;
            height: 160px;
            font-size: 18px;
            color: black;
            font-weight: bold;
        }

    </style>
</head>
<body>
<div class="container my-5">
    <jsp:include page="/WEB-INF/view/menu.jsp"/>
    home

<div>
    <div align="center"><h1>${requestYear} ${requestMonth} CALENDAR</h1></div>
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
<div class="row">
    <div class="col calendar" style="height: 60px; color: red;"><h3>SU</h3></div>
    <div class="col calendar" style="height: 60px;"><h3>MO</h3></div>
    <div class="col calendar" style="height: 60px;"><h3>TU</h3></div>
    <div class="col calendar" style="height: 60px;"><h3>WE</h3></div>
    <div class="col calendar" style="height: 60px;"><h3>TH</h3></div>
    <div class="col calendar" style="height: 60px;"><h3>FR</h3></div>
    <div class="col calendar" style="height: 60px; color: blue;"><h3>SA</h3></div>
</div>
<div class="row">
<c:set var="cnt" value="0"/>
<c:set var="colorOfday" value="col calendar-day"/>
    <c:forEach begin="${dayOfWeek}" end="">
        <c:
        <c:set var="cnt" value="${cnt+1}"/>
        <div class="${colorOfday}"></div>
    </c:forEach>
    <c:forEach var="i" begin="1" end="${empty lengthOfMonth ? 1 : lengthOfMonth}" >
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

</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</html>
