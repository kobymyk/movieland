package db2.onlineshop.dao;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.compound.GenreItem;
import db2.onlineshop.entity.compound.MovieItems;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    List<GenreItem> getByMovie(int movieId);

    void addReference(MovieItems movie);

    void editReference(MovieItems movie);
}
