package com.app.tools.model;

import com.app.tools.View;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "types", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "parent_id"}, name = "types_unique_name_parent_id_idx")})
public class Type extends AbstractNamedEntity {

    @Column(name = "parent_id")
    @Range(min = 0, max = 1000000, groups = {View.ValidatedUI.class, Default.class})
    @NotNull(groups = {View.ValidatedUI.class, Default.class})
    private Integer parentId;

    @Column(name = "level", nullable = false)
    @Range(min = 0, max = 10, groups = {View.ValidatedUI.class, Default.class})
    @NotNull(groups = {View.ValidatedUI.class, Default.class})
    private Integer level;

    @Column(name = "final_type", nullable = false, columnDefinition = "bool default false")
    private boolean finalType = false;

    public Type() {
    }

    public Type(Integer id) {
        this(id, null, null, null, false);
    }

    public Type(Integer id, String name, Integer parentId, Integer level, boolean finalType) {
        this.id=id;
        this.name=name;
        this.parentId=parentId;
        this.level=level;
        this.finalType=finalType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isFinalType() {
        return finalType;
    }

    public void setFinalType(boolean finalType) {
        this.finalType = finalType;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name=" + name +
                ", parentId=" + parentId +
                ", level=" + level +
                ", finalType=" + finalType +
                '}';
    }

}