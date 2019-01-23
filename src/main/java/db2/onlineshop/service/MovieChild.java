package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;

public interface MovieChild<T> {

    void addReference(Movie movie);

}
