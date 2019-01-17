package db2.onlineshop.service.task;

import db2.onlineshop.entity.compound.MovieCompound;
import db2.onlineshop.service.MovieEnricher;

public class MovieEnrichParam {
    private MovieEnricher enricher;
    private MovieCompound movie;

    public MovieEnrichParam(MovieEnricher enricher, MovieCompound movie) {
        this.enricher = enricher;
        this.movie = copyMovie(movie);
    }

    private MovieCompound copyMovie(MovieCompound movie) {
        MovieCompound result = new MovieCompound();
        result.setId(movie.getId());

        return result;
    }

    public MovieEnricher getEnricher() {
        return enricher;
    }

    public MovieCompound getMovie() {
        return movie;
    }
}
