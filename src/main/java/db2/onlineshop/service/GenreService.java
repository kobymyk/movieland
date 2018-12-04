package db2.onlineshop.service;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    List<Genre> getByMovie(int movieId);
}
