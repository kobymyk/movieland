package db2.onlineshop.web.controller;

import db2.onlineshop.entity.MovieReview;
import db2.onlineshop.service.ReviewService;
import db2.onlineshop.service.security.entity.Role;
import db2.onlineshop.service.security.holder.SecurityHolder;
import db2.onlineshop.web.data.ReviewRequest;
import db2.onlineshop.web.handler.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/review")
public class ReviewController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ReviewService reviewService;

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @Permission(roles = {Role.ADMIN, Role.USER})
    public void add(@RequestBody ReviewRequest reviewRequest) {
        long startTime = System.currentTimeMillis();
        log.info("add:reviewRequest={}", reviewRequest);

        MovieReview movieReview = new MovieReview();
        movieReview.setMovieId(reviewRequest.getMovieId());
        movieReview.setUser(SecurityHolder.get());
        movieReview.setText(reviewRequest.getText());
        log.info("add:movieReview={}", movieReview);

        reviewService.add(movieReview);
        log.info("add:duration={}", System.currentTimeMillis() - startTime);
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

}
