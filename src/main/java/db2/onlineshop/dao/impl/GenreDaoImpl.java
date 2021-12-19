package db2.onlineshop.dao.impl;

import db2.onlineshop.dao.main.AbstractGenericDao;
import db2.onlineshop.entity.main.Genre;
import org.springframework.stereotype.Component;


@Component
public class GenreDaoImpl extends AbstractGenericDao<Genre> {

    public GenreDaoImpl() {
        super();
        setEntityClass(Genre.class);
    }

}
