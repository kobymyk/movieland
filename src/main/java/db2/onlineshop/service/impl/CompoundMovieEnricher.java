package db2.onlineshop.service.impl;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieEnricher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

}
