package db2.onlineshop.service.impl;

import db2.onlineshop.dao.RatingDao;
import db2.onlineshop.entity.Rating;
import db2.onlineshop.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicRatingService implements RatingService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private RatingDao ratingDao;

    @Override
    public void add(Rating rating) {
        log.debug("add:rating={}", rating);
        ratingDao.add(rating);
    }

    @Autowired
    public void setRatingDao(RatingDao ratingDao) {
        this.ratingDao = ratingDao;
    }
}
