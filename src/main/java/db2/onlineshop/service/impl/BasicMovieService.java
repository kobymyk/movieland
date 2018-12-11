package db2.onlineshop.service.impl;

import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.RequestParams;
import db2.onlineshop.service.ServiceProvider;
import db2.onlineshop.service.fx.CurrencyService;
import db2.onlineshop.service.MovieEnricher;
import db2.onlineshop.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BasicMovieService implements MovieService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private CompoundMovieEnricher movieEnricher;
    private CurrencyService currencyService;
    private ServiceProvider serviceProvider;

    private MovieDao movieDao;
    private int randomCount;

    @Override
    public List<Movie> getAll(RequestParams param) {
        List<Movie> result = movieDao.getAll(param);
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

    @Override
    public Movie getById(int id, RequestParams param) {
        Movie result = movieDao.getById(id);
        log.trace("getByGenre:result={}", result);
        // enrich
        enrich(result);
        String currency = param.getCurrency();
        if (currency != null) {
            double price = currencyService.exchange(result.getPrice(), currency);
            log.debug("getById:price={}", price);
            result.setPrice(price);
        }

        return result;
    }

    @Override
    public int add(Movie movie) {
        log.trace("add:movie={}", movie);
        int result = movieDao.add(movie);
        // todo: add children

        return result;
    }

    private void enrich(Movie result) {
        List<Object> enrichers = serviceProvider.filter(MovieEnricher.class);
        for (Object enricher : enrichers) {
            movieEnricher.add((MovieEnricher) enricher);
        }
        movieEnricher.enrich(result);
        log.trace("enrich:result={}", result);
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Value("${dao.movie.randomCount:3}")
    public void setRandomCount(int randomCount) {
        this.randomCount = randomCount;
    }

    @Autowired
    public void setMovieEnricher(CompoundMovieEnricher movieEnricher) {
        this.movieEnricher = movieEnricher;
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
