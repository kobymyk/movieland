package db2.onlineshop.service;

import db2.onlineshop.entity.MovieCompound;

public interface MovieChild {

    void addReference(MovieCompound movie);

    void editReference(MovieCompound movie);
}
