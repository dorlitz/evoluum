package com.evoluum.test.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Region {

    @Id
    private Integer id;

    private String name;

    private String initials;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
}
