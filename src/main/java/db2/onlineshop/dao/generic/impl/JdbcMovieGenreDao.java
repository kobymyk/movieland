package db2.onlineshop.dao.generic.impl;

import db2.onlineshop.dao.generic.MovieGenreDao;
import db2.onlineshop.entity.main.MovieGenre;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieGenreDao extends AbstractGenericDao<MovieGenre> implements MovieGenreDao {

    public JdbcMovieGenreDao() {
        super();
        setEntityClass(MovieGenre.class);
    }
}
