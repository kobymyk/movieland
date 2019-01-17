package db2.onlineshop.service;

import db2.onlineshop.entity.MovieCompound;

public interface MovieEnricher {

    void enrich(MovieCompound movie);
}
