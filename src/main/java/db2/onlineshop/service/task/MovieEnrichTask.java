package db2.onlineshop.service.task;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.CountryService;
import db2.onlineshop.service.MovieEnricher;

import java.util.concurrent.Callable;

public class MovieEnrichTask implements Callable<Movie> {
    private MovieEnricher enricher;
    private Movie movie;

    @Override
    public Movie call() throws Exception {
        enricher.enrich(movie);
        // todo: if (enricher instanceof CountryService) return movie.getCountries()
        return movie;
    }

    public void setEnricher(MovieEnricher enricher) {
        this.enricher = enricher;
    }

    public void setMovie(Movie movie) {
        // todo: movie.copy
        this.movie = new Movie();
        this.movie.setId(movie.getId());
        this.movie.setCountries(movie.getCountries());
    }
}
