package db2.onlineshop.service;

import db2.onlineshop.entity.compound.MovieItems;

public interface MovieChild {

    void addReference(MovieItems movie);

    void editReference(MovieItems movie);
}
