package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Rating;
import db2.onlineshop.service.RatingService;
import db2.onlineshop.web.data.RatingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/movie/{movieId}")
public class RatingController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private RatingService ratingService;

    @PostMapping(value = "/rate", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void add(@PathVariable int movieId, @RequestBody RatingRequest ratingRequest) {
        log.info("rate:movieId={};ratingRequest={}", movieId, ratingRequest);
        long startTime = System.currentTimeMillis();
        Rating rating = new Rating();
        rating.setMovieId(movieId);
        ratingService.add(rating);

        log.info("rate:duration={}", System.currentTimeMillis() - startTime);
    }

    @Autowired
    public void setRatingService(RatingService ratingService) {
        this.ratingService = ratingService;
    }
}
