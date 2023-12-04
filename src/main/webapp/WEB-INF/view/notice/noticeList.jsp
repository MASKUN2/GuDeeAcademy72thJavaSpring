<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title></title>
</head>
<body>
<div class="container my-5">
<jsp:include page="/WEB-INF/view/menu.jsp"/>
    <div align="center"><h1>NOTICE</h1></div>
    <table class="table">
        <tr>
            <th>&nbsp;</th>
            <th>title</th>
            <th>author</th>
        </tr>
        <c:forEach var="notice" items="${noticeList}">
        <tr>
            <td>&nbsp;</td>
            <td><a href="/diary/notice/${notice.noticeNo}">${notice.noticeTitle}</a></td>
            <td>${notice.author}</td>
        </tr>
        </c:forEach>
    </table>
    <c:if test="${memberLoggedIn.memberLevel == 1}">
    <a href="/diary/notice/add" class="btn" >write</a>
    </c:if>

</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</html>