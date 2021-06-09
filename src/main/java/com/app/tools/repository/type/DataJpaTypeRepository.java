package com.app.tools.repository.type;

import com.app.tools.model.Type;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaTypeRepository {

    private final TypeRepository typeRepository;

    public DataJpaTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type get(int id) {
        return typeRepository.findById(id)
                .orElse(null);
    }

    public List<Type> getAll() {
        return typeRepository.getAll();
    }

    @Transactional
    public Type save(Type type) {
        if (!type.isNew() && get(type.getId()) == null) {
            return null;
        }
        return typeRepository.save(type);
    }

    public boolean delete(int id) {
        return typeRepository.delete(id) != 0;
    }




}
