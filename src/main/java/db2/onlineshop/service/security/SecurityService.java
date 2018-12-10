package db2.onlineshop.service.security;

import db2.onlineshop.entity.User;
import db2.onlineshop.service.security.entity.Session;

import java.util.Optional;

public interface SecurityService {
    Session login(String email, String password);

    void logout(String token);

    Optional<User> getUser(String token);

}
