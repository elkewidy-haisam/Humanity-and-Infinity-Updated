package com.humanity.config;

import java.sql.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.humanity.model.Cart;
import com.humanity.model.ChargeRequest;
import com.humanity.model.Comic;
import com.humanity.model.OrderHistory;
import com.humanity.model.Preview;
import com.humanity.model.User;


@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.humanity" })
public class HumanityConfig {
	
	
	@Bean
	public User user() {
		
		return new User();
		
	}
	
	@Bean
	public Comic comic() {
		
		return new Comic();
		
	}
	
	@Bean
	public Preview preview() {
		
		return new Preview();
		
	}
	
	@Bean
	public Cart cart() {
		
		return new Cart();
		
	}
	
	@Bean
	public OrderHistory orderHistory() {
		
		return new OrderHistory();
		
	}
	
	@Bean
	public ChargeRequest chargeRequest() {
		
		return new ChargeRequest();
		
	}
	
	
	
	/* @Bean
	public DataSource dataOracle() {
		
		BasicDataSource da = new BasicDataSource();
		da.setDriverClassName("oracle.jdbc.OracleDriver");
		da.setUrl("jdbc:oracle:thin:@caliber-1707-java.crwnhws3xbwv.us-east-2.rds.amazonaws.com:1521:ORCL");
		da.setUsername("elkewidyhaisam");
		da.setPassword("Ilovetexas123!");
		
		return da;
		
	} */
	
	@Bean
	public DataSource dataPostgreSql() {
		
		BasicDataSource da = new BasicDataSource();
		
		da.setDriverClassName("org.postgresql.Driver");
		da.setUrl("jdbc:postgresql://localhost:5432/Humanity");
		da.setUsername("postgres");
		da.setPassword("texas123");
		
		return da;
		
		
		
	}
	
	//Hibernate Transaction Manager
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
	   HibernateTransactionManager txManager = new HibernateTransactionManager();
	   txManager.setSessionFactory(sessionFactory);
	 
	   return txManager;
	   
	}
	 
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
	    
		return new PersistenceExceptionTranslationPostProcessor();
	    
	}
	
	//SessionFactory
	/*@Bean
	public AnnotationSessionFactoryBean sessionFactoryOracle(DataSource da) {
		
		AnnotationSessionFactoryBean sfb = new AnnotationSessionFactoryBean();
		sfb.setDataSource(da);
		sfb.setPackagesToScan(new String[] {"com.humanity.model"});
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		props.setProperty("show_sql", "true");
		sfb.setHibernateProperties(props);
		
		return sfb;
	} */
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryPostgreSQL(DataSource da) {
		
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(da);
		sfb.setPackagesToScan(new String[] {"com.humanity"});
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("show_sql", "true");
		props.setProperty("javax.persistence.validation.mode", "none");
		sfb.setHibernateProperties(props);
		
		return sfb;
	}
	

}
