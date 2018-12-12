package db2.onlineshop.service.impl;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieEnricher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class CompoundMovieEnricher implements MovieEnricher {
    private final Logger log = LoggerFactory.getLogger(getClass());

    List<MovieEnricher> enrichers = new ArrayList<>();

    public void add(MovieEnricher enricher) {
        enrichers.add(enricher);
    }

    @Override
    public void enrich(Movie result) {
        int movieId = result.getId();
        log.debug("enrich:movieId={}", movieId);
        for (MovieEnricher enricher : enrichers) {
            enricher.enrich(result);
        }
        //returns result
    }

    @Override
    public void addReference(Movie movie) {
        log.debug("addReference");
        for (MovieEnricher enricher : enrichers) {
            enricher.addReference(movie);
        }
    }

    @Override
    public void editReference(Movie movie) {
        log.debug("addReference");
        for (MovieEnricher enricher : enrichers) {
            enricher.editReference(movie);
        }
    }
}
