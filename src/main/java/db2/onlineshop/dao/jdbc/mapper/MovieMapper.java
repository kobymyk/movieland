package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Movie;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie result = new Movie();

        result.setId(resultSet.getInt("id"));
        result.setName(resultSet.getString("name"));
        result.setNameNative(resultSet.getString("name_native"));
        result.setYearOfRelease(resultSet.getInt("release_year"));
        result.setRating(resultSet.getDouble("rating"));
        result.setPrice(resultSet.getDouble("price"));
        result.setPicturePath(resultSet.getString("picture_path"));

        return result;
    }
}
