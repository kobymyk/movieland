package db2.onlineshop.service;

import db2.onlineshop.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByEmail(String email);
}
