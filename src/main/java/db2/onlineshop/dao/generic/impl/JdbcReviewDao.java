package db2.onlineshop.dao.generic.impl;

import db2.onlineshop.dao.generic.ReviewDao;
import db2.onlineshop.entity.main.Review;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcReviewDao extends AbstractGenericDao<Review> implements ReviewDao {

    public JdbcReviewDao() {
        super();
        setEntityClass(Review.class);
    }

}
