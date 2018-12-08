package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        int reviewId = resultSet.getInt("id");
        String reviewText = resultSet.getString("review_text");
        // todo: User mapper, but "user_id"
        User user = new User();
        int userId = resultSet.getInt("user_id");
        user.setId(userId);
        String userNickname = resultSet.getString("user_name");
        user.setNickname(userNickname);

        Review result = new Review(reviewId, reviewText, user);

        return result;
    }
}
