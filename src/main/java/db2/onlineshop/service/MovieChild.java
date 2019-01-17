package db2.onlineshop.service;

import db2.onlineshop.entity.compound.MovieCompound;

public interface MovieChild {

    void addReference(MovieCompound movie);

    void editReference(MovieCompound movie);
}
