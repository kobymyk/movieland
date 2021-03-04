package db2.onlineshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({"classpath:application.properties"})
@Profile("oracle")
public class OracleConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(environment.getProperty("oracle.jdbc.driver"));
        result.setUrl(environment.getProperty("oracle.jdbc.url"));
        result.setUsername(environment.getProperty("oracle.jdbc.user"));
        result.setPassword(environment.getProperty("oracle.jdbc.password"));
        result.setInitialSize(Integer.parseInt(environment.getProperty("oracle.jdbc.poolsize")));

        return result;
    }
}
