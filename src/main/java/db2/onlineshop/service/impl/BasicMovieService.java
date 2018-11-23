package db2.onlineshop.service.impl;

import db2.onlineshop.dao.jdbc.MovieDb;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BasicMovieService implements MovieService {
    private MovieDb movieDb;

    @Autowired
    public void setMovieDb(MovieDb movieDb) {
        this.movieDb = movieDb;
    }

    @Override
    public List<Movie> getAll() {
        return movieDb.getAll();
    }

    @Override
    public List<Movie> getRandom(int size) {
        return movieDb.getRandom(size);
    }
}
