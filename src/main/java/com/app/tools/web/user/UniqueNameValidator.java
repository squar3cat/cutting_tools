package com.app.tools.web.user;

import com.app.tools.repository.DataJpaUserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import com.app.tools.HasIdAndName;
import com.app.tools.model.User;
import com.app.tools.web.ExceptionInfoHandler;


@Component
public class UniqueNameValidator implements org.springframework.validation.Validator {

    private final DataJpaUserRepository repository;

    public UniqueNameValidator(DataJpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return HasIdAndName.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HasIdAndName user = ((HasIdAndName) target);
        if (StringUtils.hasText(user.getName())) {
            User dbUser = repository.getByName(user.getName().toLowerCase());
            if (dbUser != null && !dbUser.getId().equals(user.getId())) {
                errors.rejectValue("name", ExceptionInfoHandler.EXCEPTION_USER_DUPLICATE_NAME);
            }
        }
    }
}
