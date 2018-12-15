package db2.onlineshop.service.task;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Review;
import db2.onlineshop.service.MovieEnricher;

import java.util.ArrayList;
import java.util.List;

public class MovieEnrichParam {
    private MovieEnricher enricher;
    private Movie movie;

    public MovieEnrichParam(MovieEnricher enricher) {
        this.enricher = enricher;
    }

    public void setMovie(Movie movie) {
        // todo: movie.copy
        this.movie = new Movie();
        this.movie.setId(movie.getId());
        List<Country> countries = new ArrayList<>();
        countries = movie.getCountries();
        this.movie.setCountries(countries);
        List<Genre> genres = new ArrayList<>();
        genres = movie.getGenres();
        this.movie.setGenres(genres);
        List<Review> reviews = new ArrayList<>();
        reviews = movie.getReviews();
        this.movie.setReviews(reviews);
    }

    public MovieEnricher getEnricher() {
        return enricher;
    }

    public Movie getMovie() {
        return movie;
    }
}
