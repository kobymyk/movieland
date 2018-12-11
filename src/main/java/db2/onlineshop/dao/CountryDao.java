package db2.onlineshop.dao;

import db2.onlineshop.entity.Country;

import java.util.List;

public interface CountryDao {

    List<Country> getByMovie(int movieId);

    List<Country> getAll();

    void addReference(int movieId, List<Integer> countryIds);
}
