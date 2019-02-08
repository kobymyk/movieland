package db2.onlineshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@ComponentScan({"db2.onlineshop.dao"})
public class PersistenceConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                new String[] {"db2.onlineshop.entity"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(environment.getProperty("jdbc.driver"));
        result.setUrl(environment.getProperty("jdbc.url"));
        result.setUsername(environment.getProperty("jdbc.user"));
        result.setPassword(environment.getProperty("jdbc.password"));
        result.setInitialSize(Integer.parseInt(environment.getProperty("jdbc.poolsize")));

        return result;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager result = new HibernateTransactionManager();
        result.setSessionFactory(sessionFactory);

        return result;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("show_sql", "true");
                setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
            }
        };
    }

}
