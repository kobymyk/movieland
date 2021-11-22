package db2.onlineshop.dao;

import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Ordering;

public interface MovieDao extends GenericDao<Movie> {
    void setOrdering(Ordering ordering);
}
