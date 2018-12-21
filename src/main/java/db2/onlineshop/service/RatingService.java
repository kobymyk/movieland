package db2.onlineshop.service;

import db2.onlineshop.entity.Rating;

import java.util.List;

public interface RatingService extends MovieEnricher {

    List<Rating> getByMovie(int movieId);

    void add(Rating rating);
}
