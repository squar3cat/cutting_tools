var form;

function makeEditable(datatableOpts) {

    $.fn.dataTable.moment('DD.MM.YYYY');

    ctx.datatableApi = $("#datatable").DataTable(
        $.extend(true, datatableOpts,
            {
                "ajax": {
                    "url": ctx.ajaxUrl,
                    "dataSrc": ""
                },
                "language": {
                    "info": "Показано c _START_ по _END_ из _TOTAL_ позиций",
                    "infoEmpty": "Показано 0 позиций",
                    "search": "Поиск:",
                    "emptyTable": "В таблице нет доступных данных по вашему запросу"
                },
                "paging": false,
                "info": true
            }
        ));

    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function add() {

    $("#modalTitle").html("Добавить новую запись");
    form.find(":input:not([type='checkbox'])").val("");
    form.find(":input([type='checkbox'])").prop("checked", false);
    $("#editRow").modal();
}

function updateRow(id) {
    form.find(":input:not([type='checkbox'])").val("");
    form.find(":input([type='checkbox'])").prop("checked", false);
    $("#modalTitle").html("Изменить существующую запись");
    $.get(ctx.ajaxUrl + id, function (data) {
        ctx.putDataToForm(data);
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    $("#modalTitleDelete").html("Удалить существующую запись?");
    $('#confirmDelete').modal();
    $("#buttonConfirmDelete").on("click", function () {
        $.ajax({
            url: ctx.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            $("#confirmDelete").modal("hide");
            $("#buttonConfirmDelete").off("click");
            ctx.updateTable();
            successNoty("Запись удалена");
        });
    });
}

function updateTableByData(data) {
    ctx.datatableApi.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("Запись сохранена");
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + key,
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    var  errorInfo = jqXHR.responseJSON;
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + "Статус ошибки: " + jqXHR.status +
            "<br>" + errorInfo.type + "<br>" + errorInfo.details.join("<br>"),
        type: "error",
        layout: "bottomRight"
    }).show();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<button class='btn btn-dark rounded-0 p-2' onclick='updateRow(" + row.id + ");'><span class='fa fa-pencil'></span></button>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<button class='btn btn-danger rounded-0 p-2' onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></button>";
    }
}

$('.modal').on('hidden.bs.modal', function () {
    $("#detailsForm")
        .find(".is-valid, .is-invalid")
        .removeClass("is-valid is-invalid");
});
