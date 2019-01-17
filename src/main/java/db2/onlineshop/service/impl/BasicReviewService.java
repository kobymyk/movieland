package db2.onlineshop.service.impl;

import db2.onlineshop.dao.ReviewDao;
import db2.onlineshop.entity.model.MovieReview;
import db2.onlineshop.entity.compound.MovieCompound;
import db2.onlineshop.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicReviewService implements ReviewService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ReviewDao reviewDao;

    @Override
    public List<db2.onlineshop.entity.Review> getByMovie(int movieId) {
        log.debug("getByMovie:movieId={}", movieId);
        List<db2.onlineshop.entity.Review> result = reviewDao.getByMovie(movieId);

        return result;
    }

    @Override
    public void add(MovieReview movieReview) {
        log.debug("add:movieReview={}", movieReview);
        reviewDao.add(movieReview);
    }

    @Override
    public void enrich(MovieCompound movie) {
        int movieId = movie.getId();
        log.debug("enrich:movieId={}", movieId);
        List<db2.onlineshop.entity.Review> reviews = getByMovie(movieId);

        movie.setReviews(reviews);
    }

    @Autowired
    public void setReviewDao(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }
}
