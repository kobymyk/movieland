package db2.onlineshop.dao;

import db2.onlineshop.entity.MovieRating;

public interface RatingDao {
    void add(MovieRating movieRating);
    MovieRating getRating(int movieId, int userId);
}
