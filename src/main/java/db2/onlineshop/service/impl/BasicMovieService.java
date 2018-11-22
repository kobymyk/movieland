package db2.onlineshop.service.impl;

import db2.onlineshop.dao.jdbc.MovieDb;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class BasicMovieService implements MovieService {
    @Autowired
    private MovieDb movieDb;

    @Override
    public List<Movie> getMovies() {
        return movieDb.selectAll();
    }

    public List<Movie> getRandomMovies(int maxCount) {
        List<Movie> result = new ArrayList<>();
        int maxId = movieDb.getMaxKey();
        int count = 0;
        Movie movie = null;
        while (count < maxCount) {
            movie = movieDb.fetchRow(new Random().nextInt(maxId));
            if (movie != null) {
                count++;
                result.add(movie);
            }
        }
        return result;
    }

    @Override
    public int updateMovie(Map params) {
        Movie Movie = fromParams(params);

        return movieDb.updateRow(Movie);
    }

    @Override
    public int addMovie(Map params) {
        Movie Movie = fromParams(params);

        return movieDb.insertRow(Movie);
    }

    @Override
    public Movie getMovie(int id) {
        return movieDb.fetchRow(id);
    }

    @Override
    public int removeMovie(int id) {
        return movieDb.deleteRow(id);
    }

    private Movie fromParams(Map params) {
        Movie result = new Movie();
        result.setId(Integer.parseInt((String) params.get("id")));
        result.setName((String) params.get("name"));
        result.setPrice(Double.parseDouble((String) params.get("price")));

        return result;
    }
}
