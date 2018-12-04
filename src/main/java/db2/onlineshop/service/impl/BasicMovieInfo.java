package db2.onlineshop.service.impl;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BasicMovieInfo implements MovieInfo {
    private final Logger log = LoggerFactory.getLogger(getClass());
    // todo: add enrich obj
    private MovieInfo genreService;
    private MovieInfo countryService;
    private MovieInfo reviewService;

    @Override
    public void enrich(Movie result) {

        int movieId = result.getId();
        log.debug("enrich:movieId={}", movieId);
        // todo: fori
        genreService.enrich(result);
        log.trace("enrich:result={}", result);
        countryService.enrich(result);
        log.trace("enrich:result={}", result);
        reviewService.enrich(result);
        log.trace("enrich:result={}", result);

        //returns result
    }

    @Autowired
    @Qualifier("basicGenreService")
    public void setGenreService(MovieInfo genreService) {
        this.genreService = genreService;
    }

    @Autowired
    @Qualifier("basicCountryService")
    public void setCountryService(MovieInfo countryService) {
        this.countryService = countryService;
    }

    @Autowired
    @Qualifier("basicReviewService")
    public void setReviewService(MovieInfo reviewService) {
        this.reviewService = reviewService;
    }
}
