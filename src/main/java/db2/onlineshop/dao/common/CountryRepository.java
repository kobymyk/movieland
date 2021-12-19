package db2.onlineshop.dao.common;

import db2.onlineshop.entity.common.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
}
