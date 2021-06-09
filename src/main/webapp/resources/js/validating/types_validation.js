const schema = joi.object({
    nameType: joi.string().min(2).max(100).label("Наименование").required().messages({
        "string.base": `Поле не должно быть пустым`,
        "string.empty": `Поле не должно быть пустым`,
        "string.min": `Поле должно быть длиной минимум {#limit} символов`,
        "string.max": `Поле должно быть длиной максимум {#limit} символов`,
        "any.required": `Поле является обязательным`
    }),
    parentId: joi.number().integer().min(0).max(1000000).label("Родительская иерархия").required().messages({
        "number.base": `Поле является обязательным`,
        "number.empty": `Поле не должно быть пустым`,
        "number.integer": `Поле должно содержать только целые числа`,
        "number.min": `Поле должно быть длиной минимум {#limit} символов`,
        "number.max": `Поле должно быть длиной максимум {#limit} символов`,
        "any.required": `Поле является обязательным`
    }),
    level: joi.number().integer().min(0).max(10).label("Уровень вложенности").required().messages({
        "number.base": `Поле не должно быть пустым`,
        "number.empty": `Поле не должно быть пустым`,
        "number.integer": `Поле должно содержать только целые числа`,
        "number.min": `Поле должно быть длиной минимум {#limit} символов`,
        "number.max": `Поле должно быть длиной максимум {#limit} символов`,
        "any.required": `Поле является обязательным`
    }),
});

function validate(dataObject) {
    const result = schema.validate(
        {
            ...dataObject,
        },
        { abortEarly: false }
    );
    return result;
}

$(document).ready(function () {
    $("#buttonSave").on("click", function (e) {

        e.preventDefault();
        const contactForm = "#detailsForm";

        const formErrors = validate({
            nameType: $(contactForm).find("#nameType").val(),
            parentId: $(contactForm).find("#parentId").val(),
            level: $(contactForm).find("#level").val(),
        });

        const initialErros = {
            nameType: null,
            parentId: null,
            level: null,
        };

        if (formErrors?.error) {
            const { details } = formErrors.error;
            details.map((detail) => {
                initialErros[detail.context.key] = detail.message;
            });
        }

        Object.keys(initialErros).map((errorName) => {
            if (initialErros[errorName] !== null) {

                $(`#${errorName}`).removeClass("is-valid").addClass("is-invalid");

                $(`#${errorName}`)
                    .nextAll(".invalid-feedback")
                    .text(initialErros[errorName]);
            } else {
                $(`#${errorName}`).removeClass("is-invalid").addClass("is-valid");
            }
        });

        let isFormValid = Object.values(initialErros).every(
            (value) => value === null
        );
        if (isFormValid) {
            $(contactForm)
                .find(".is-valid, .is-invalid")
                .removeClass("is-valid is-invalid");
            save();
        };
    });
});
