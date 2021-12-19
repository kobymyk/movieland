package db2.onlineshop.dao.generic;

import db2.onlineshop.entity.main.MovieRating;

public interface RatingDao {
    void add(MovieRating movieRating);
    MovieRating getRating(int movieId, int userId);
}
