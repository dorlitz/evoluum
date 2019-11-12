package com.evoluum.test.dto;

import java.io.Serializable;
import java.util.Map;

public class LocalityResume implements Serializable {

    private Integer idEstado;
    private String siglaEstado;
    private String regiaoNome;
    private String nomeCidade;
    private String nomeMesorregiao;
    private String nomeFormatado;

    public LocalityResume(Map<String, Object> values) {
        this.idEstado = values.get("estateId") != null ? (Integer) values.get("estateId") : null;
        this.siglaEstado = values.get("stateInitials") != null ? (String) values.get("stateInitials") : null;
        this.regiaoNome = values.get("regionName") != null ? (String) values.get("regionName") : null;
        this.nomeCidade = values.get("cityName") != null ? (String) values.get("cityName") : null;
        this.nomeMesorregiao = values.get("mesoName") != null ? (String) values.get("mesoName") : null;
        this.nomeFormatado = this.nomeCidade.concat("/").concat(this.siglaEstado);
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getRegiaoNome() {
        return regiaoNome;
    }

    public void setRegiaoNome(String regiaoNome) {
        this.regiaoNome = regiaoNome;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getNomeMesorregiao() {
        return nomeMesorregiao;
    }

    public void setNomeMesorregiao(String nomeMesorregiao) {
        this.nomeMesorregiao = nomeMesorregiao;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    public void setNomeFormatado(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }
}
