package db2.onlineshop.entity;

import java.util.List;

public class Movie {
    protected int id;
    protected int yearOfRelease;
    protected String name;
    protected String nameNative;
    protected String description;
    protected String picturePath;
    protected double rating;
    protected double price;

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

    public String getDescription() { return description; }


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

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", yearOfRelease=" + yearOfRelease +
                ", name='" + name + '\'' +
                ", nameNative='" + nameNative + '\'' +
                ", description='" + description + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }
}
