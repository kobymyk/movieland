package db2.onlineshop.service.impl;

import db2.onlineshop.dao.UserDao;
import db2.onlineshop.entity.User;
import db2.onlineshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasicUserService implements UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private UserDao userDao;

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
