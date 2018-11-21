package db2.onlineshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "picturePath" })
public class Movie {
    private int id;
    private String name;
    private String nameNative;
    private int yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameNative() {
        return nameNative;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameNative(String nameNative) {
        this.nameNative = nameNative;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Movie(int id, String name, String nameNative, int yearOfRelease, double rating, double price, String picturePath) {
        this.id = id;
        this.name = name;
        this.nameNative = nameNative;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
        this.price = price;
        this.picturePath = picturePath;
    }

    public Movie() {
    }
}
