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
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col">
            <table class="table border">
                <tr>
                    <td class="p-3">
                        <h3>TITLE : ${notice.noticeTitle}</h3>
                    </td>
                </tr>
                <tr>
                    <td class="p-3">
                        AUTHOR : ${notice.author}
                    </td>
                </tr>
                <tr height="500 px">
                    <td class="p-3">
                        ${notice.noticeContent}
                    </td>
                </tr>
                <c:forEach var="comment" items="${notice.noticeCommentList}">
                    <tr>
                        <td>
                            <div class="m-3">
                               <span name="commentNo" hidden="hidden">${comment.commentNo}</span>
                               <span name="commentContent">${comment.commentContent}</span>
                            </div>
                            <div align="right" class="mx-3" >
                                <button name="btnCommentEdit" class="btn">✏️</button>
                                <button name="btnCommentDelete" class="btn">❌</button>
                                <span class="btn btn-secondary btn-sm"> by: ${comment.author}</span>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/notice/comment" method="post">
                            <div class="row">
                                <div class="col pl-3">
                                    <textarea name="commentContent" type="text" class="form-control" placeholder="댓글추가" ></textarea>
                                    <input name="noticeNo" type="number" value="${notice.noticeNo}" readonly="readonly" hidden="hidden">
                                </div>
                                <div class="col-3" align="left">
                                    🔏<input class="form-check-input align-middle" type="checkbox" name="isSecret" value="true">
                                    <input class="btn btn-light m-3" type="submit" value="+">
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $('button[name=btnCommentEdit]').click(function (){
        let tr = $(this).closest('tr');
        let commentContent = tr.find('span[name=commentContent').text();
        console.log(commentContent);
        let input = '<input name="commentContent" class="form-control" value="'+commentContent+'">'
        tr.find('span[name=commentContent').replaceWith(input);
        $(this).click(function (){
            let tr = $(this).closest('tr');
            let commentNo = tr.find('span[name=commentNo').text();
            let commentContent = tr.find('input[name=commentContent').val();
            console.log(commentNo,commentContent);
            $.ajax({
                type : 'PUT',
                data : JSON.stringify({'commentNo':commentNo, 'commentContent':commentContent}),
                contentType : 'application/json; charset=utf-8',
                url:'/diary/notice/comment',
                success: function (response){
                    window.location.reload();
                },
                error: function (error){
                    alert('fail');
                    window.location.reload();
                }
            });
        });

    });
</script>
</html>