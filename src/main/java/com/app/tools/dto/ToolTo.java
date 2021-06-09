package com.app.tools.dto;

import com.app.tools.model.Location;
import com.app.tools.model.Type;

import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Objects;

public class ToolTo extends BaseTo {

    private final String description;

    private final Integer toolsCount;

    private final String manufacturer;

    private final LocalDate registrationDate;

    private final Location location;

    private final Integer deficiency;

    private final Type type;

    private final boolean isDeficient;

    @ConstructorProperties({"id", "registrationDate", "description", "toolsCount", "manufacturer", "location", "deficiency", "type", "isDeficient"})
    public ToolTo(Integer id, LocalDate registrationDate, String description, Integer toolsCount, String manufacturer, Location location, Integer deficiency, Type type, boolean isDeficient) {
        this.id = id;
        this.registrationDate=registrationDate;
        this.description=description;
        this.toolsCount=toolsCount;
        this.manufacturer=manufacturer;
        this.location=location;
        this.deficiency=deficiency;
        this.type=type;
        this.isDeficient=isDeficient;
    }

    public String getDescription() {
        return description;
    }

    public Integer getToolsCount() {
        return toolsCount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Location getLocation() {
        return location;
    }

    public Integer getDeficiency() {
        return deficiency;
    }

    public Type getType() {
        return type;
    }

    public boolean getIsDeficient() { return isDeficient; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToolTo toolTo = (ToolTo) o;
        return isDeficient == toolTo.isDeficient &&
                description.equals(toolTo.description) &&
                toolsCount.equals(toolTo.toolsCount) &&
                manufacturer.equals(toolTo.manufacturer) &&
                registrationDate.equals(toolTo.registrationDate) &&
                location.equals(toolTo.location) &&
                deficiency.equals(toolTo.deficiency) &&
                type.equals(toolTo.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, toolsCount, manufacturer, registrationDate, location, deficiency, type, isDeficient);
    }

    @Override
    public String toString() {
        return "ToolTo{" +
                "id=" + id +
                ", description=" + description +
                ", toolsCount='" + toolsCount +
                ", manufacturer=" + manufacturer +
                ", registrationDate=" + registrationDate +
                ", location=" + location +
                ", deficiency=" + deficiency +
                ", type=" + type +
                ", isDeficient=" + isDeficient +
                '}';
    }
}
