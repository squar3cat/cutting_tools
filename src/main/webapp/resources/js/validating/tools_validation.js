const schema = joi.object({
    registrationDate: joi.date().label("Дата прихода").required().messages({
        "date.base": `Поле должно иметь формат даты`,
        "date.empty": `Поле не должно быть пустым`,
        "any.required": `Поле является обязательным`
    }),
    description: joi.string().min(3).max(120).label("Наименование инструмента").required().messages({
        "string.base": `Поле не должно быть пустым`,
        "string.empty": `Поле не должно быть пустым`,
        "string.min": `Поле должно быть длиной минимум {#limit} символов`,
        "string.max": `Поле должно быть длиной максимум {#limit} символов`,
        "any.required": `Поле является обязательным`
    }),
    toolsCount: joi.number().integer().min(0).max(50000).label("Количество").required().messages({
        "number.base": `Поле не должно быть пустым`,
        "number.empty": `Поле не должно быть пустым`,
        "number.integer": `Поле должно содержать только целые числа`,
        "number.min": `Поле должно быть длиной минимум {#limit} символов`,
        "number.max": `Поле должно быть длиной максимум {#limit} символов`,
        "any.required": `Поле является обязательным`
    }),
    manufacturer: joi.string().min(2).max(50).label("Производитель").required().messages({
        "string.base": `Поле не должно быть пустым`,
        "string.empty": `Поле не должно быть пустым`,
        "string.min": `Поле должно быть длиной минимум {#limit} символов`,
        "string.max": `Поле должно быть длиной максимум {#limit} символов`,
        "any.required": `Поле является обязательным`
    }),
    location: joi.string().label("Местонахождение").required().messages({
        "string.base": `Поле является обязательным`,
        "string.empty": `Поле не должно быть пустым`,
        "any.required": `Поле является обязательным`
    }),
    deficiency: joi.number().integer().min(1).max(10000).label("Минимальное количество").required().messages({
        "number.base": `Поле не должно быть пустым`,
        "number.empty": `Поле не должно быть пустым`,
        "number.integer": `Поле должно содержать только целые числа`,
        "number.min": `Поле должно быть длиной минимум {#limit} символов`,
        "number.max": `Поле должно быть длиной максимум {#limit} символов`,
        "any.required": `Поле является обязательным`
    }),
    type: joi.string().label("Тип инструмента").required().messages({
        "string.base": `Поле является обязательным`,
        "string.empty": `Поле не должно быть пустым`,
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
            registrationDate: $(contactForm).find("#registrationDate").val(),
            description: $(contactForm).find("#description").val(),
            toolsCount: $(contactForm).find("#toolsCount").val(),
            manufacturer: $(contactForm).find("#manufacturer").val(),
            location: $(contactForm).find("#location").val(),
            deficiency: $(contactForm).find("#deficiency").val(),
            type: $(contactForm).find("#type").val(),
        });

        const initialErros = {
            registrationDate: null,
            description: null,
            toolsCount: null,
            manufacturer: null,
            location: null,
            deficiency: null,
            type: null,
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
