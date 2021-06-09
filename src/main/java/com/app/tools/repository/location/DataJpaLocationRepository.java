package com.app.tools.repository.location;

import com.app.tools.model.Location;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaLocationRepository {

    private final LocationRepository locationRepository;

    public DataJpaLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location get(int id) {
        return locationRepository.findById(id)
                .orElse(null);
    }

    public List<Location> getAll() {
        return locationRepository.getAll();
    }

    @Transactional
    public Location save(Location location) {
        if (!location.isNew() && get(location.getId()) == null) {
            return null;
        }
        return locationRepository.save(location);
    }

    public boolean delete(int id) {
        return locationRepository.delete(id) != 0;
    }




}
