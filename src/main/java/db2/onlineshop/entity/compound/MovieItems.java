package db2.onlineshop.entity.compound;

import db2.onlineshop.entity.Movie;

import java.util.List;

public class MovieItems extends Movie {
    private List<CountryItem> countries;
    private List<GenreItem> genres;
    private List<ReviewItem> reviews;

    public MovieItems() {
    }

    public MovieItems(Movie movie) {
        this.id = movie.getId();
        this.yearOfRelease = movie.getYearOfRelease();
        this.name = movie.getName();
        this.nameNative = movie.getNameNative();
        this.description = movie.getDescription();
        this.picturePath = movie.getPicturePath();
        this.rating = movie.getRating();
        this.price = movie.getPrice();
    }

    public List<CountryItem> getCountries() {
        return countries;
    }

    public List<ReviewItem> getReviews() {
        return reviews;
    }

    public List<GenreItem> getGenres() {
        return genres;
    }

    public void setCountries(List<CountryItem> countries) {
        this.countries = countries;
    }

    public void setReviews(List<ReviewItem> reviews) {
        this.reviews = reviews;
    }

    public void setGenres(List<GenreItem> genres) {
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
