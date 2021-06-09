var userAjaxUrl = "admin/users/";

var ctx = {
    ajaxUrl: userAjaxUrl,
    updateTable: function () {
        $.get(userAjaxUrl, updateTableByData);
    },
    putDataToForm: function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
    }
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
    $.ajax({
        url: userAjaxUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-userEnabled", enabled);
        successNoty(enabled ? "Пользователь активен" : "Пользователь не активен");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name",
                className: "dt-body-center"
            },
            {
                "data": "roles",
                className: "dt-body-center"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                },
                className: "dt-body-center"
            },
            {
                "data": "registered",
                "render": function (data) {
                    return moment(data).format('DD.MM.YYYY')
                },
                className: "dt-body-center"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn,
                "width": "22px"
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn,
                "width": "22px"
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).attr("data-userEnabled", false);
            }
        }
    });
});