package db2.onlineshop.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({"classpath:application.properties"})
@Profile("mySql")
public class MySqlConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(environment.getProperty("mySql.jdbc.driver"));
        result.setUrl(environment.getProperty("mySql.jdbc.url"));
        result.setUsername(environment.getProperty("mySql.jdbc.user"));
        result.setPassword(environment.getProperty("mySql.jdbc.password"));
        result.setInitialSize(Integer.parseInt(environment.getProperty("mySql.jdbc.poolsize")));

        return result;
    }
}
