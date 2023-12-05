<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="border my-4">
    <a href="/diary/home">home</a>
    <a href="/diary/notice">notice</a>

    <c:if test="${memberLoggedIn == null}">
    <a href="/diary/member/login">login</a>
    <a href="/diary/member/add">register</a>
    </c:if>
    <c:if test="${memberLoggedIn != null}">
    <a href="/diary/member/logout">logout</a>
    </c:if>
</div>
