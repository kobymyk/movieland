package db2.onlineshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static db2.onlineshop.config.Constants.jpaMain.*;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = REPO_PACKAGE_PATH,
        entityManagerFactoryRef = "entityManagerFactoryMain",
        transactionManagerRef = "transactionManagerMain"
)
public class JpaMainConfig extends AbstractJpaConfig {
    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "transactionManagerMain")
    public PlatformTransactionManager transactionManagerMain(@Qualifier("entityManagerFactoryMain") EntityManagerFactory emf) {
        return super.getTransactionManager(emf);
    }

    @Bean(name = "entityManagerFactoryMain")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerMain(@Qualifier("dataSourceMain") DataSource dataSource) {
        return super.getEntityManagerFactory(dataSource, ENTITY_PACKAGE_PATH, getProperties(env));
    }

    @Override
    protected Properties getProperties(Environment env) {
        Properties result = super.getProperties(env);
        //result.put()
        return result;
    }

    @Bean(name = "dataSourceMain")
    @Primary
    public DataSource dataSourceMain() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(env.getProperty("oracle.jdbc.driver"));
        result.setUrl(env.getProperty("oracle.jdbc.url"));
        result.setUsername(env.getProperty("oracle.jdbc.user"));
        result.setPassword(env.getProperty("oracle.jdbc.password"));
        result.setInitialSize(Integer.parseInt(env.getProperty("oracle.jdbc.poolsize")));

        return result;
    }

}
