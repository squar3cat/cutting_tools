package com.app.tools.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "locations", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "locations_unique_name_idx")})
public class Location extends AbstractNamedEntity {

    public Location() {
    }

    public Location(int id) {
        this(id, null);
    }

    public Location(Integer id, String name) {
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}