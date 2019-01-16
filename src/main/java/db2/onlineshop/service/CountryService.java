package db2.onlineshop.service;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.compound.CountryItem;

import java.util.List;

public interface CountryService extends MovieEnricher {

    List<CountryItem> getByMovie(int movieId);

    List<Country> getAll();
}
