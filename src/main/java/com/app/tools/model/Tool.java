package com.app.tools.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import com.app.tools.View;
import com.app.tools.View.ValidatedUI;
import com.app.tools.util.DateUtil;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.time.LocalDate;

@Entity
@Table(name = "tools", uniqueConstraints = {@UniqueConstraint(columnNames = {"description", "location"}, name = "tools_unique_description_location_idx")})
public class Tool extends AbstractBaseEntity {

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 3, max = 120, groups = {ValidatedUI.class, Default.class})
    private String description;

    @Column(name = "tools_count", nullable = false)
    @NotNull(groups = {ValidatedUI.class, Default.class})
    @Range(min = 0, max = 50000, groups = {ValidatedUI.class, Default.class})
    private Integer toolsCount;

    @Column(name = "manufacturer", nullable = false)
    @NotBlank
    @Size(min = 2, max = 50, groups = {ValidatedUI.class, Default.class})
    private String manufacturer;

    @Column(name = "registration_date", nullable = false)
    @NotNull(groups = {ValidatedUI.class, Default.class})
    @DateTimeFormat(pattern = DateUtil.DATE_PATTERN)
    private LocalDate registrationDate;

    @NotNull(groups = View.Persist.class)
    @OneToOne()
    @JoinColumn(name="location", referencedColumnName = "id", nullable = false)
    private Location location;

    @Column(name = "deficiency", nullable = false)
    @Range(min = 1, max = 10000, groups = {ValidatedUI.class, Default.class})
    @NotNull(groups = {ValidatedUI.class, Default.class})
    private Integer deficiency;

    @NotNull(groups = View.Persist.class)
    @OneToOne()
    @JoinColumn(name="type", referencedColumnName = "id", nullable = false)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull(groups = View.Persist.class)
    private User user;

    public Tool() {
    }

    public Tool(LocalDate registrationDate, String description, Integer toolsCount, String manufacturer, Location location, Integer deficiency, Type type) {
        this(null, registrationDate, description, toolsCount, manufacturer, location, deficiency, type);
    }

    public Tool(Integer id, LocalDate registrationDate, String description, Integer toolsCount, String manufacturer, Location location, Integer deficiency, Type type) {
        super(id);
        this.registrationDate=registrationDate;
        this.description=description;
        this.toolsCount=toolsCount;
        this.manufacturer=manufacturer;
        this.location=location;
        this.deficiency=deficiency;
        this.type=type;
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

    public User getUser() {
        return user;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setToolsCount(Integer toolsCount) { this.toolsCount = toolsCount; }

    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public void setLocation(Location location) { this.location = location; }

    public void setDeficiency(Integer deficiency) { this.deficiency = deficiency; }

    public void setType(Type type) { this.type = type; }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", description=" + description +
                ", toolsCount=" + toolsCount +
                ", manufacturer=" + manufacturer +
                ", registrationDate=" + registrationDate +
                ", location=" + location +
                ", deficiency=" + deficiency +
                ", type=" + type +
                '}';
    }
}
