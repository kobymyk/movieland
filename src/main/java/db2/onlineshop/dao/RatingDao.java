package db2.onlineshop.dao;

import db2.onlineshop.entity.Rating;

public interface RatingDao {

    void add(Rating rating);

    Rating getByMovie(int movieId, int userId);

}
