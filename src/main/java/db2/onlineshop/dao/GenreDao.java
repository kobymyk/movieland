package db2.onlineshop.dao;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    List<Genre> getByMovie(int movieId);

    void addReference(Movie movie);
}
