package db2.onlineshop.service;

import db2.onlineshop.entity.compound.MovieCompound;

public interface MovieEnricher {

    void enrich(MovieCompound movie);
}
