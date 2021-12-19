package db2.onlineshop.dao.impl;

import db2.onlineshop.dao.AbstractGenericDao;
import db2.onlineshop.dao.MovieGenreDao;
import db2.onlineshop.entity.MovieGenre;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieGenreDao extends AbstractGenericDao<MovieGenre> implements MovieGenreDao {

    public JdbcMovieGenreDao() {
        super();
        setEntityClass(MovieGenre.class);
    }
}
