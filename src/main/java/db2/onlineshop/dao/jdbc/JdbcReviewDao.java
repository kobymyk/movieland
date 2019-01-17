package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.ReviewDao;
import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.model.MovieReview;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcReviewDao extends MovieChildTemplate<Review, MovieReview> implements ReviewDao {

    public JdbcReviewDao() {
        super();
        setModelType(MovieReview.class);
        setEntityType(Review.class);
    }

}
