package db2.onlineshop.service;

import db2.onlineshop.entity.model.MovieRating;

public interface RatingService {

    void add(MovieRating movieRating);

    MovieRating getByMovie(int movieId);
}
