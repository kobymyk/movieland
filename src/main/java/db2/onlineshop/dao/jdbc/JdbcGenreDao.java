package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.GenreDao;
import db2.onlineshop.entity.Genre;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcGenreDao extends EntityTemplate<Genre> implements GenreDao {

    public JdbcGenreDao() {
        super();
        setEntityClass(Genre.class);
    }

}
