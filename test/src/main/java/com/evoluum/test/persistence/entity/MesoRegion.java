package com.evoluum.test.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MesoRegion {

    @Id
    private Integer id;

    private String name;

   private Integer federationId;

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

    public Integer getFederationId() {
        return federationId;
    }

    public void setFederationId(Integer federationId) {
        this.federationId = federationId;
    }
}
