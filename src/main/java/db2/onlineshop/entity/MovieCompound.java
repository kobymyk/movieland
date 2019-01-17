package db2.onlineshop.entity;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.Movie;

import java.util.List;

public class MovieCompound {
    private Movie movie;
    private List<Country> countries;
    private List<Genre> movieGenres;
    private List<Review> reviews;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Genre> getMovieGenres() {
        return movieGenres;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setMovieGenres(List<Genre> movieGenres) {
        this.movieGenres = movieGenres;
    }

    @Override
    public String toString() {
        return "MovieCompound{" +
                "movie=" + movie +
                ", countries=" + countries +
                ", movieGenres=" + movieGenres +
                ", reviews=" + reviews +
                '}';
    }
}
