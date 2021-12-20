package db2.onlineshop.dao.main;

import db2.onlineshop.config.JpaMainConfig;
import db2.onlineshop.entity.main.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@ContextConfiguration(classes = JpaMainConfig.class)
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void findAll() {
        List<Movie> actual = movieRepository.findAll();
        assertNotNull(actual);
    }

    @Test
    public void getById() {
        Movie actual = movieRepository.getById(1);
        assertNotNull(actual);
    }

    @Test
    public void findByGenreId() {
        List<Movie> actual = movieRepository.findByGenreId(1);
        assertNotNull(actual);
    }

}
