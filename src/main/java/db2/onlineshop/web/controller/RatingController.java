package db2.onlineshop.web.controller;

import db2.onlineshop.entity.MovieRating;
import db2.onlineshop.service.RatingService;
import db2.onlineshop.service.security.entity.Role;
import db2.onlineshop.service.security.holder.SecurityHolder;
import db2.onlineshop.web.data.RatingRequest;
import db2.onlineshop.web.data.RatingResponse;
import db2.onlineshop.web.data.ResponseMapper;
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
    private final ResponseMapper<MovieRating, RatingResponse> RATING_MAPPER = new ResponseMapper<>(RatingResponse.class);

    private RatingService ratingService;

    @PostMapping(value = "/rate", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Permission(roles = {Role.ROLE_ADMIN, Role.ROLE_USER})
    public void add(@PathVariable int movieId, @RequestBody RatingRequest ratingRequest) {
        log.info("add:movieId={};ratingRequest={}", movieId, ratingRequest);
        long startTime = System.currentTimeMillis();

        MovieRating movieRating = new MovieRating();
        movieRating.setMovieId(movieId);
        //todo:
        movieRating.setUserId(SecurityHolder.get().getId());
        movieRating.setRating(ratingRequest.getRating());

        ratingService.add(movieRating);

        log.info("add:duration={}", System.currentTimeMillis() - startTime);
    }

    @GetMapping(value = "/rating", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Permission(roles = {Role.ROLE_USER})
    public RatingResponse getByMovie(@PathVariable int movieId) {
        long startTime = System.currentTimeMillis();
        log.info("getByMovie:movieId={}", movieId);

        MovieRating movieRating = ratingService.getByMovie(movieId);
        RatingResponse result = RATING_MAPPER.mapObject(movieRating);
        log.info("getByMovie:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setRatingService(RatingService ratingService) {
        this.ratingService = ratingService;
    }
}
