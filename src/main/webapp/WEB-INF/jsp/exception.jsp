<%@ page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/header.jsp"/>

<body>
<jsp:include page="fragments/sidebarMenu.jsp"/>

<div class="jumbotron">
    <div class="container text-center">
        <br>
        <h4 class="my-3">${status}</h4>
        <h2>Ошибка приложения</h2>
        <h4 class="my-5">${message}</h4>
    </div>
</div>
<!--
<c:forEach items="${exception.stackTrace}" var="stackTrace">
    ${stackTrace}
</c:forEach>
-->
</body>
</html>