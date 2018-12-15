package db2.onlineshop.service.impl;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieEnricher;
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
    public void enrich(Movie result) {
        log.debug("enrich");
        ExecutorService executor = Executors.newSingleThreadExecutor();

        List<Callable<Movie>> tasks = new ArrayList<>();
        for (MovieEnricher enricher : enrichers) {
            Callable<Movie> task = new MovieEnrichTask();
            ((MovieEnrichTask) task).setEnricher(enricher);
            ((MovieEnrichTask) task).setMovie(result);

            tasks.add(task);
            //enricher.enrich(result);
        }
        try {
            List<Future<Movie>> futures = executor.invokeAll(tasks, 5, TimeUnit.SECONDS);
            if (futures.stream().noneMatch(Future::isCancelled)) {
                log.info("enrich:results.size={}", futures.size());
            }
            for (Future<Movie> future : futures) {
                Movie movie = future.get();
                // todo: return List<Object>
                if (movie.getCountries() != null) {
                    result.setCountries(movie.getCountries());
                }
                if (movie.getGenres() != null) {
                    result.setGenres(movie.getGenres());
                }
                if (movie.getReviews() != null) {
                    result.setReviews(movie.getReviews());
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("enrich", e);
            throw new RuntimeException(e);
        }
    }
}
