<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="border my-4">
    <a href="/diary/home">home</a>
    <a href="/diary/notice">notice</a>

    <c:if test="${userLoggedIn == null}">
    <a href="/diary/user/login">login</a>
    <a href="/diary/user/add">register</a>
    </c:if>
    <c:if test="${userLoggedIn != null}">
    <a href="/diary/user/logout">logout</a>
    </c:if>
</div>
