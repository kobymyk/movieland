package db2.onlineshop.service;

import db2.onlineshop.entity.main.Genre;

import java.util.List;

public interface GenreService extends MovieEnricher {

    List<Genre> getAll();

}
