package db2.onlineshop.dao;

import db2.onlineshop.entity.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll();

    List<Movie> getRandom(int size);

    List<Movie> getByGenreId(int genreId);
}
