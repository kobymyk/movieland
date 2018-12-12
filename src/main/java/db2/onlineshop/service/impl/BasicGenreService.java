package db2.onlineshop.service.impl;

import db2.onlineshop.dao.GenreDao;
import db2.onlineshop.entity.Genre;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.GenreService;
import db2.onlineshop.service.MovieEnricher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicGenreService implements GenreService, MovieEnricher {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        List<Genre> result = genreDao.getAll();
        log.info("getAll:result.size={}", result.size());
        log.trace("getAll:result={}", result);

        return result;
    }

    @Override
    public List<Genre> getByMovie(int movieId) {
        List<Genre> result = genreDao.getByMovie(movieId);
        log.info("getByMovie:result.size={}", result.size());
        log.trace("getByMovie:result={}", result);

        return result;
    }

    @Override
    public void enrich(Movie movie) {
        log.debug("enrich");
        int movieId = movie.getId();
        List<Genre> genres = getByMovie(movieId);

        movie.setGenres(genres);
    }

    @Override
    public void addReference(Movie movie) {
        log.debug("addReference");
        genreDao.addReference(movie);
    }

    @Override
    public void editReference(Movie movie) {

    }

    @Autowired
    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }
}
