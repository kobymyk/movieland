package db2.onlineshop.dao;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.MovieCompound;

public interface CountryDao extends PersistOperation<Country> {

    void addReference(MovieCompound movie);

    void updateReference(MovieCompound movie);
}
