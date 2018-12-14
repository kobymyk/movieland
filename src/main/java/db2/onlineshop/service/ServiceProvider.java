package db2.onlineshop.service;

import db2.onlineshop.service.fx.CurrencyService;
import db2.onlineshop.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProvider {
    private MovieService movieService;
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;
    private CurrencyService currencyService;

    public CompoundMovieEnricher getCompoundMovieEnricher() {
        CompoundMovieEnricher result = new CompoundMovieEnricher();

        result.add((MovieEnricher) genreService);
        result.add((MovieEnricher) countryService);
        result.add((MovieEnricher) reviewService);
        result.add((MovieEnricher) currencyService);

        return result;
    }

    public CompoundMovieChild getCompoundMovieChild() {
        CompoundMovieChild result = new CompoundMovieChild();

        result.add((MovieChild) genreService);
        result.add((MovieChild) countryService);
        //result.add((MovieChild) reviewService);

        return result;
    }

    public List<Object> getAll() {
        List<Object> result = new ArrayList<>();

        result.add(movieService);
        result.add(genreService);
        result.add(countryService);
        result.add(reviewService);

        return result;
    }

    public List<Object> filter(Class<?> anyInterface) {
        List<Object> result = new ArrayList<>();
        List<Object> services = getAll();
        for (Object service : services) {
            if (anyInterface.isAssignableFrom(service.getClass())) {
                result.add(service);
            }
        }

        return result;
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
