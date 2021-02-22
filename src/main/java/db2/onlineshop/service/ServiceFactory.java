package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ServiceFactory {
    private final List<Object> services;
    private final List<MovieEnricher> enrichers;
    private final List<Child<Movie>> movieChildren;

    public ServiceFactory(List<MovieEnricher> enrichers, List<Child<Movie>> movieChildren) {
        this.enrichers = enrichers;
        this.movieChildren = movieChildren;
        //todo:
        this.services = Collections.EMPTY_LIST;
    }

    public List<MovieEnricher> getEnrichers() {
        return enrichers;
    }

    public List<Child<Movie>> getMovieChildren() {
        return movieChildren;
    }

    public List<Object> getAll() {
        //services = Arrays.asList(genreService, countryService, reviewService);
        return services;
    }

}
