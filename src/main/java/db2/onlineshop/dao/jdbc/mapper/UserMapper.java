package db2.onlineshop.dao.jdbc.mapper;

import db2.onlineshop.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User result = new User();

        int id = resultSet.getInt("id");
        result.setId(id);
        String nickname = resultSet.getString("name");
        result.setNickname(nickname);
        String email = resultSet.getString("email");
        result.setEmail(email);
        String password = resultSet.getString("password");
        result.setPassword(password);

        return result;
    }
}
