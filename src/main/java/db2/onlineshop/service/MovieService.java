package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;
import java.util.List;

public interface MovieService {

    List<Movie> getAll();

    List<Movie> getRandom();

    List<Movie> getByGenre(int genreId);
}
