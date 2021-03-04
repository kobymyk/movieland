package db2.onlineshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "db2.onlineshop.dao.main",
        entityManagerFactoryRef = "mainEntityManager",
        transactionManagerRef = "mainTransactionManager"
)
public class PersistenceMainConfiguration {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mainEntityManager() {
        LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
        result.setDataSource(mainDataSource());
        //todo: ("db2.onlineshop.entity.main")
        result.setPackagesToScan(new String[] {"db2.onlineshop.entity.main"});

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        result.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        //todo: env.getProperty("hibernate.dialect")
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        //todo: setProperty("show_sql", "true");
        result.setJpaPropertyMap(properties);

        return result;
    }

    @Bean
    @Primary
    public BasicDataSource mainDataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(env.getProperty("jdbc.driver"));
        result.setUrl(env.getProperty("jdbc.url"));
        result.setUsername(env.getProperty("jdbc.user"));
        result.setPassword(env.getProperty("jdbc.password"));
        result.setInitialSize(Integer.parseInt(env.getProperty("jdbc.poolsize")));

        return result;
    }

    @Primary
    @Bean
    public PlatformTransactionManager mainTransactionManager() {
        JpaTransactionManager result = new JpaTransactionManager();
        result.setEntityManagerFactory(mainEntityManager().getObject());
        return result;
    }

}
