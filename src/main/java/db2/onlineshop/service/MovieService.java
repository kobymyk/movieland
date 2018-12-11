package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.RequestParams;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(RequestParams param);

    List<Movie> getRandom();

    List<Movie> getByGenre(int genreId);

    Movie getById(int id, RequestParams param);

    int add(Movie movie);
}
