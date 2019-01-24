package db2.onlineshop.service.enricher;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieEnricher;

public class MovieEnrichParam {
    private MovieEnricher enricher;
    private Movie movie;

    public MovieEnrichParam(MovieEnricher enricher, Movie movie) {
        this.enricher = enricher;
        this.movie = copyMovie(movie);
    }

    private Movie copyMovie(Movie movie) {
        Movie result = new Movie();
        result.setId(movie.getId());

        return result;
    }

    public MovieEnricher getEnricher() {
        return enricher;
    }

    public Movie getMovie() {
        return movie;
    }
}
