package db2.onlineshop.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.entity.SortParam;
import db2.onlineshop.service.MovieService;
import db2.onlineshop.web.data.View;
import db2.onlineshop.web.utils.SortOrderSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {
    private MovieService movieService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @JsonView(View.Simple.class)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getAll(@RequestParam(value = "rating", required = false) SortOrder ratingOrder,
                              @RequestParam(value = "price", required = false) SortOrder priceOrder) {
        long startTime = System.currentTimeMillis();
        SortParam param = null;
        if (ratingOrder != null) {
            log.info("getAll:ratingOrder={}", ratingOrder);
            param = new SortParam("rating", ratingOrder);
        } else if (priceOrder != null) {
            log.info("getAll:priceOrder={}", priceOrder);
            param = new SortParam("price", priceOrder);
        }

        List<Movie> result = movieService.getAll(param);
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @JsonView(View.Simple.class)
    @GetMapping(path = "/random", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getRandom() {
        long startTime = System.currentTimeMillis();
        List<Movie> result = movieService.getRandom();
        log.info("getRandom:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @JsonView(View.Simple.class)
    @GetMapping(value = "/genre/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Movie> getByGenre(@PathVariable int id) {
        long startTime = System.currentTimeMillis();
        log.debug("getByGenre:id={}", id);
        List<Movie> result = movieService.getByGenre(id);
        log.info("getByGenre:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    //@JsonView(View.Full.class)
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Movie getById(@PathVariable int id) {
        long startTime = System.currentTimeMillis();
        log.info("getById:id={}", id);
        Movie result = movieService.getById(id);
        log.info("getById:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(SortOrder.class, new SortOrderSupport());
    }
}
