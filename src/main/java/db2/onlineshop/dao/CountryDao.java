package db2.onlineshop.dao;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.compound.CountryItem;
import db2.onlineshop.entity.compound.MovieItems;

import java.util.List;

public interface CountryDao {

    List<CountryItem> getByMovie(int movieId);

    List<Country> getAll();

    void addReference(MovieItems movie);

    void updateReference(MovieItems movie);
}
