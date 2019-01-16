package db2.onlineshop.service.impl;

import db2.onlineshop.dao.RatingDao;
import db2.onlineshop.entity.MovieRating;
import db2.onlineshop.entity.User;
import db2.onlineshop.service.RatingService;
import db2.onlineshop.service.security.holder.SecurityHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicRatingService implements RatingService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private RatingDao ratingDao;

    @Override
    public void add(MovieRating movieRating) {
        log.debug("add:rating={}", movieRating);
        ratingDao.add(movieRating);
    }

    @Override
    public MovieRating getByMovie(int movieId) {
        log.debug("getByMovie:movieId={}", movieId);
        User user = SecurityHolder.get();
        log.debug("getByMovie:user={}", user);

        MovieRating result = ratingDao.getByMovie(movieId, user.getId());
        log.debug("getByMovie:result={}", result);

        return result;
    }

    @Autowired
    public void setRatingDao(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }
}
