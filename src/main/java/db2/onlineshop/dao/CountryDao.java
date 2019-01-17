package db2.onlineshop.dao;

import db2.onlineshop.entity.Country;
import db2.onlineshop.entity.compound.MovieCompound;
import db2.onlineshop.entity.model.MovieCountry;

import java.util.List;

public interface CountryDao extends Persistent<MovieCountry>, MovieChild<Country>{

    List<MovieCountry> getAll();

    void addReference(MovieCompound movie);

    void updateReference(MovieCompound movie);
}
