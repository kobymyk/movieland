package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Rating;
import db2.onlineshop.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingMapper implements RowMapper<Rating> {
    @Override
    public Rating mapRow(ResultSet resultSet, int i) throws SQLException {
        Rating result = new Rating();
        result.setMovieId(resultSet.getInt("movie_id"));
        result.setRating(resultSet.getDouble("rating"));
        result.setUserId(resultSet.getInt("user_id"));

        return result;
    }
}
