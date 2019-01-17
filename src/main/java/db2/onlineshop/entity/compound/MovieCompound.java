package db2.onlineshop.entity.compound;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.model.Movie;

import java.util.List;

public class MovieCompound extends Movie {
    private List<Country> countries;
    private List<Genre> genres;
    private List<Review> reviews;

    public MovieCompound() {
    }

    public MovieCompound(Movie movie) {
        this.id = movie.getId();
        this.yearOfRelease = movie.getYearOfRelease();
        this.name = movie.getName();
        this.nameNative = movie.getNameNative();
        this.description = movie.getDescription();
        this.picturePath = movie.getPicturePath();
        this.rating = movie.getRating();
        this.price = movie.getPrice();
    }

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
        return "MovieItems{" +
                "countries=" + countries +
                ", genres=" + genres +
                ", reviews=" + reviews +
                '}';
    }
}
