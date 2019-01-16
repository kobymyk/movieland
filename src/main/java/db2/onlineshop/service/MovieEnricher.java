package db2.onlineshop.service;

import db2.onlineshop.entity.compound.MovieItems;

public interface MovieEnricher {

    void enrich(MovieItems movie);
}
