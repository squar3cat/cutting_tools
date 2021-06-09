package com.app.tools.web.converter;

import com.app.tools.model.Location;
import com.app.tools.repository.location.DataJpaLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocationFormatter implements Formatter<Location> {

    @Autowired
    private DataJpaLocationRepository locationRepository;

    @Override
    public Location parse(String locationId, Locale locale) {
        return locationRepository.get(Integer.parseInt(locationId));
    }

    @Override
    public String print(Location location, Locale locale) {
        return location.getId().toString();
    }

}
