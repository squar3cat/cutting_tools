package com.app.tools.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.app.tools.model.User;

import java.util.List;

@Repository
public class DataJpaUserRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final UserRepository repository;

    public DataJpaUserRepository(UserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return repository.findAll(SORT_NAME);
    }

    public User getByName(String name) {
        return repository.getByName(name);
    }

    public User getWithTools(int id) {
        return repository.getWithTools(id);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }






}
