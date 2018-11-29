package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.SortParam;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(SortParam param);

    List<Movie> getRandom();

    List<Movie> getByGenre(int genreId);
}
