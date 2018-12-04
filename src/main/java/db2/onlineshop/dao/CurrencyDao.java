package db2.onlineshop.dao;

import db2.onlineshop.entity.Currency;

import java.util.List;

public interface CurrencyDao {
    Currency get(String code);

    List<Currency> getAll();
}
