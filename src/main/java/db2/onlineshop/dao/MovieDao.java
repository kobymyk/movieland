package db2.onlineshop.dao;

import db2.onlineshop.entity.model.Movie;
import db2.onlineshop.entity.RequestParams;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll(RequestParams param);

    List<Movie> getRandom(int size);

    List<Movie> getByGenre(int genreId);

    Movie getById(int id);

    int add(Movie movie);

    void edit(Movie movie);
}
