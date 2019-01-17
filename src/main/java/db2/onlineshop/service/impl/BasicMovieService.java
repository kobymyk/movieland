package db2.onlineshop.service.impl;

import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.entity.compound.MovieCompound;
import db2.onlineshop.entity.model.Movie;
import db2.onlineshop.entity.RequestParams;
import db2.onlineshop.service.ServiceProvider;
import db2.onlineshop.service.fx.CurrencyService;
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

    private ServiceProvider serviceProvider;
    // todo: enrich
    private CurrencyService currencyService;

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
    public MovieCompound getById(int id, RequestParams param) {
        Movie movie = movieDao.getById(id);
        log.trace("getById:movie={}", movie);

        CompoundMovieEnricher enricher = serviceProvider.getCompoundMovieEnricher();
        MovieCompound result = new MovieCompound(movie);
        enricher.enrich(result);
        log.trace("getById:result={}", result);

        String currency = param.getCurrency();
        if (currency != null) {
            double price = currencyService.exchange(result.getPrice(), currency);
            log.debug("getById:price={}", price);
            result.setPrice(price);
        }

        return result;
    }

    @Override
    public int add(MovieCompound movie) {
        log.trace("add:movie={}", movie);
        int result = movieDao.add((Movie) movie);
        movie.setId(result);

        CompoundMovieChild children = serviceProvider.getCompoundMovieChild();
        children.addReference(movie);

        return result;
    }

    @Override
    public void edit(MovieCompound movie) {
        log.trace("edit:movie={}", movie);
        movieDao.edit((Movie) movie);

        CompoundMovieChild children = serviceProvider.getCompoundMovieChild();
        children.editReference(movie);
    }

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Value("${movie.randomCount:3}")
    public void setRandomCount(int randomCount) {
        this.randomCount = randomCount;
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
