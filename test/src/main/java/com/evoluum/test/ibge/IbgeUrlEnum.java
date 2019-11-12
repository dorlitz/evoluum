package com.evoluum.test.ibge;

import java.util.Arrays;
import java.util.HashMap;

public enum IbgeUrlEnum {

    UNKNOWN(0, "unknown"),
    IBGE_SERVER(1, "https://servicodados.ibge.gov.br/api/v1/localidades/"),
    URL_STATES(2, IBGE_SERVER.getUrlValue().concat("estados")),
    URL_COUNTIES(3, IBGE_SERVER.getUrlValue().concat("estados/{UF}/municipios"));

    private String urlValue;
    private int position;

    private static HashMap<Integer, IbgeUrlEnum> enumByPosition = new HashMap<>();

    static {
        Arrays.stream(values()).forEach(e -> enumByPosition.put(e.getPosition(), e));
    }

    public static IbgeUrlEnum getByPosition(int position) {
        return enumByPosition.getOrDefault(position, UNKNOWN);
    }

    IbgeUrlEnum(int position, String urlValue){
        this.urlValue = urlValue;
        this.position = position;
    }

    public void setUrlvalue(String urlValue) {
        this.urlValue = urlValue;
    }

    public String getUrlValue() {
        return this.urlValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
