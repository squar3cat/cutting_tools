package com.app.tools.service;

import com.app.tools.model.Location;
import com.app.tools.repository.location.DataJpaLocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.app.tools.util.ValidationUtil.checkNotFoundWithId;

@Service
public class LocationService {

    private final DataJpaLocationRepository repository;

    public LocationService(DataJpaLocationRepository repository) {
        this.repository = repository;
    }

    public Location get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Location> getAll() {
        return repository.getAll();
    }

    public Location create(Location location) {
        Assert.notNull(location, "location must not be null");
        return repository.save(location);
    }

    public void update(Location location) {
        Assert.notNull(location, "location must not be null");
        checkNotFoundWithId(repository.save(location), location.getId());
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

}