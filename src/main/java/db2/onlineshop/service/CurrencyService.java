package db2.onlineshop.service;

import db2.onlineshop.entity.Currency;

import java.util.List;

public interface CurrencyService {

    Currency get(String code);

    double exchange(double value, String code);

    List<Currency> getAll();

}
