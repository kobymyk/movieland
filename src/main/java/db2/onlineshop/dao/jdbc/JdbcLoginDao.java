package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.UserLoginDao;
import db2.onlineshop.entity.UserLogin;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcLoginDao extends EntityTemplate<UserLogin> implements UserLoginDao {

    public JdbcLoginDao() {
        super();
        setEntityClass(UserLogin.class);
    }

}
