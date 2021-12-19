package db2.onlineshop.service.enricher;

import db2.onlineshop.entity.main.Movie;
import db2.onlineshop.service.MovieEnricher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class MovieEnrichExecutor {
    private final Logger log = LoggerFactory.getLogger(getClass());

    List<MovieEnricher> enrichers;

    public MovieEnrichExecutor(List<MovieEnricher> enrichers) {
        this.enrichers = enrichers;
    }

    public void add(MovieEnricher enricher) {
        log.debug("add");
        enrichers.add(enricher);
    }

    public void enrich(Movie movie) {
        log.debug("enrich");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<MovieEnrichParam>> tasks = new ArrayList<>(enrichers.size());
        // fill tasks
        for (MovieEnricher enricher : enrichers) {
            MovieEnrichParam param = new MovieEnrichParam(enricher, movie);
            Callable<MovieEnrichParam> task = new MovieEnrichTask(param);

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
                enricher.merge(result.getMovie(), movie);
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
