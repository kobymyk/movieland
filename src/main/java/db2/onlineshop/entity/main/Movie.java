package db2.onlineshop.entity.main;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieSequence")
    @SequenceGenerator(name = "movieSequence", sequenceName = "movie_seq", allocationSize = 1)
    private int id;
    @Column(name = "release_year")
    private int yearOfRelease;
    private String name;
    @Column(name = "name_native")
    private String nameNative;
    private String description;
    @Column(name = "picture_path")
    private String picturePath;
    private double rating;
    private double price;
    @Column(name = "country_code")
    private String countryCode;

    @Transient
    private List<Genre> genres;
    @Transient
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

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

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
                ", countryCode=" + countryCode +
                ", genres=" + genres +
                ", reviews=" + reviews +
                '}';
    }
}
