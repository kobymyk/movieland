package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review result = new Review();
        result.setId(resultSet.getInt("id"));
        result.setText(resultSet.getString("review_text"));

        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setNickname(resultSet.getString("user_name"));
        result.setUser(user);

        return result;
    }
}
