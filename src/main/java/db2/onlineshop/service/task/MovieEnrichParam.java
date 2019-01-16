package db2.onlineshop.service.task;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.compound.MovieItems;
import db2.onlineshop.service.MovieEnricher;

import java.util.ArrayList;
import java.util.List;

public class MovieEnrichParam {
    private MovieEnricher enricher;
    private MovieItems movie;

    public MovieEnrichParam(MovieEnricher enricher, MovieItems movie) {
        this.enricher = enricher;
        this.movie = copyMovie(movie);
    }

    private MovieItems copyMovie(MovieItems movie) {
        MovieItems result = new MovieItems();
        result.setId(movie.getId());

        return result;
    }

    public MovieEnricher getEnricher() {
        return enricher;
    }

    public MovieItems getMovie() {
        return movie;
    }
}
