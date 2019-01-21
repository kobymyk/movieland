package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieCountryDao;
import db2.onlineshop.entity.MovieCountry;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieCountryDao extends EntityTemplate<MovieCountry> implements MovieCountryDao {

    public JdbcMovieCountryDao() {
        super();
        setEntityClass(MovieCountry.class);
    }
}
