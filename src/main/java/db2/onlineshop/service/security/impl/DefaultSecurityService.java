package db2.onlineshop.service.security.impl;

import db2.onlineshop.entity.User;
import db2.onlineshop.service.UserService;
import db2.onlineshop.service.security.SecurityService;
import db2.onlineshop.service.security.entity.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSecurityService implements SecurityService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserService userService;

    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    @Override
    public Optional<User> getUser(String token) {
        return Optional.empty();
    }

    @Override
    public Session login(User user) {
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
