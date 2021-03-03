package db2.onlineshop.dao.jdbc;

import db2.onlineshop.dao.CountryDao;
import db2.onlineshop.entity.common.Country;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcCountryDao extends EntityTemplate<Country> implements CountryDao {

    public JdbcCountryDao() {
        super();
        setEntityClass(Country.class);
    }

}
