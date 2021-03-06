package db2.onlineshop.service.impl;

import db2.onlineshop.dao.GenreDao;
import db2.onlineshop.dao.MovieGenreDao;
import db2.onlineshop.entity.*;
import db2.onlineshop.service.GenreService;
import db2.onlineshop.service.Child;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicGenreService implements GenreService, Child<Movie> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GenreDao genreDao;
    private final MovieGenreDao movieGenreDao;

    @Autowired
    public BasicGenreService(GenreDao genreDao, MovieGenreDao movieGenreDao) {
        this.genreDao = genreDao;
        this.movieGenreDao = movieGenreDao;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> result = genreDao.getAll();
        log.info("getAll:result.size={}", result.size());

        return result;
    }

    @Override
    public void enrich(Movie movie) {
        int movieId = movie.getId();
        log.debug("enrich:movieId={}", movieId);
        List<MovieGenre> movieGenres = movieGenreDao.listByKey("movieId", movieId);
        List<Genre> genres = new ArrayList<>(movieGenres.size());
        for (MovieGenre movieGenre : movieGenres) {
            genres.add(movieGenre.getGenre());
        }
        movie.setGenres(genres);
    }

    @Override
    public void merge(Movie movie, Movie result) {
        result.setGenres(movie.getGenres());
    }

    @Override
    public void addReference(Movie movie) {
        int movieId = movie.getId();
        log.debug("addReference:movieId={}", movieId);

        for (Genre genre : movie.getGenres()) {
            MovieGenre movieGenre = new MovieGenre();
            movieGenre.setMovieId(movieId);
            movieGenre.setGenre(genre);
            movieGenreDao.add(movieGenre);
        }
    }

}
