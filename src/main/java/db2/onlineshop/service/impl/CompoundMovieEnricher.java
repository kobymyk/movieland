package db2.onlineshop.service.impl;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.CountryService;
import db2.onlineshop.service.GenreService;
import db2.onlineshop.service.MovieEnricher;
import db2.onlineshop.service.ReviewService;
import db2.onlineshop.service.task.MovieEnrichParam;
import db2.onlineshop.service.task.MovieEnrichTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class CompoundMovieEnricher implements MovieEnricher {
    private final Logger log = LoggerFactory.getLogger(getClass());

    List<MovieEnricher> enrichers = new ArrayList<>();

    public void add(MovieEnricher enricher) {
        log.debug("add");
        enrichers.add(enricher);
    }

    @Override
    public void enrich(Movie movie) {
        log.debug("enrich");
        ExecutorService executor = Executors.newSingleThreadExecutor();

        List<Callable<MovieEnrichParam>> tasks = new ArrayList<>();
        for (MovieEnricher enricher : enrichers) {
            Callable<MovieEnrichParam> task = new MovieEnrichTask();

            MovieEnrichParam param = new MovieEnrichParam(enricher);
            param.setMovie(movie);

            ((MovieEnrichTask) task).setParam(param);

            tasks.add(task);
            //enricher.enrich(result);
        }
        try {
            List<Future<MovieEnrichParam>> futures = executor.invokeAll(tasks, 5, TimeUnit.SECONDS);
            if (futures.stream().noneMatch(Future::isCancelled)) {
                log.info("enrich:results.size={}", futures.size());
            }
            for (Future<MovieEnrichParam> future : futures) {
                MovieEnrichParam futureParam = future.get();
                MovieEnricher enricher = futureParam.getEnricher();
                Movie futureMovie = futureParam.getMovie();

                if (enricher instanceof CountryService) {
                    movie.setCountries(futureMovie.getCountries());
                }
                if (enricher instanceof GenreService) {
                    movie.setGenres(futureMovie.getGenres());
                }
                if (enricher instanceof ReviewService) {
                    movie.setReviews(futureMovie.getReviews());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("enrich", e);
            throw new RuntimeException(e);
        }
    }
}
