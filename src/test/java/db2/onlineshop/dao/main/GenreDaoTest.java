package db2.onlineshop.dao.main;

import db2.onlineshop.config.MainJpaConfiguration;
import db2.onlineshop.dao.GenericDao;
import db2.onlineshop.entity.main.Genre;
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
@ContextConfiguration(classes = MainJpaConfiguration.class)
public class GenreDaoTest {
    @Autowired
    private GenericDao<Genre> genreDao;

    @Test
    public void getById() {
        Genre actual = genreDao.getById(1);
        assertNotNull(actual);
    }
}
