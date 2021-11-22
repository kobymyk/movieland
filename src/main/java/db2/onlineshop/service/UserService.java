package db2.onlineshop.service;

import db2.onlineshop.entity.main.User;

public interface UserService {
    User findByEmail(String email);
    void resister(User user);
    String authenticate(String email, String password);
}
