<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<sec:authorize access="isAuthenticated()">
    <div id="sidebarMenuWrapper">
<div class="header"></div>
<input type="checkbox" class="openSidebarMenu" id="openSidebarMenu">
<label for="openSidebarMenu" class="sidebarIconToggle">
    <div class="spinner diagonal part-1"></div>
    <div class="spinner horizontal"></div>
    <div class="spinner diagonal part-2"></div>
</label>
<div id="sidebarMenu">
    <ul class="sidebarMenuInner">
        <li><i class="fa fa-compass"></i><a href="tools">Инструменты</a></li>
        <sec:authorize access="hasRole('ADMIN')">
            <li><i class="fa fa-user"></i><a href="users">Пользователи</a></li>
        </sec:authorize>
        <hr class="mt-0 mb-0">
        <form:form class="form-inline my-2" action="logout" method="post">
            <button class="btn btn-primary my-1 rounded-0" type="submit">
                <span class="fa fa-sign-out"></span>
                Выйти из аккаунта
            </button>
        </form:form>
    </ul>
</div>
    </div>
</sec:authorize>

