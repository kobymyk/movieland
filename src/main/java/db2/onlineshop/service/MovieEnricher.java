package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;

public interface MovieEnricher {

    void enrich(Movie movie);

    void merge(Movie movie, Movie result);
}
