package db2.onlineshop.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import db2.onlineshop.web.templater.PageGenerator;

import db2.onlineshop.web.transform.JsonConverter;
import db2.onlineshop.web.transform.impl.DefaultJsonConverter;
import db2.onlineshop.web.transform.impl.ParamConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    private static final String KEY = "id";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    private final PageGenerator PAGE_GENERATOR = PageGenerator.instance();
    private final JsonConverter JSON_CONVERTER = new DefaultJsonConverter();

    @RequestMapping(path = "/movies", method = RequestMethod.GET)
    @ResponseBody
    public String getHtmlMovies() {
        log.info("MovieController/start");
        long startTime = System.currentTimeMillis();
        List<Movie> items = movieService.getMovies();
        Map<String, Object> data = ParamConverter.fromList(items, "movies");
        String result = PAGE_GENERATOR.getPage("movies", data);
        log.info("MovieController:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @RequestMapping(path = "/v1/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getJsonMovies() throws JsonProcessingException {
        log.info("getMoviesAsJson/start");
        long startTime = System.currentTimeMillis();
        List<Movie> items = movieService.getMovies();
        String result = JSON_CONVERTER.toJson(items);
        //log.debug("result={}", result);
        log.info("getMoviesAsJson/end:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }
}
