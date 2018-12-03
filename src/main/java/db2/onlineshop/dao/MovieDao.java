package db2.onlineshop.dao;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.SortParam;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll(SortParam param);

    List<Movie> getRandom(int size);

    List<Movie> getByGenre(int genreId);

    Movie getById(int id);
}
