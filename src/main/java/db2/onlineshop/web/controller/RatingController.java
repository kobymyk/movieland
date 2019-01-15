package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Rating;
import db2.onlineshop.service.RatingService;
import db2.onlineshop.service.security.entity.Role;
import db2.onlineshop.service.security.holder.SecurityHolder;
import db2.onlineshop.web.data.RatingRequest;
import db2.onlineshop.web.data.RatingResponse;
import db2.onlineshop.web.data.mapper.RatingMapper;
import db2.onlineshop.web.handler.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/movie/{movieId}")
public class RatingController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RatingMapper RATING_MAPPER = new RatingMapper();

    private RatingService ratingService;

    @PostMapping(value = "/rate", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @Permission(roles = {Role.ADMIN, Role.USER})
    public void add(@PathVariable int movieId, @RequestBody RatingRequest ratingRequest) {
        log.info("add:movieId={};ratingRequest={}", movieId, ratingRequest);
        long startTime = System.currentTimeMillis();

        Rating rating = new Rating();
        rating.setMovieId(movieId);
        rating.setUserId(SecurityHolder.get().getId());
        rating.setRating(ratingRequest.getRating());

        ratingService.add(rating);

        log.info("add:duration={}", System.currentTimeMillis() - startTime);
    }

    @GetMapping(value = "/rating", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @Permission(roles = {Role.USER})
    public RatingResponse getByMovie(@PathVariable int movieId) {
        long startTime = System.currentTimeMillis();
        log.info("getByMovie:movieId={}", movieId);

        Rating rating = ratingService.getByMovie(movieId);
        RatingResponse result = RATING_MAPPER.mapObject(rating);
        log.info("getByMovie:result={}", result);
        log.info("getByMovie:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setRatingService(RatingService ratingService) {
        this.ratingService = ratingService;
    }
}
