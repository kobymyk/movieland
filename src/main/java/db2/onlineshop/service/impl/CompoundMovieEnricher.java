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
        log.debug("add");
        enrichers.add(enricher);
    }

    @Override
    public void enrich(Movie result) {
        log.debug("enrich");
        for (MovieEnricher enricher : enrichers) {
            enricher.enrich(result);
        }
    }
}
