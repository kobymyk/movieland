package db2.onlineshop.service;

import db2.onlineshop.entity.model.MovieCountry;
import db2.onlineshop.entity.Country;

import java.util.List;

public interface CountryService extends MovieEnricher {

    List<Country> getByMovie(int movieId);

    List<MovieCountry> getAll();
}
