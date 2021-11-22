package db2.onlineshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "db2.onlineshop.dao.common",
        entityManagerFactoryRef = "commonEntityManager",
        transactionManagerRef = "commonTransactionManager"
)
public class PersistenceCommonConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean commonEntityManager() {
        LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
        result.setDataSource(commonDataSource());
        result.setPackagesToScan("db2.onlineshop.entity.common");

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
    public BasicDataSource commonDataSource() {
        BasicDataSource result = new BasicDataSource();
        //todo: "jdbc.driverClassName"
        result.setDriverClassName(env.getProperty("mySql.jdbc.driver"));
        result.setUrl(env.getProperty("mySql.jdbc.url"));
        result.setUsername(env.getProperty("mySql.jdbc.user"));
        result.setPassword(env.getProperty("mySql.jdbc.password"));
        result.setInitialSize(Integer.parseInt(env.getProperty("mySql.jdbc.poolsize")));

        return result;
    }

    @Bean
    public PlatformTransactionManager commonTransactionManager() {
        JpaTransactionManager result = new JpaTransactionManager();
        result.setEntityManagerFactory(commonEntityManager().getObject());
        return result;
    }

}
