<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col">
            <form action="/diary/notice" method="post">
            <table class="table border">
                <tr>
                    <td class="p-3"><h3>TITLE </h3></td>
                    <td><input name="noticeTitle" class="form-control"></td>
                </tr>
                <tr>
                    <td class="p-3">AUTHOR</td>
                    <td>${memberLoggedIn.memberId}</td>
                </tr>
                <tr height="300 px">
                    <td class="p-3">content</td>
                    <td class="p-3">
                        <textarea name="noticeContent" class="form-control" rows="10" ></textarea>
                    </td>
                </tr>
            </table>
                <div align="center">
                    <button class="btn" type="submit">submit</button>
                </div>
            </form>
        </div>
        <div class="col-3">
        </div>
    </div>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</html>