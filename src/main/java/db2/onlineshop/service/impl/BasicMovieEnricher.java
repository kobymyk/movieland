package db2.onlineshop.service.impl;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieEnricher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
//@Primary
public class BasicMovieEnricher implements MovieEnricher {
    private final Logger log = LoggerFactory.getLogger(getClass());
    // todo: Spring provider
    private MovieEnricher genreService;
    private MovieEnricher countryService;
    private MovieEnricher reviewService;

    @Override
    public void enrich(Movie result) {

        int movieId = result.getId();
        log.debug("enrich:movieId={}", movieId);
        // todo: fori List< MovieEnricher> enricherList;
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
    public void setGenreService(MovieEnricher genreService) {
        this.genreService = genreService;
    }

    @Autowired
    @Qualifier("basicCountryService")
    public void setCountryService(MovieEnricher countryService) {
        this.countryService = countryService;
    }

    @Autowired
    @Qualifier("basicReviewService")
    public void setReviewService(MovieEnricher reviewService) {
        this.reviewService = reviewService;
    }
}
