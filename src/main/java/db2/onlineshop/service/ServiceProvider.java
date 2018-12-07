package db2.onlineshop.service;

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

    public List<Object> getAll() {
        List<Object> result = new ArrayList<>();

        result.add(movieService);
        result.add(genreService);
        result.add(countryService);
        result.add(reviewService);
        //services.add();

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
}
