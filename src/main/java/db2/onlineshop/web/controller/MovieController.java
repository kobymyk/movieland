package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import db2.onlineshop.web.templater.PageGenerator;

import db2.onlineshop.web.utils.ParamConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    private static final String KEY = "id";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    private final PageGenerator PAGE_GENERATOR = PageGenerator.instance();

    @RequestMapping(path = "/movies", method = RequestMethod.GET)
    @ResponseBody
    public String getMovies() {
        log.info("MovieController::start");
        List<Movie> items = movieService.getMovies();
        Map<String, Object> data = ParamConverter.fromList(items, "movies");
        String result = PAGE_GENERATOR.getPage("movies", data);
        log.info("MovieController::end");

        return result;
    }
}
