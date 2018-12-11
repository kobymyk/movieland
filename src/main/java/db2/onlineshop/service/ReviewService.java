package db2.onlineshop.service;

import db2.onlineshop.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getByMovie(int movieId);

    void add(int movieId, Review review);
}
