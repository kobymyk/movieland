package db2.onlineshop.service.impl;

import db2.onlineshop.dao.ReviewDao;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Review;
import db2.onlineshop.service.MovieEnricher;
import db2.onlineshop.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicReviewService implements ReviewService, MovieEnricher {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ReviewDao reviewDao;

    @Override
    public List<Review> getByMovie(int movieId) {
        List<Review> result = reviewDao.getByMovie(movieId);
        log.info("getByMovie:result.size={}", result.size());
        log.trace("getByMovie:result={}", result);

        return result;
    }

    @Override
    public void enrich(Movie movie) {
        int movieId = movie.getId();
        List<Review> reviews = getByMovie(movieId);

        movie.setReviews(reviews);
    }

    @Autowired
    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
}
