package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.ReviewDao;
import db2.onlineshop.entity.Review;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcReviewDao extends EntityTemplate<Review> implements ReviewDao {

    public JdbcReviewDao() {
        super();
        setEntityClass(Review.class);
    }

}
