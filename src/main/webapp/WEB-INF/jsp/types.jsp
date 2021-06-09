<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/header.jsp"/>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/types.js" defer></script>
<jsp:include page="fragments/sidebarMenu.jsp"/>

<div class="jumbotron mb-0">
    <div class="container">
        <div class="row">
            <h4>Типы инструмента</h4>
        </div>
        <div class="row mb-3">
            <button class="btn btn-dark rounded-0" onclick="add()">
                <span class="fa fa-plus"></span>
                Добавить тип инструмента
            </button>
        </div>
        <div class="row bg-white p-4">
            <table class="table page_types" id="datatable">
                <thead>
                <tr>
                    <th>Идентификатор</th>
                    <th>Наименование</th>
                    <th>Идентификатор родительского типа</th>
                    <th>Уровень вложенности</th>
                    <th>Финальный тип</th>
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
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="nameType" class="col-form-label">Наименование</label>
                        <input type="text" class="form-control" id="nameType" name="name">
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="parentId" class="col-form-label">Родительская иерархия</label>
                        <select class="form-control custom-select" name="parentId" id="parentId" size="1">
                            <option value="0">--- Корневой тип ---</option>
                            <c:forEach items="${types}" var="toolType">
                                <option value="${toolType.id}">${toolType.level} ${toolType.name}</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="level" class="col-form-label">Уровень вложенности</label>
                        <input type="text" class="form-control" id="level" name="level">
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="finalType" name="finalType">
                        <label for="finalType" class="col-check-label mb-0 mt-1">Финальный тип</label>
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
<script type="text/javascript" src="resources/js/selectmenu.js" defer></script>
<script type="text/javascript" src="resources/js/validating/types_validation.js" defer></script>
</body>
</html>