package db2.onlineshop.service.enricher;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieEnricher;

import java.util.concurrent.Callable;

public class MovieEnrichTask implements Callable<MovieEnrichParam> {
    private MovieEnrichParam param;

    public MovieEnrichTask(MovieEnrichParam param) {
        this.param = param;
    }

    @Override
    public MovieEnrichParam call() throws Exception {
        MovieEnricher enricher = param.getEnricher();
        Movie movie = param.getMovie();
        enricher.enrich(movie);

        return param;
    }
}
