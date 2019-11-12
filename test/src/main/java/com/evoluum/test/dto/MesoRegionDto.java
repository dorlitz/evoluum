package com.evoluum.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MesoRegionDto {

    private Integer id;

    private String nome;

   private FederationDto UF;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("UF")
    public FederationDto getUF() {
        return UF;
    }

    @JsonProperty("UF")
    public void setUF(FederationDto UF) {
        this.UF = UF;
    }
}
