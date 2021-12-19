package db2.onlineshop.service;

import db2.onlineshop.entity.main.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    List<Movie> getByGenre(int genreId);
    Movie getById(int id, String currency);
    void add(Movie movie);
    void edit(Movie movie);
}
