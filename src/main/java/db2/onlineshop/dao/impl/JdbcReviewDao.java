package db2.onlineshop.dao.impl;

import db2.onlineshop.dao.main.AbstractGenericDao;
import db2.onlineshop.dao.main.ReviewDao;
import db2.onlineshop.entity.main.Review;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcReviewDao extends AbstractGenericDao<Review> implements ReviewDao {

    public JdbcReviewDao() {
        super();
        setEntityClass(Review.class);
    }

}
