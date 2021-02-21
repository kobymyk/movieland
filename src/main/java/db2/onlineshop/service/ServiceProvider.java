package db2.onlineshop.service;

import db2.onlineshop.service.fx.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProvider {
    private List<Object> services;
    private List<MovieEnricher> enrichers;

    private MovieService movieService;
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;
    private CurrencyService currencyService;

    public ServiceProvider(List<MovieEnricher> enrichers) {
        this.enrichers = enrichers;
    }

    public List<Object> getAll() {
        if (services == null) {
            services = new ArrayList<>();

            services.add(movieService);
            services.add(genreService);
            services.add(countryService);
            services.add(reviewService);
            //services.add(currencyService);
        }

        return services;
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
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
