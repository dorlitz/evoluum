package com.evoluum.test.dto;

public class FederationDto {
    private Integer id;

    private String nome;

    private RegionDto regiao;

    private String sigla;

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public RegionDto getRegiao() {
        return regiao;
    }

    public void setRegiao(RegionDto regiao) {
        this.regiao = regiao;
    }
}
