<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>register</title>
</head>
<body>
<div class="container my-5">
    <jsp:include page="/WEB-INF/view/menu.jsp"/>
    <h1>REGISTER</h1>
    <form method="post" class="form-control">
        <div>
            <span style="color: brown">
            <spring:hasBindErrors name="userAddRequestDto">
                <c:forEach var="fieldError" items="${errors.fieldErrors}">
                    ${fieldError.defaultMessage}<br>
                </c:forEach>
            </spring:hasBindErrors>
            </span>
        </div>
        user ID : <input name="id" class="form-control" value="${userAddRequestDto.id}">
        user password : <input name="pw" class="form-control" type="password">
        user password check : <input name="pwCheck" class="form-control" type="password">
        <button class="btn" type="submit">register</button>
    </form>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</html>