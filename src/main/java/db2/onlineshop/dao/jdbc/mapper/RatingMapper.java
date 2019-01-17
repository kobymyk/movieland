package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.MovieRating;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingMapper implements RowMapper<MovieRating> {
    @Override
    public MovieRating mapRow(ResultSet resultSet, int i) throws SQLException {
        MovieRating result = new MovieRating();
        result.setMovieId(resultSet.getInt("movie_id"));
        result.setRating(resultSet.getDouble("rating"));
        result.setUserId(resultSet.getInt("user_id"));

        return result;
    }
}
