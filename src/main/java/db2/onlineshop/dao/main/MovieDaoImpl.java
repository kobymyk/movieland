package db2.onlineshop.dao.main;

import db2.onlineshop.dao.main.AbstractGenericDao;
import db2.onlineshop.dao.main.MovieDao;
import db2.onlineshop.entity.main.Movie;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractGenericDao<Movie> implements MovieDao {

    public MovieDaoImpl() {
        super();
        setEntityClass(Movie.class);
    }

}
