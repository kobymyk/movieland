package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Movie> getMovies();

    int updateMovie(Map<String, String> params);

    int addMovie(Map<String, String> params);

    Movie getMovie(String key);

    int removeMovie(String key);
}
