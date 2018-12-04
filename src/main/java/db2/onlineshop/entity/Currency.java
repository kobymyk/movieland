package db2.onlineshop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency {
    @JsonProperty("r030")
    private int id;
    @JsonProperty("txt")
    private String name;
    private double rate;
    @JsonProperty("cc")
    private String code;
}
