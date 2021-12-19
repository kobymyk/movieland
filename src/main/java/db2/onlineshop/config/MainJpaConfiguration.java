package db2.onlineshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static db2.onlineshop.config.Constants.jpaMain.*;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = REPO_PACKAGE_PATH,
        entityManagerFactoryRef = "mainEntityManager",
        transactionManagerRef = "mainTransactionManager"
)
public class MainJpaConfiguration extends AbstractJpaConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mainEntityManager() {
        return super.getEntityManagerFactory(mainDataSource(), ENTITY_PACKAGE_PATH, getProperties(env));
    }

    @Override
    protected Properties getProperties(Environment env) {
        Properties result = super.getProperties(env);
        //result.put()
        return result;
    }

    @Bean
    @Primary
    public DataSource mainDataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(env.getProperty("oracle.jdbc.driver"));
        result.setUrl(env.getProperty("oracle.jdbc.url"));
        result.setUsername(env.getProperty("oracle.jdbc.user"));
        result.setPassword(env.getProperty("oracle.jdbc.password"));
        result.setInitialSize(Integer.parseInt(env.getProperty("oracle.jdbc.poolsize")));

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
