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
    <div align="center" class="my-5">
        <h3> ${date} SCHEDULE</h3>
        <a class="btn btn-light" href="/diary/home/${yearMonth}">CALENDAR </a>
    </div>
    <table class="table table-strigh">
        <tr>
            <th></th>
            <th>MEMO</th>
        </tr>
        <c:forEach var="memo" items="${memoList}">
            <tr>
                <td></td>
                <td>
                        <div class="row">
                            <div class="col">
                                <input name="memoNo" value="${memo.memoNo}" readonly="true" hidden="hidden">
                                <input name="memo" class="form-control" value="${memo.memo}">
                            </div>
                            <div class="col col-2">
                                <button name="btnModify" class="btn btn-light" >✏️</button>
                                <button name="btnDelete" class="btn btn-light" >🗑️</button>
                            </div>
                        </div>
            </tr>
        </c:forEach>
        <tr>
            <td width="80 px">
                <select id="emoticon" class="form-select form-select-sm mt-1">
                    <option value=""> </option>
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
                    <div class="col-10">
                        <input id="memo" type="text" class="form-control" placeholder="일정추가" >
                    </div>
                    <div class="col-2">
                        <button id="btnAdd" class="btn btn-light" type="button">➕</button>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
        let memoNoValue;
        let memoValue;

    $("button[name=btnModify]").click(function(){
        let row = $(this).closest('.row');
        memoNoValue = row.find('input[name="memoNo"]').val();
        memoValue = row.find('input[name="memo"]').val();
        console.log("memoNoValue: " + memoNoValue);
        $.ajax({
            url: "/diary/schedule/"+memoNoValue,
            type: "PUT",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({ "memo" : memoValue}),
            success: function(response) {
                console.log("성공");
                window.location.reload()
            },
            error: function(error) {
                console.log("실패");
                window.location.reload()
            }
        });
    });
    $('button[name=btnDelete]').click(function (){
        let row = $(this).closest('.row');
        memoNoValue = row.find('input[name=memoNo]').val();
        $.ajax({
            url: '/diary/schedule/'+memoNoValue,
            type: 'DELETE',
            contentType: 'application/json; charset=utf-8',
            success: function (response) {
                console.log('성공');
                window.location.reload()
            },
            error: function (error){
                console.log('실패');
                window.location.reload()
            }
        });
    });
    $('#btnAdd').click(function (){
        memoValue = $('#emoticon').val() + $('#memo').val();
        console.log(memoValue);
        $.post({
            url: '/diary/schedule',
            contentType : 'application/json; charset=utf-8',
            data: JSON.stringify({"memo":memoValue, "yearMonthDate":'${yearMonthDate}'}),
            success : function (){
                console.log('성공');
                window.location.reload()
            },
            error: function (error){
                console.log('실패');
                alert("test");
                window.location.reload()
            }
        });
    });

</script>
</html>