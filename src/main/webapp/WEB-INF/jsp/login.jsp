<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/header.jsp"/>
<link rel="stylesheet" href="resources/css/signin.css">
<body>
        <c:if test="${param.error}">
            <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
        </c:if>
        <c:if test="${not empty param.message}">
            <div class="message">${param.message}</div>
        </c:if>
        <sec:authorize access="isAnonymous()">
           <form:form class="form-signin" id="login_form" action="spring_security_check" method="post">
                <h4 class="mb-3 font-weight-normal text-center">Введите свои данные</h4>
                <div class="form-group">
                    <label for="inputLogin" class="sr-only">Логин</label>
                    <input type="text" name="username" id="inputLogin" class="form-control rounded-0" placeholder="Логин" required autofocus>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="sr-only">Пароль</label>
                    <div class="input-group">
                        <input type="password" name="password" id="inputPassword" class="form-control rounded-0" placeholder="Пароль" required>
                        <div class="input-group-append">
                            <span class="input-group-text rounded-0"><i class="fa fa-eye-slash"></i></span>
                        </div>
                    </div>
                </div>
                <button class="btn btn-lg btn-dark btn-block rounded-0" type="submit">Войти</button>
                <hr class="mt-3 mb-3">
                <button class="btn btn-lg btn-dark btn-block rounded-0" type="submit" onclick="login('user', 'password')">Войти как гость</button>
                <button class="btn btn-lg btn-dark btn-block rounded-0" type="submit" onclick="login('admin', 'admin')">Войти как Admin</button>
                <button class="btn btn-lg btn-dark btn-block rounded-0" type="submit" onclick="login('editor', 'editor')">Войти как Editor</button>
               <c:if test="${param.error}">
                   <div class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
               </c:if>
               <c:if test="${not empty param.message}">
                   <div class="message">${param.message}</div>
               </c:if>
            </form:form>
        </sec:authorize>
<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>

    function login(username, password) {
        setCredentials(username, password);
        $("#login_form").submit();
    }
    function setCredentials(username, password) {
        $('input[name="username"]').val(username);
        $('input[name="password"]').val(password);
    }
</script>
<script type="text/javascript" src="resources/js/showhide-pass.js" defer></script>
</body>
</html>

