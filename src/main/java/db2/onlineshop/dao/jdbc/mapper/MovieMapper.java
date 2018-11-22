package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.Movie;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;

public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) {
        Movie result = new Movie();
        try {
            result.setId(resultSet.getInt("id"));
            result.setName(resultSet.getString("name"));
            result.setNameNative(resultSet.getString("name_native"));
            result.setDescription(resultSet.getString("description"));
            result.setYearOfRelease(resultSet.getInt("release_year"));
            result.setRating(resultSet.getDouble("rating"));
            result.setPrice(resultSet.getDouble("price"));
            result.setPicturePath(resultSet.getString("picture_path"));

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
