package db2.onlineshop.service.impl;

import db2.onlineshop.dao.MovieCompoundDao;
import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.entity.MovieCompound;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Ordering;
import db2.onlineshop.service.ServiceProvider;
import db2.onlineshop.service.fx.CurrencyService;
import db2.onlineshop.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BasicMovieService implements MovieService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private ServiceProvider serviceProvider;
    // todo: enrich
    private CurrencyService currencyService;

    private MovieDao movieDao;
    private MovieCompoundDao movieCompoundDao;

    @Override
    public List<Movie> getAll(Ordering ordering) {
        movieDao.setOrdering(ordering);
        List<Movie> result = movieDao.getAll();
        log.info("getAll:result.size={}", result.size());

        return result;
    }

    @Override
    public List<Movie> getByGenre(int genreId) {
        List<Movie> result = movieDao.listByKey("genreId", genreId);
        log.info("getByGenre:result.size={}", result.size());

        return result;
    }

    @Override
    public MovieCompound getById(int id, String currency) {
        Movie movie = movieDao.getById(id);
        log.trace("getById:movie={}", movie);

        CompoundMovieEnricher enricher = serviceProvider.getCompoundMovieEnricher();
        MovieCompound result = new MovieCompound();
        result.setMovie(movie);
        enricher.enrich(result);
        log.trace("getById:result={}", result);

        if (currency != null) {
            double price = currencyService.exchange(movie.getPrice(), currency);
            log.debug("getById:price={}", price);
            result.getMovie().setPrice(price);
        }

        return result;
    }

    @Override
    @Transactional
    public void add(MovieCompound movie) {
        log.trace("add:movie={}", movie);
        movieCompoundDao.add(movie);
    }

    @Override
    @Transactional
    public void edit(MovieCompound movie) {
        log.trace("edit:movie={}", movie);
        //movieDao.edit((Movie) movie);

        CompoundMovieChild children = serviceProvider.getCompoundMovieChild();
        children.editReference(movie);
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Autowired
    public void setMovieCompoundDao(MovieCompoundDao movieCompoundDao) {
        this.movieCompoundDao = movieCompoundDao;
    }

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Autowired
    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
