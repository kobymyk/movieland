package db2.onlineshop.service.task;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieEnricher;

import java.util.concurrent.Callable;

public class MovieEnrichTask implements Callable<MovieEnrichParam> {
    private MovieEnrichParam param;

    @Override
    public MovieEnrichParam call() throws Exception {
        MovieEnricher enricher = param.getEnricher();
        Movie movie = param.getMovie();
        enricher.enrich(movie);

        return param;
    }

    public void setParam(MovieEnrichParam param) {
        this.param = param;
    }
}
