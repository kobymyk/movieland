package db2.onlineshop.dao;

import db2.onlineshop.entity.model.MovieRating;

public interface RatingDao {

    void add(MovieRating movieRating);

    MovieRating getByMovie(int movieId, int userId);

}
