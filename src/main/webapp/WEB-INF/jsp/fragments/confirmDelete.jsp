<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div class="modal fade p-0" tabindex="-1" id="confirmDelete">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <div class="modal-header bg-dark text-white">
                <h5 class="modal-title" id="modalTitleDelete"></h5>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>

            <div class="modal-body">
                Вы уверены?
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger rounded-0" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-ban"></span>
                    Отмена
                </button>
                <button type="button" class="btn btn-dark rounded-0" id="buttonConfirmDelete">
                    <span class="fa fa-check"></span>
                    Удалить
                </button>
            </div>

        </div>
    </div>
</div>
