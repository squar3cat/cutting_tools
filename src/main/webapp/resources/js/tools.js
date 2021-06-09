var toolAjaxUrl = "profile/tools/";

var ctx = {
    ajaxUrl: toolAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: toolAjaxUrl + "filter",
            data: $("#filter").serialize()
        }).done(updateTableByData);
    },
    putDataToForm: function (data) {
        $.each(data, function (key, value) {
            if (typeof value == "object" && value !== null) {
                form.find("select[name='"+key+"']").val(Object.values(value)[0]);
            }
            else if (typeof value == "boolean") {
                if (value)
                    form.find("input[name='" + key + "']").prop("checked", true);
                else
                    form.find("input[name='" + key + "']").prop("checked", false);
            }
            else {
                form.find("input[name='" + key + "']").val(value);
            }
        });
    }
};

function clearFilter() {
    $("#filter")[0].reset();
    $.get(toolAjaxUrl, updateTableByData);
}

function roleStatus() {
    $role = document.getElementById('role').value;
    return $role === 'editor' || $role === 'admin';
}

$(function () {

    makeEditable({
        "columns": [
                {
                    "data": "registrationDate",
                    className: "dt-body-center",
                    "render": function (data) {
                        return moment(data).format('DD.MM.YYYY')
                    }
                },
                {
                    "data": "description"
                },
                {
                    "data": "toolsCount",
                    className: "dt-body-center"
                },
                {
                    "data": "manufacturer",
                    className: "dt-body-center"
                },
                {
                    "data": "location.name",
                    className: "dt-body-center"
                },
                {
                    "data": "deficiency",
                    className: "dt-body-center",
                    "visible": roleStatus()
                },
                {
                    "data": "type.name",
                    className: "dt-body-center"
                },
                {
                    "render": renderEditBtn,
                    "defaultContent": "",
                    "orderable": false,
                    "visible": roleStatus()

                },
                {
                    "render": renderDeleteBtn,
                    "defaultContent": "",
                    "orderable": false,
                    "visible": roleStatus()
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ],
        "createdRow": function (row, data, dataIndex) {
            if (roleStatus())
                $(row).attr("data-toolDeficient", data.isDeficient);
        },
    });

});