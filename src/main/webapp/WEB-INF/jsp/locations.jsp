<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/header.jsp"/>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/locations.js" defer></script>
<jsp:include page="fragments/sidebarMenu.jsp"/>

<div class="jumbotron mb-0">
    <div class="container">
        <div class="row">
            <h4>Местонахождения</h4>
        </div>
        <div class="row mb-3">
            <button class="btn btn-dark rounded-0" onclick="add()">
                <span class="fa fa-plus"></span>
                Добавить местонахождение
            </button>
        </div>
        <div class="row bg-white p-4">
            <table class="table page_locations" id="datatable">
                <thead>
                <tr>
                    <th>Наименование</th>
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
                        <label for="nameLocation" class="col-form-label">Наименование</label>
                        <input type="text" class="form-control" id="nameLocation" name="name">
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
<script type="text/javascript" src="resources/js/selectmenu.js" defer></script>
<script type="text/javascript" src="resources/js/validating/locations_validation.js" defer></script>
</body>
</html>