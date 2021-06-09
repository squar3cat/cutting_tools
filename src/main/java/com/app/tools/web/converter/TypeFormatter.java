package com.app.tools.web.converter;

import com.app.tools.model.Type;
import com.app.tools.repository.type.DataJpaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TypeFormatter implements Formatter<Type> {

    @Autowired
    private DataJpaTypeRepository typeRepository;

    @Override
    public Type parse(String typeId, Locale locale) {
        return typeRepository.get(Integer.parseInt(typeId));
    }

    @Override
    public String print(Type type, Locale locale) {
        return type.getId().toString();
    }

}
