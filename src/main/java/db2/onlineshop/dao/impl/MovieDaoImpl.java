package db2.onlineshop.dao.impl;

import db2.onlineshop.dao.AbstractGenericDao;
import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Ordering;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends AbstractGenericDao<Movie> implements MovieDao {
    private Ordering ordering;

    public MovieDaoImpl() {
        super();
        setEntityClass(Movie.class);
    }

    @Override
    protected void addOrdering(Criteria criteria) {
        if (ordering != null) {
            log.trace("addOrdering:ordering={}", ordering);
            if ("ASC".equals(ordering.getDirection())) {
                criteria.addOrder(Order.asc(ordering.getField()));
            } else {
                criteria.addOrder(Order.desc(ordering.getField()));
            }
        }
    }

    public void setOrdering(Ordering ordering) {
        this.ordering = ordering;
    }
}
