package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieGenreDao;
import db2.onlineshop.entity.MovieGenre;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieGenreDao extends EntityTemplate<MovieGenre> implements MovieGenreDao {

    public JdbcMovieGenreDao() {
        super();
        setEntityClass(MovieGenre.class);
    }
}
