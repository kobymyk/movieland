package db2.onlineshop.service;

import db2.onlineshop.entity.Country;

import java.util.List;

public interface CountryService {

    List<Country> getByMovie(int movieId);
}
