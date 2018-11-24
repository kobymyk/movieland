package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/movie")
public class MovieController {
    private MovieService movieService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getAll() {
        long startTime = System.currentTimeMillis();
        log.debug("getAll:startTime={}", startTime);
        List<Movie> result = movieService.getAll();
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(path = "/random", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getRandom() {
        long startTime = System.currentTimeMillis();
        log.debug("getRandom:startTime={}", startTime);
        List<Movie> result = movieService.getRandom();
        log.info("getRandom:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }
}
