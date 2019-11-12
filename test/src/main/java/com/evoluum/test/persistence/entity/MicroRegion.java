package com.evoluum.test.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MicroRegion {

    @Id
    private Integer id;

    private String name;

    private Integer mesoregionId;

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

    public Integer getMesoregionId() {
        return mesoregionId;
    }

    public void setMesoregionId(Integer mesoregionId) {
        this.mesoregionId = mesoregionId;
    }
}
