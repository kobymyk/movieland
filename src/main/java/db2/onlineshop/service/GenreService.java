package db2.onlineshop.service;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.compound.GenreItem;

import java.util.List;

public interface GenreService extends MovieEnricher {

    List<Genre> getAll();

    List<GenreItem> getByMovie(int movieId);
}
