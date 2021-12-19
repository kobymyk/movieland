package db2.onlineshop.service.impl;

import db2.onlineshop.dao.generic.ReviewDao;
import db2.onlineshop.entity.main.Review;
import db2.onlineshop.entity.main.MovieReview;
import db2.onlineshop.entity.main.Movie;
import db2.onlineshop.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BasicReviewService implements ReviewService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ReviewDao reviewDao;

    @Override
    @Transactional
    public void add(MovieReview movieReview) {
        log.debug("add:movieReview={}", movieReview);
        reviewDao.add(movieReview);
    }

    @Override
    public void enrich(Movie movie) {
        int movieId = movie.getId();
        log.debug("enrich:movieId={}", movieId);
        List<Review> reviews = reviewDao.listByKey("movieId", movieId);;

        movie.setReviews(reviews);
    }

    @Override
    public void merge(Movie movie, Movie result) {
        result.setReviews(movie.getReviews());
    }

    @Autowired
    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
}
