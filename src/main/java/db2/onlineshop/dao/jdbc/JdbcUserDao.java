package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.UserDao;
import db2.onlineshop.dao.jdbc.mapper.UserMapper;
import db2.onlineshop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class JdbcUserDao implements UserDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final UserMapper ROW_MAPPER = new UserMapper();

    private JdbcTemplate jdbcTemplate;

    private String selectByEmail;

    @Override
    public Optional<User> getByEmail(String email) {
        long startTime = System.currentTimeMillis();
        User result = jdbcTemplate.queryForObject(selectByEmail, ROW_MAPPER, email);
        log.debug("getAll:duration={}", System.currentTimeMillis() - startTime);
        // todo: catch
        return Optional.of(result);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    @Qualifier("selectByEmailUser")
    public void setSelectByEmail(String selectByEmail) {
        this.selectByEmail = selectByEmail;
    }


}
