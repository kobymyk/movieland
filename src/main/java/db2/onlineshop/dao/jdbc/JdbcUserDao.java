package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.UserDao;
import db2.onlineshop.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class JdbcUserDao implements UserDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private SessionFactory sessionFactory;
    private Session session;

    @Override
    public Optional<User> getByEmail(String email) {
        log.debug("getByEmail:email={}", email);
        Optional<User> result;
        try {
            User user = (User) session.byNaturalId(User.class)
                    .using("email", email)
                    .load();
            result = Optional.of(user);
        } catch (ObjectNotFoundException e) {
            log.warn("getByEmail:empty", e);
            result = Optional.empty();
        }
        log.debug("getByEmail:result", result);

        return result;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        try {
            this.session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            this.session = sessionFactory.openSession();
        }
    }


}
