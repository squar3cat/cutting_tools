const schema = joi.object({
    nameLocation: joi
        .string()
        .min(2)
        .max(100)
        .label("Наименование")
        .required()
        .messages({
            "string.base": `Поле не должно быть пустым`,
            "string.empty": `Поле не должно быть пустым`,
            "string.min": `Поле должно быть длиной минимум {#limit} символов`,
            "string.max": `Поле должно быть длиной максимум {#limit} символов`,
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
            nameLocation: $(contactForm).find("#nameLocation").val(),
        });

        const initialErros = {
            nameLocation: null,
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
