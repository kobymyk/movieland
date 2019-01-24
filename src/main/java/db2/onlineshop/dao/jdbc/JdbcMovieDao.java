package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.MovieDao;
import db2.onlineshop.entity.Movie;
import db2.onlineshop.entity.Ordering;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcMovieDao extends EntityTemplate<Movie> implements MovieDao {
    private Ordering ordering;

    public JdbcMovieDao() {
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
