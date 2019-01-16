package db2.onlineshop.service;

import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.compound.ReviewItem;

import java.util.List;

public interface ReviewService extends MovieEnricher {

    List<ReviewItem> getByMovie(int movieId);

    void add(int movieId, Review review);
}
