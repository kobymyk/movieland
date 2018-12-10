package db2.onlineshop.dao;

import db2.onlineshop.entity.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> getByMovie(int movieId);

}
