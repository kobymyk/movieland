package db2.onlineshop.dao.main;

import db2.onlineshop.config.JpaMainConfig;
import db2.onlineshop.dao.generic.impl.MovieDaoImpl;
import db2.onlineshop.entity.main.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@ContextConfiguration(classes = JpaMainConfig.class)
public class MovieDaoTest {
    @Autowired
    private MovieDaoImpl movieDao;

    @Test
    public void getById() {
        Movie actual = movieDao.getById(1);
        assertNotNull(actual);
    }
}
