package db2.onlineshop.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class MovieController {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @RequestMapping(path = "/v1/movie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getJsonMovies() throws JsonProcessingException {
        log.info("getMoviesAsJson/start");
        long startTime = System.currentTimeMillis();
        List<Movie> items = movieService.getMovies();
        String result = JSON_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(items);
        log.info("getMoviesAsJson/end:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }
}
