<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/header.jsp"/>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/users.js" defer></script>
<jsp:include page="fragments/sidebarMenu.jsp"/>

<div class="jumbotron mb-0">
    <div class="container">
        <div class="row">
            <h4>Пользователи</h4>
        </div>
        <div class="row mb-4">
            <button class="btn btn-dark rounded-0" onclick="add()">
                <span class="fa fa-user-plus"></span>
                Добавить пользователя
            </button>
        </div>
        <div class="row bg-white p-4">
            <table class="table page_users" id="datatable">
                <thead>
                <tr>
                    <th>Имя</th>
                    <th>Роль</th>
                    <th>Активный</th>
                    <th>Зарегистрирован</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<div class="modal fade p-0" tabindex="-1" id="editRow">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header bg-dark text-white">
                <h5 class="modal-title" id="modalTitle"></h5>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm" class="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <label for="username" class="col-form-label">Имя</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="username" name="name">
                        <div class="invalid-feedback"></div>
                    </div>

                    <label for="password" class="col-form-label">Пароль</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="password" name="password">
                        <div class="input-group-append">
                            <span class="input-group-text rounded-0"><i class="fa fa-eye-slash"></i></span>
                        </div>
                        <div class="invalid-feedback"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger rounded-0" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-ban"></span>
                    Отмена
                </button>
                <button type="button" class="btn btn-dark" id="buttonSave">
                    <span class="fa fa-check"></span>
                    Сохранить
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/confirmDelete.jsp"/>
<script type="text/javascript" src="resources/js/showhide-pass.js" defer></script>
<script type="text/javascript" src="resources/js/validating/users_validation.js" defer></script>
</body>
</html>