package db2.onlineshop.dao.impl;

import db2.onlineshop.entity.common.Country;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public class CountryDaoImpl extends AbstractGenericDao<Country> {

    public CountryDaoImpl() {
        super();
        setEntityClass(Country.class);
    }

}
