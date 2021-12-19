package db2.onlineshop.service;

import db2.onlineshop.entity.main.MovieReview;

public interface ReviewService extends MovieEnricher {
    void add(MovieReview movieReview);
}
