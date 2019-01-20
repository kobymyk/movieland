package db2.onlineshop.dao;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.Movie;

public interface CountryDao extends PersistOperation<Country> {

    void addReference(Movie movie);

    void updateReference(Movie movie);
}
