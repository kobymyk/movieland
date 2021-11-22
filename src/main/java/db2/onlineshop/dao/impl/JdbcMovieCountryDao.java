package db2.onlineshop.dao.impl;

import db2.onlineshop.dao.MovieCountryDao;
import db2.onlineshop.entity.MovieCountry;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieCountryDao extends AbstractGenericDao<MovieCountry> implements MovieCountryDao {

    public JdbcMovieCountryDao() {
        super();
        setEntityClass(MovieCountry.class);
    }
}
