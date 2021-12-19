package db2.onlineshop.config;

import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

public abstract class AbstractJpaConfig {
    protected LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource,
                                                                             String entityPackagePath,
                                                                             Properties properties) {
        LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
        result.setDataSource(dataSource);
        result.setPackagesToScan(entityPackagePath);
        result.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        result.setJpaProperties(properties);

        return result;
    }

    protected Properties getProperties(Environment env) {
        Properties result = new Properties();
        result.put("hibernate.hbm2ddl.auto", env.getProperty("jpa.hibernate.hbm2ddl.auto"));
        result.put("hibernate.dialect", env.getProperty("jpa.hibernate.dialect"));
        //todo: setProperty("show_sql", "true");

        return result;
    }
}
