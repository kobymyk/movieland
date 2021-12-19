package db2.onlineshop.dao.generic.impl;

import db2.onlineshop.dao.generic.MovieCountryDao;
import db2.onlineshop.entity.main.MovieCountry;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieCountryDao extends AbstractGenericDao<MovieCountry> implements MovieCountryDao {

    public JdbcMovieCountryDao() {
        super();
        setEntityClass(MovieCountry.class);
    }
}
