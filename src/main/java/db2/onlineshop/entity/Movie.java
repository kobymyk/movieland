package db2.onlineshop.entity;

import java.util.List;

public class Movie {
    private int id;
    private int yearOfRelease;

    private String name;
    private String nameNative;
    private String description;
    private String picturePath;

    private double rating;
    private double price;

    private List<Country> countries;
    private List<Genre> genres;
    private List<Review> reviews;

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

    public List<Country> getCountries() {
        return countries;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Genre> getGenres() {
        return genres;
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

    public void setDescription(String description) { this.description = description; }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
