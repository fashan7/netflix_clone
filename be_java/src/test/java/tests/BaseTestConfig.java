package tests;

import org.hibernate.dialect.PostgreSQL81Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class BaseTestConfig {

    @Bean("db.mainDatasource")
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/netflix_clone", "postgres", "Qwerty#123");
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(@Autowired DataSource dataSource){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("epita/datamodel");

        Properties properties = new Properties();
        properties.setProperty("hibernate.properties", PostgreSQL81Dialect.class.getName());
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.show_sql","true");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }


}
