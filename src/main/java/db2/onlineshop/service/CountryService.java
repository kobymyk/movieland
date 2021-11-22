package db2.onlineshop.service;

import db2.onlineshop.entity.common.Country;

import java.util.List;

public interface CountryService extends MovieEnricher {
    List<Country> getAll();
}
