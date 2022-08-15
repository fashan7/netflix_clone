package epita;

import epita.repository.MovieRepository;
import epita.repository.MovieUserRepository;
import epita.repository.RoleRepository;
import epita.repository.UserRepository;
import epita.service.MovieJPADAO;
import epita.service.MovieUserJPADAO;
import epita.service.RoleJPADAO;
import epita.service.UserJPADAO;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
public class NetflixBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(NetflixBackendApplication.class);
	}

	@Bean
	public DataSource dataSource() {
		return new DriverManagerDataSource("jdbc:postgresql://localhost:5432/netflix_clone", "postgres", "Qwerty#123");
	}
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean(@Autowired DataSource dataSource){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("epita/datamodel");
		Properties properties = new Properties();
		properties.setProperty("hibernate.properties", PostgreSQL10Dialect.class.getName());
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql","true");
		sessionFactory.setHibernateProperties(properties);
		return sessionFactory;
	}


	@Bean
	public UserJPADAO getUserDAO(SessionFactory sf){
		return new UserJPADAO(sf);
	}
	@Bean
	public RoleJPADAO getRoleDAO(SessionFactory sf){
		return new RoleJPADAO(sf);
	}
	@Bean
	public MovieJPADAO getMovieDAO(SessionFactory sf){
		return new MovieJPADAO(sf);
	}
	@Bean
	public MovieUserJPADAO getMovieUserDAO(SessionFactory sf){
		return new MovieUserJPADAO(sf);
	}



	@Bean
	public UserRepository getUserRepository(SessionFactory sf,
											UserJPADAO userDao
	){
		return new UserRepository(sf, userDao);
	}

	@Bean
	public RoleRepository getRoleRepository(SessionFactory sf,
											RoleJPADAO roleDAO
	){
		return new RoleRepository(sf, roleDAO);
	}

	@Bean
	public MovieRepository getMovieRepository(SessionFactory sf,
											 MovieJPADAO movieDAO
	){
		return new MovieRepository(sf, movieDAO);
	}

	@Bean
	public MovieUserRepository getMovieUserRepository(SessionFactory sf,
													  MovieUserJPADAO movieUserDAO
	){
		return new MovieUserRepository(sf, movieUserDAO);
	}
}

