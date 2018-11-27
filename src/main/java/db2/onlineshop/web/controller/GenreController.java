package db2.onlineshop.web.controller;

import db2.onlineshop.entity.Genre;
import db2.onlineshop.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/genre")
public class GenreController {
    private GenreService genreService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public List<Genre> getAll() {
        long startTime = System.currentTimeMillis();
        List<Genre> result = genreService.getAll();
        log.info("getAll:duration={}", System.currentTimeMillis() - startTime);

        return result;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
}
