package db2.onlineshop.dao;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Movie;

import java.util.List;

public interface CountryDao {

    List<Country> getByMovie(int movieId);

    List<Country> getAll();

    void addReference(Movie movie);

    void updateReference(Movie movie);
}
