package db2.onlineshop.service.fx.impl;

import db2.onlineshop.service.fx.CurrencyDao;
import db2.onlineshop.service.fx.entity.Currency;
import db2.onlineshop.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicCurrencyService implements CurrencyService {
    private CurrencyDao currencyDao;

    @Override
    public Currency get(String code) {
        return currencyDao.get(code);
    }

    @Override
    public double exchange(double value, String code) {
        Currency currency = get(code);
        double rate = currency.getRate();
        // todo: division by zero
        double result = value / rate;

        return result;
    }

    @Override
    public List<Currency> getAll() {
        return currencyDao.getAll();
    }

    @Autowired
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }
}
