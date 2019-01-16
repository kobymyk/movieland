package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Review;
import db2.onlineshop.entity.User;
import db2.onlineshop.entity.compound.ReviewItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<ReviewItem> {
    @Override
    public ReviewItem mapRow(ResultSet resultSet, int i) throws SQLException {
        ReviewItem result = new ReviewItem();
        result.setId(resultSet.getInt("id"));
        result.setText(resultSet.getString("review_text"));

        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setNickname(resultSet.getString("user_name"));
        result.setUser(user);

        return result;
    }
}
