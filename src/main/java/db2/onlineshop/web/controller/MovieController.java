package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/movie")
public class MovieController {
    private MovieService movieService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getAll() {
        long startTime = System.currentTimeMillis();
        List<Movie> result = movieService.getAll();
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(path = "/random", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getRandom() {
        long startTime = System.currentTimeMillis();
        List<Movie> result = movieService.getRandom();
        log.info("getRandom:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(value = "/genre/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getByGenre(@PathVariable int id) {
        long startTime = System.currentTimeMillis();
        log.debug("getByGenre:id={}", id);
        List<Movie> result = movieService.getByGenre(id);
        log.info("getByGenre:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
