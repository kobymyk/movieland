package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String nickname = resultSet.getString("name");
        User result = new User(id, nickname);

        return result;
    }
}
