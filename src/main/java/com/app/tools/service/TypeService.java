package com.app.tools.service;

import com.app.tools.model.Type;
import com.app.tools.repository.type.DataJpaTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.app.tools.util.ValidationUtil.checkNotFoundWithId;

@Service
public class TypeService {

    private final DataJpaTypeRepository repository;

    public TypeService(DataJpaTypeRepository repository) {
        this.repository = repository;
    }

    public Type get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Type> getAll() {
        return repository.getAll();
    }

    public Type create(Type type) {
        Assert.notNull(type, "type must not be null");
        return repository.save(type);
    }

    public void update(Type type) {
        Assert.notNull(type, "type must not be null");
        checkNotFoundWithId(repository.save(type), type.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

}