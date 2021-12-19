package db2.onlineshop.dao.generic.impl;

import db2.onlineshop.dao.generic.MovieDao;
import db2.onlineshop.entity.main.Movie;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractGenericDao<Movie> implements MovieDao {

    public MovieDaoImpl() {
        super();
        setEntityClass(Movie.class);
    }

}
