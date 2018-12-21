package db2.onlineshop.dao;

import db2.onlineshop.entity.Rating;

import java.util.List;

public interface RatingDao {

    List<Rating> getByMovie(int movieId);

    int add(Rating rating);

}
