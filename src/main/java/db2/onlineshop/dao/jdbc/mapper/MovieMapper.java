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
        result.setPrice(resultSet.getDouble("price"));

        return result;
    }
}
