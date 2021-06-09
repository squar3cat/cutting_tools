var typeAjaxUrl = "profile/tools/locations/";

var ctx = {
    ajaxUrl: typeAjaxUrl,
    updateTable: function () {
        $.get(typeAjaxUrl, updateTableByData);
    },
    putDataToForm: function (data) {
        $.each(data, function (key, value) {
            form.find("[name='" + key + "']").val(value);
        });
    }
}

$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name",
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