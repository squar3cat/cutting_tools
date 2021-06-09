<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/header.jsp"/>
<%--<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">--%>
<body>
<script type="text/javascript" src="resources/js/common.js" defer></script>
<script type="text/javascript" src="resources/js/tools.js" defer></script>

<jsp:include page="fragments/sidebarMenu.jsp"/>

<input type="hidden" id="role" value="<sec:authentication property='principal.username'/>">

<div class="jumbotron mb-0">
    <div class="container">
       <div class="row">
            <h4>Инструменты</h4>
            <hr>
        </div>
        <div class="row mb-4 align-items-center">
            <sec:authorize access="hasRole('EDITOR')">
                <div class="col-md-4 bg-transparent pb-4 pt-4 pl-5 pr-5 text-center">
                    <button class="btn btn-dark rounded-0 mb-1 w-100" onclick="add()">
                        <span class="fa fa-plus"></span>
                        Добавить новый инструмент
                    </button>
                    <sec:authorize access="hasRole('ADMIN')">
                    <button class="btn btn-dark rounded-0 mb-1 w-100" onclick="document.location='tools/types/'">
                        <span class="fa fa-pencil"></span>
                        Редактировать типы инструментов
                    </button>
                    <button class="btn btn-dark rounded-0 mb-1 w-100" onclick="document.location='tools/locations/'">
                        <span class="fa fa-pencil"></span>
                        Редактировать местонахождения
                    </button>
                    </sec:authorize>
                </div>
            </sec:authorize>
            <div class="col bg-white p-4">
                <form id="filter">
                    <div class="row row-cols-4">
                        <div class="col-3">
                            <label for="toolType">Тип инструмента:</label>
                            <select class="form-control custom-select" name="toolType" id="toolType" size="1">
                                <option value="0">Все типы</option>
                                <c:forEach items="${types}" var="toolType">
                                    <jsp:useBean id="toolType" type="com.app.tools.model.Type"/>
                                    <option value="${toolType.id}">${toolType.level} ${toolType.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <label for="filteredLocation">Местонахождение:</label>
                            <select class="form-control custom-select" name="filteredLocation" id="filteredLocation"
                                    size="1">
                                <option value="0">Все БИХ</option>
                                <c:forEach items="${locations}" var="location">
                                    <jsp:useBean id="location" type="com.app.tools.model.Location"/>
                                    <option value="${location.id}">${location.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <label for="startDate">С (включительно):</label>
                            <input class="form-control" type="date" name="startDate" id="startDate" autocomplete="off">
                        </div>
                        <div class="col">
                            <label for="endDate">По (включительно):</label>
                            <input class="form-control" type="date" name="endDate" id="endDate" autocomplete="off">
                        </div>
                    </div>
                </form>
                <button class="btn btn-danger rounded-0" onclick="clearFilter()">
                    <span class="fa fa-ban"></span>
                    Очистить фильтр
                </button>
                <button class="btn btn-dark rounded-0" onclick="ctx.updateTable()">
                    <span class="fa fa-filter"></span>
                    Фильтр
                </button>
            </div>
        </div>
        <div class="row bg-white p-4">
            <table class="table page_tools" id="datatable">
                <thead>
                <tr>
                    <th>Последняя дата прихода</th>
                    <th>Наименование инструмента</th>
                    <th>Количество</th>
                    <th>Производитель</th>
                    <th>Местонахождение</th>
                    <th>Минимальное количество</th>
                    <th>Тип инструмента</th>
                    <th></th>
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
                        <label for="registrationDate" class="col-form-label">Дата прихода</label>
                        <input type="date" class="form-control" id="registrationDate" name="registrationDate"
                               autocomplete="off">
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label">Наименование инструмента</label>
                        <input type="text" class="form-control" id="description" name="description">
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="toolsCount" class="col-form-label">Количество</label>
                        <input type="number" class="form-control" id="toolsCount" name="toolsCount">
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="manufacturer" class="col-form-label">Производитель</label>
                        <input type="text" class="form-control" id="manufacturer" name="manufacturer">
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="location" class="col-form-label">Местонахождение</label>
                        <select class="form-control custom-select" name="location" id="location" size="1">
                            <c:forEach items="${locations}" var="location">
                                <option value="${location.id}">${location.name}</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="deficiency" class="col-form-label">Минимальное количество</label>
                        <input type="number" class="form-control" id="deficiency" name="deficiency">
                        <div class="invalid-feedback"></div>
                    </div>

                    <div class="form-group">
                        <label for="type" class="col-form-label">Тип инструмента</label>
                        <select class="form-control custom-select" name="type" id="type" size="1">
                            <c:forEach items="${types}" var="toolType">
                                <c:choose>
                                    <c:when test="${toolType.finalType==false}">
                                        <option disabled value="">${toolType.level} ${toolType.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${toolType.id}">${toolType.level} ${toolType.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger rounded-0" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-ban"></span>
                    Отмена
                </button>
                <button type="button" class="btn btn-dark rounded-0" id="buttonSave">
                    <span class="fa fa-check"></span>
                    Сохранить
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/confirmDelete.jsp"/>
<script type="text/javascript" src="resources/js/selectmenu.js" defer></script>
<script type="text/javascript" src="resources/js/validating/tools_validation.js" defer></script>
</body>
</html>