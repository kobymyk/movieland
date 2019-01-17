package db2.onlineshop.dao;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.compound.MovieCompound;
import db2.onlineshop.entity.model.MovieGenre;

import java.util.List;

public interface GenreDao {

    List<MovieGenre> getAll();

    List<Genre> getByMovie(int movieId);

    void addReference(MovieCompound movie);

    void editReference(MovieCompound movie);
}
