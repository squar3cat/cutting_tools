var typeAjaxUrl = "profile/tools/types/";

var ctx = {
    ajaxUrl: typeAjaxUrl,
    updateTable: function () {
        $.get(typeAjaxUrl, updateTableByData);
    },
    putDataToForm: function (data) {
        $.each(data, function (key, value) {
            if (typeof value == "boolean") {
                if (value)
                    form.find("input[name='" + key + "']").prop("checked", true);
                else
                    form.find("input[name='" + key + "']").prop("checked", false);
            }
            else {
                form.find("[name='" + key + "']").val(value);
            }
        });
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "id",
                className: "dt-body-center"
            },
            {
                "data": "name",
                className: "dt-body-center"
            },
            {
                "data": "parentId",
                className: "dt-body-center"
            },
            {
                "data": "level",
                className: "dt-body-center"
            },
            {
                "data": "finalType",
                "render": function (data, type) {
                    return "<input type='checkbox' " + (data ? "checked" : "") + " disabled/>";
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
        ]
    });
});