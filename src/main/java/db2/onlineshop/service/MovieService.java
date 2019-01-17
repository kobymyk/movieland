package db2.onlineshop.service;

import db2.onlineshop.entity.MovieCompound;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Ordering;

import java.util.List;

public interface MovieService {

    List<Movie> getAll(Ordering ordering);

    List<Movie> getByGenre(int genreId);

    MovieCompound getById(int id, String currency);

    void add(MovieCompound movie);

    void edit(MovieCompound movie);
}
