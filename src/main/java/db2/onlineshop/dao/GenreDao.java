package db2.onlineshop.dao;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.MovieCompound;

public interface GenreDao extends PersistOperation<Genre> {

    void addReference(MovieCompound movie);

    void editReference(MovieCompound movie);
}
