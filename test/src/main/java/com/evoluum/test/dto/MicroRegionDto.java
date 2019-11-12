package com.evoluum.test.dto;

public class MicroRegionDto {

    private Integer id;

    private String nome;

    private MesoRegionDto mesorregiao;

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

    public MesoRegionDto getMesorregiao() {
        return mesorregiao;
    }

    public void setMesorregiao(MesoRegionDto mesorregiao) {
        this.mesorregiao = mesorregiao;
    }
}
