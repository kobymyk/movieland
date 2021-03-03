package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.UserDao;
import db2.onlineshop.entity.main.User;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcUserDao extends EntityTemplate<User> implements UserDao {

    public JdbcUserDao() {
        super();
        setEntityClass(User.class);
    }

}
