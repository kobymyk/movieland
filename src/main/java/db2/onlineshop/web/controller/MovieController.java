package db2.onlineshop.web.controller;

import db2.onlineshop.entity.MovieCompound;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.entity.Ordering;
import db2.onlineshop.service.MovieService;
import db2.onlineshop.service.security.entity.Role;
import db2.onlineshop.web.data.MovieAddRequest;
import db2.onlineshop.web.data.MovieEditRequest;
import db2.onlineshop.web.data.MovieResponse;
import db2.onlineshop.web.data.ResponseMapper;
import db2.onlineshop.web.handler.Permission;
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
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ResponseMapper<Movie, MovieResponse> MOVIE_MAPPER = new ResponseMapper(MovieResponse.class);

    private MovieService movieService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieResponse> getAll(@RequestParam(value = "rating", required = false) SortOrder ratingOrder,
                                      @RequestParam(value = "price", required = false) SortOrder priceOrder) {
        long startTime = System.currentTimeMillis();
        Ordering param = null;
        if (ratingOrder != null) {
            log.info("getAll:ratingOrder={}", ratingOrder);
            param = new Ordering("rating", ratingOrder);
        } else if (priceOrder != null) {
            log.info("getAll:priceOrder={}", priceOrder);
            param = new Ordering("price", priceOrder);
        }

        List<Movie> movies = movieService.getAll(param);
        List<MovieResponse> result = MOVIE_MAPPER.mapList(movies);
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(value = "/genre/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieResponse> getByGenre(@PathVariable int id) {
        long startTime = System.currentTimeMillis();
        log.debug("getByGenre:id={}", id);
        List<Movie> movies = movieService.getByGenre(id);
        List<MovieResponse> result = MOVIE_MAPPER.mapList(movies);
        log.info("getByGenre:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public MovieCompound getById(@PathVariable int id,
                         @RequestParam(name = "currency", required = false, defaultValue = "UAH") String currency) {
        long startTime = System.currentTimeMillis();
        log.info("getById:id={},currency={}", id, currency);

        MovieCompound result = movieService.getById(id, currency);
        log.info("getById:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @Permission(roles = Role.ADMIN)
    public void add(@RequestBody MovieAddRequest request) {
        long startTime = System.currentTimeMillis();
        log.info("add:request={}", request);
        MovieCompound movie = request.getMovie();
        log.info("add:movie={}", movie);

        movieService.add(movie);
        log.info("add:duration={}", System.currentTimeMillis() - startTime);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @Permission(roles = Role.ADMIN)
    public void edit(@RequestBody MovieEditRequest request, @PathVariable int id) {
        long startTime = System.currentTimeMillis();
        log.info("edit:id={}", id);
        MovieCompound movie = request.getMovie();
        //movie.setId(id);
        log.debug("edit:movie={}", movie);

        movieService.edit(movie);
        log.info("edit:duration={}", System.currentTimeMillis() - startTime);
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
