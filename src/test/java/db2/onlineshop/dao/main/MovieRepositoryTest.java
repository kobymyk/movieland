package db2.onlineshop.dao.main;

import db2.onlineshop.config.JpaMainConfig;
import db2.onlineshop.entity.main.Movie;
import db2.onlineshop.entity.main.MovieGenre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@ContextConfiguration(classes = JpaMainConfig.class)
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    @Transactional(readOnly = true)
    public void findAll() {
        List<Movie> actual = movieRepository.findAll();
        assertNotNull(actual);
    }

    @Test
    @Transactional(readOnly = true)
    public void getById() {
        Movie movie = movieRepository.getById(1);
        assertNotNull(movie);
        Set<MovieGenre> movieGenres = movie.getMovieGenres();
        assertNotNull(movieGenres);
    }

    @Test
    @Transactional(readOnly = true)
    public void findByGenreId() {
        List<Movie> actual = movieRepository.findByGenreId(1);
        assertNotNull(actual);
    }

}
