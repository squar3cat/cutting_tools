const schema = joi.object({
    username: joi
        .string()
        .alphanum()
        .min(3)
        .max(15)
        .lowercase()
        .label("Имя")
        .required()
        .messages({
            "string.base": `Поле должно иметь текстовый формат`,
            "string.alphanum": `Поле должно содержать символы a-z и 0-9`,
            "string.empty": `Поле не должно быть пустым`,
            "string.min": `Поле должно быть длиной минимум {#limit} символов`,
            "string.max": `Поле должно быть длиной максимум {#limit} символов`,
            "string.lowercase": `Поле должно содержать буквы символы в нижнем регистре`,
            "any.required": `Поле является обязательным`
        }),
    password: joi
        .string()
        .alphanum()
        .min(5)
        .max(15)
        .lowercase()
        .label("Пароль")
        .required()
        .messages({
            "string.base": `Поле должно иметь текстовый формат`,
            "string.alphanum": `Поле должно содержать символы a-z и 0-9`,
            "string.empty": `Поле не должно быть пустым`,
            "string.min": `Поле должно быть длиной минимум {#limit} символов`,
            "string.max": `Поле должно быть длиной максимум {#limit} символов`,
            "string.lowercase": `Поле должно содержать буквы символы в нижнем регистре`,
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
            username: $(contactForm).find("#username").val(),
            password: $(contactForm).find("#password").val(),
        });

        const initialErros = {
            username: null,
            password: null,
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
