package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.User;
import db2.onlineshop.service.security.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User result = new User();

        result.setId(resultSet.getInt("id"));
        result.setNickname(resultSet.getString("name"));
        result.setEmail(resultSet.getString("email"));
        result.setPassword(resultSet.getString("password"));
        result.setRole(Role.valueOf(resultSet.getString("role")));

        return result;
    }
}
