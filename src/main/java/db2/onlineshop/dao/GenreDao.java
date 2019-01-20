package db2.onlineshop.dao;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;

public interface GenreDao extends PersistOperation<Genre> {

    void addReference(Movie movie);

    void editReference(Movie movie);
}
