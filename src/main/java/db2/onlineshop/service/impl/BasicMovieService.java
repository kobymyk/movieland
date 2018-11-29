package db2.onlineshop.service.impl;

import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BasicMovieService implements MovieService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private MovieDao movieDao;
    private int randomCount;

    @Override
    public List<Movie> getAll() {
        List<Movie> result = movieDao.getAll();
        log.trace("getAll:result={}", result);
        log.info("getAll:result.size={}", result.size());

        return result;
    }

    @Override
    public List<Movie> getRandom() {
        log.info("getRandom:randomCount={}", randomCount);
        List<Movie> result = movieDao.getRandom(randomCount);
        log.trace("getRandom:result={}", result);

        return result;
    }

    @Override
    public List<Movie> getByGenre(int genreId) {
        List<Movie> result = movieDao.getByGenre(genreId);
        log.trace("getByGenre:result={}", result);
        log.info("getByGenre:result.size={}", result.size());

        return result;
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Autowired
    public void setRandomCount(@Value("${movie.randomCount:3}") int randomCount) {
        this.randomCount = randomCount;
    }
}
