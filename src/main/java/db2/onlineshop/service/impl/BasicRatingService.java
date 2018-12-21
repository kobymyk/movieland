package db2.onlineshop.service.impl;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Rating;
import db2.onlineshop.service.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicRatingService implements RatingService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    //private CountryDao countryDao;

    @Override
    public List<Rating> getByMovie(int movieId) {
        log.debug("getByMovie:movieId={}", movieId);
        List<Rating> result = null;
        log.debug("getByMovie:result.size={}", result.size());

        return result;
    }

    @Override
    public void add(Rating rating) {
        log.debug("add:rating={}", rating);

    }

    @Override
    public void enrich(Movie movie) {
        int movieId = movie.getId();
        log.debug("enrich:movieId={}", movieId);
        //List<Country> countries = getByMovie(movieId);

        //movie.setCountries(countries);
    }

    //@Autowired
}
