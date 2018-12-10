package db2.onlineshop.dao;

import db2.onlineshop.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> getByEmail(String email);
}
