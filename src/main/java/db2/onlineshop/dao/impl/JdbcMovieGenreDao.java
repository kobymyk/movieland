package db2.onlineshop.dao.impl;

import db2.onlineshop.dao.main.AbstractGenericDao;
import db2.onlineshop.dao.main.MovieGenreDao;
import db2.onlineshop.entity.main.MovieGenre;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieGenreDao extends AbstractGenericDao<MovieGenre> implements MovieGenreDao {

    public JdbcMovieGenreDao() {
        super();
        setEntityClass(MovieGenre.class);
    }
}
