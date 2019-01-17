package db2.onlineshop.service.impl;

import db2.onlineshop.entity.MovieCompound;
import db2.onlineshop.service.MovieChild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class CompoundMovieChild implements MovieChild {
    private final Logger log = LoggerFactory.getLogger(getClass());

    List<MovieChild> children = new ArrayList<>();

    public void add(MovieChild child) {
        children.add(child);
    }


    @Override
    public void addReference(MovieCompound movie) {
        log.debug("addReference");
        for (MovieChild child : children) {
            child.addReference(movie);
        }
    }

    @Override
    public void editReference(MovieCompound movie) {
        log.debug("addReference");
        for (MovieChild child : children) {
            child.editReference(movie);
        }
    }
}
