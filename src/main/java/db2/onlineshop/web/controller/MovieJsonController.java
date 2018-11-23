package db2.onlineshop.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/movie")
public class MovieJsonController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Value("${movie.randomCount:3}")
    private int randomCount;

    @Autowired
    private MovieService movieService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getAllMovies() {
        log.info("getMoviesAsJson/start");
        long startTime = System.currentTimeMillis();
        List<Movie> result = movieService.getMovies();
        log.info("getMoviesAsJson:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Movie> getRandomMovies() {
        log.info("getJsonRandomMovies/start");
        long startTime = System.currentTimeMillis();
        List<Movie> result = movieService.getRandomMovies(randomCount);
        log.info("getJsonRandomMovies:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }
}
