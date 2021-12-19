package db2.onlineshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static db2.onlineshop.config.Constants.jpaCommon.ENTITY_PACKAGE_PATH;
import static db2.onlineshop.config.Constants.jpaCommon.REPO_PACKAGE_PATH;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = REPO_PACKAGE_PATH,
        entityManagerFactoryRef = "entityManagerCommon",
        transactionManagerRef = "transactionManagerCommon"
)
public class JpaCommonConfig extends AbstractJpaConfig{
    @Autowired
    private Environment env;

    @Bean
    public PlatformTransactionManager transactionManagerCommon(@Qualifier("entityManagerCommon") EntityManagerFactory emf) {
        return super.getTransactionManager(emf);
    }

    @Bean(name = "entityManagerCommon")
    public LocalContainerEntityManagerFactoryBean entityManagerCommon(@Qualifier("dataSourceCommon") DataSource dataSource) {
        return super.getEntityManagerFactory(dataSource, ENTITY_PACKAGE_PATH, getProperties(env));
    }

    @Override
    protected Properties getProperties(Environment env) {
        Properties result = super.getProperties(env);
        //result.put()
        return result;
    }

    @Bean(name = "dataSourceCommon")
    public DataSource dataSourceCommon() {
        BasicDataSource result = new BasicDataSource();
        //todo: "jdbc.driverClassName"
        result.setDriverClassName(env.getProperty("mySql.jdbc.driver"));
        result.setUrl(env.getProperty("mySql.jdbc.url"));
        result.setUsername(env.getProperty("mySql.jdbc.user"));
        result.setPassword(env.getProperty("mySql.jdbc.password"));
        result.setInitialSize(Integer.parseInt(env.getProperty("mySql.jdbc.poolsize")));

        return result;
    }

}
