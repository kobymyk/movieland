package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.entity.SortParam;
import db2.onlineshop.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {
    private MovieService movieService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getAll(@RequestParam(value = "rating", required = false) String ratingDirection,
                              @RequestParam(value = "price", required = false) String priceDirection) {
        long startTime = System.currentTimeMillis();
        SortParam param = null;
        if (ratingDirection != null) {
            log.info("getAll:ratingDirection={}", ratingDirection);
            param = new SortParam("rating", SortOrder.getValue(ratingDirection));
        } else if (priceDirection != null) {
            log.info("getAll:priceDirection={}", priceDirection);
            param = new SortParam("price", SortOrder.getValue(priceDirection));
        }

        List<Movie> result = movieService.getAll(param);
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
