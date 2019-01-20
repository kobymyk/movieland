package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;

public interface MovieChild {

    void addReference(Movie movie);

    void editReference(Movie movie);
}
