package db2.onlineshop.service.impl;

import db2.onlineshop.dao.generic.MovieDao;
import db2.onlineshop.entity.main.Movie;
import db2.onlineshop.service.MovieEnricher;
import db2.onlineshop.service.ServiceFactory;
import db2.onlineshop.service.enricher.MovieEnrichExecutor;
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

    private final ServiceFactory serviceFactory;
    // todo: enrich
    private final CurrencyService currencyService;
    private final MovieDao movieDao;

    @Autowired
    public BasicMovieService(ServiceFactory serviceFactory, CurrencyService currencyService, MovieDao movieDao) {
        this.serviceFactory = serviceFactory;
        this.currencyService = currencyService;
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> getAll() {
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
    public Movie getById(int id, String currency) {
        Movie result = movieDao.getById(id);
        log.trace("getById:movie={}", result);

        List<MovieEnricher> enrichers = serviceFactory.getEnrichers();
        MovieEnrichExecutor enrichExecutor = new MovieEnrichExecutor(enrichers);
        enrichExecutor.enrich(result);
        log.trace("getById:result={}", result);

        if (currency != null) {
            double price = currencyService.exchange(result.getPrice(), currency);
            log.debug("getById:price={}", price);
            result.setPrice(price);
        }

        return result;
    }

    @Override
    @Transactional
    public void add(Movie movie) {
        log.trace("add:movie={}", movie);
        movieDao.add(movie);

        serviceFactory.getMovieChildren().forEach(child -> child.addReference(movie));
    }

    @Override
    @Transactional
    public void edit(Movie movie) {
        log.trace("edit:movie={}", movie);
        movieDao.edit(movie);
    }

}
