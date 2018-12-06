package db2.onlineshop.service.fx.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
    @JsonProperty("r030")
    private int id;
    @JsonProperty("txt")
    private String name;
    private double rate;
    @JsonProperty("cc")
    private String code;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public String getCode() {
        return code;
    }
}
