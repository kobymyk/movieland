package db2.onlineshop.service.impl;

import db2.onlineshop.dao.jdbc.MovieDb;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BasicMovieService implements MovieService {
    @Autowired
    private MovieDb movieDb;

    @Override
    public List<Movie> getMovies() {
        return movieDb.selectAll();
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
    public Movie getMovie(String key) {
        return (Movie) movieDb.fetchRow(key);
    }

    @Override
    public int removeMovie(String key) {
        return movieDb.deleteRow(key);
    }

    private Movie fromParams(Map params) {
        Movie result = new Movie();
        result.setId(Integer.parseInt((String) params.get("id")));
        result.setName((String) params.get("name"));
        result.setPrice(Double.parseDouble((String) params.get("price")));

        return result;
    }
}
