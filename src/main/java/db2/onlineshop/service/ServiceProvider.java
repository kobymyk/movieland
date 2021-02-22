package db2.onlineshop.service;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.fx.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ServiceProvider {
    private List<Object> services;
    private final List<MovieEnricher> enrichers;
    private final List<Child<Movie>> movieChildren;

    //private MovieService movieService;
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;
    private CurrencyService currencyService;

    public ServiceProvider(List<MovieEnricher> enrichers, List<Child<Movie>> movieChildren) {
        this.enrichers = enrichers;
        this.movieChildren = movieChildren;
    }

    public List<MovieEnricher> getEnrichers() {
        return enrichers;
    }

    public List<Child<Movie>> getMovieChildren() {
        return movieChildren;
    }

    public List<Object> getAll() {
        if (services == null) {
            services = Arrays.asList(genreService, countryService, reviewService);
            //services.add(currencyService);
        }

        return services;
    }

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) { this.currencyService = currencyService; }
}
