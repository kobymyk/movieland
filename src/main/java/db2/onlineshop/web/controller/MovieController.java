package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.SortOrder;
import db2.onlineshop.entity.SortParam;
import db2.onlineshop.service.MovieService;
import db2.onlineshop.web.data.MovieSimpleDto;
import db2.onlineshop.web.utils.SortOrderSupport;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/movie")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ModelMapper modelMapper = new ModelMapper();

    private MovieService movieService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieSimpleDto> getAll(@RequestParam(value = "rating", required = false) SortOrder ratingOrder,
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

        List<Movie> movies = movieService.getAll(param);
        List<MovieSimpleDto> result = convertToDto(movies);
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(path = "/random", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieSimpleDto> getRandom() {
        long startTime = System.currentTimeMillis();
        List<Movie> movies = movieService.getRandom();
        List<MovieSimpleDto> result = convertToDto(movies);
        log.info("getRandom:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @GetMapping(value = "/genre/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<MovieSimpleDto> getByGenre(@PathVariable int id) {
        long startTime = System.currentTimeMillis();
        log.debug("getByGenre:id={}", id);
        List<Movie> movies = movieService.getByGenre(id);
        List<MovieSimpleDto> result = convertToDto(movies);
        log.info("getByGenre:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    //@JsonView(View.Full.class)
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Movie getById(@PathVariable int id, @RequestParam(name = "currency", required = false) String currency) {
        long startTime = System.currentTimeMillis();
        log.info("getById:id={},currency={}", id, currency);
        Movie result = movieService.getById(id, currency);
        log.info("getById:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    private MovieSimpleDto convertToDto(Movie movie) {
        MovieSimpleDto result = modelMapper.map(movie, MovieSimpleDto.class);

        return result;
    }

    private List<MovieSimpleDto> convertToDto(List<Movie> movies) {
        List<MovieSimpleDto> result = movies.stream()
                .map(movie -> convertToDto(movie))
                .collect(Collectors.toList());

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
