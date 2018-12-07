package db2.onlineshop.service.fx.impl;

import db2.onlineshop.service.fx.CurrencyDao;
import db2.onlineshop.service.fx.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasicCurrencyService implements CurrencyService {
    private CurrencyDao currencyDao;

    @Override
    public double exchange(double value, String code) {
        double rate = currencyDao.get(code);
        // todo: default currency
        double result = value / rate;

        return result;
    }

    @Autowired
    public void setCurrencyDao(CurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
    }
}
