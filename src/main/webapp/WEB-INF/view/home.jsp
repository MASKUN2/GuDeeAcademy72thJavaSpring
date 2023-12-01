<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    //Debug용 소스
     Enumeration<String> attributeNames = request.getAttributeNames();
     while (attributeNames.hasMoreElements()){
         String attributeName = attributeNames.nextElement();
         Object attributeValue = request.getAttribute(attributeName);
         if(!attributeName.startsWith("org.springframework")){
             System.out.println(attributeName + "=" + attributeValue );
         }
     }

%>
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
    <div align="center"><h1>CALENDAR</h1></div>
    <div align="center"><h1>${homeCalendar.yearMonth}</h1></div>
    <div align="center" class="mb-4">
        <button class="btn btn-light" id="btnPrevious">prev</button>
        <button class="btn btn-light" id="btnNext">next</button>
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
    <c:forEach begin="1" end="${homeCalendar.numFrontBlank}">
        <c:set var="cnt" value="${cnt+1}"/>
        <div class="col calendar"></div>
    </c:forEach>
    <c:forEach var="dateInfo" items="${homeCalendar.dateInfoList}" >
        <c:set var="cnt" value="${cnt+1}"/>
        <div class="col calendar" style="${ ((cnt-1) % 7 == 0 || (dateInfo.isHoliday() == true) )? "color: red;" : (cnt % 7 == 0)? "color: blue;" : "color: black;"}">
            ${dateInfo.dateIndex} <span style="font-size: 14px;">${dateInfo.dateName}</span>
            <c:if test="${memberLoggedIn != null}">
                <a class="btn btn-basic" href="${pageContext.request.contextPath}/schedule/${homeCalendar.yearMonth}-${dateInfo.dateIndexStr}"><span style="font-size: 12px;">✏️</span></a>
            <div style="font-size: 14px; color: black;font-weight: normal;">
                <c:forEach var="memo" items="${dateInfo.memoHead}">
                    · ${memo}<br>
                </c:forEach>
                    <c:if test="${fn:length(dateInfo.dateMemoList) > fn:length(dateInfo.memoHead)}">
                        &nbsp;&nbsp;<span style="font-size: 10px; color: darkslateblue; font-weight: bold">${fn:length(dateInfo.dateMemoList) - fn:length(dateInfo.memoHead)}+</span>
                    </c:if>
            </div>
            </c:if>
        </div>
        <c:if test="${cnt % 7 == 0}">
        </div> <div class="row">
        </c:if>
    </c:forEach>
    <c:forEach begin="1" end="${homeCalendar.numBackBlank}" >
        <div class="col calendar"></div>
    </c:forEach>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    let year = ${homeCalendar.year};
    let month = ${homeCalendar.month};
    let PreviousUrl = "/diary/home/"+year+"-"+(month-1).toString().padStart(2,"0");
    let NextUrl = "/diary/home/"+year+"-"+(month+1).toString().padStart(2,"0");
    if(month-1 == 0){
        PreviousUrl = "/diary/home/"+(year-1)+"-"+"12";
    }
    if(month+1 == 13){
        NextUrl = "/diary/home/"+(year+1)+"-"+"01";
    }
    $("#btnPrevious").click(function (){
        window.location.href = PreviousUrl;
    })
    $("#btnNext").click(function (){
        window.location.href = NextUrl;
    })

</script>
</html>
