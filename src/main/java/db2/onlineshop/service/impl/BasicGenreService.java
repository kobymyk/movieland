package db2.onlineshop.service.impl;

import db2.onlineshop.dao.GenreDao;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.compound.GenreItem;
import db2.onlineshop.entity.compound.MovieItems;
import db2.onlineshop.service.GenreService;
import db2.onlineshop.service.MovieChild;
import db2.onlineshop.service.MovieEnricher;
import db2.onlineshop.service.task.MovieEnrichTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Service
public class BasicGenreService implements GenreService, MovieChild {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        List<Genre> result = genreDao.getAll();
        log.info("getAll:result.size={}", result.size());

        return result;
    }

    @Override
    public List<GenreItem> getByMovie(int movieId) {
        List<GenreItem> result = genreDao.getByMovie(movieId);
        log.info("getByMovie:result.size={}", result.size());

        return result;
    }

    @Override
    public void enrich(MovieItems movie) {
        log.debug("enrich");
        int movieId = movie.getId();
        List<GenreItem> genres = getByMovie(movieId);

        movie.setGenres(genres);
    }

    @Override
    public void addReference(MovieItems movie) {
        log.debug("addReference");
        genreDao.addReference(movie);
    }

    @Override
    public void editReference(MovieItems movie) {

    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
}
