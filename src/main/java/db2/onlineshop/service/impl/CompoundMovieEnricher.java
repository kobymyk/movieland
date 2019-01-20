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
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<MovieEnrichParam>> tasks = new ArrayList<>(enrichers.size());
        // fill tasks
        for (MovieEnricher enricher : enrichers) {
            Callable<MovieEnrichParam> task = new MovieEnrichTask();

            MovieEnrichParam param = new MovieEnrichParam(enricher, movie);
            ((MovieEnrichTask) task).setParam(param);

            tasks.add(task);
        }
        log.info("enrich:tasks.size={}", tasks.size());

        try {
            List<Future<MovieEnrichParam>> futures = executor.invokeAll(tasks);
            for (Future<MovieEnrichParam> future : futures) {
                if (!future.isDone()) {
                    log.debug("enrich::running");
                }
                MovieEnrichParam result = null;
                try {
                    // get actual result
                    result = future.get(5, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    log.error("enrich::timeout", e);
                    future.cancel(true);
                }
                MovieEnricher enricher = result.getEnricher();
                Movie futureMovie = result.getMovie();
                // merge
                if (enricher instanceof CountryService) {
                    movie.setCountries(futureMovie.getCountries());
                }
                if (enricher instanceof GenreService) {
                    movie.setMovieGenres(futureMovie.getMovieGenres());
                }
                if (enricher instanceof ReviewService) {
                    movie.setReviews(futureMovie.getReviews());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("enrich::interrupt", e);
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }
}
