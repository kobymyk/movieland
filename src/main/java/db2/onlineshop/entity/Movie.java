package db2.onlineshop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movieSequence")
    @SequenceGenerator(name = "movieSequence", sequenceName = "movie_seq", allocationSize = 1)
    protected int id;
    @Column(name = "release_year")
    protected int yearOfRelease;
    protected String name;
    @Column(name = "name_native")
    protected String nameNative;
    protected String description;
    @Column(name = "picture_path")
    protected String picturePath;
    protected double rating;
    protected double price;

    @Transient
    private List<Country> countries;
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

    public List<Country> getCountries() {
        return countries;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
                ", countries=" + countries +
                ", genres=" + genres +
                ", reviews=" + reviews +
                '}';
    }
}
