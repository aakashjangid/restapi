package com.iotapi.restapp.configuration;

import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.iotapi.restapp.model.BaseEntity;

@Configuration
@ComponentScan(basePackages = "com.iotapi")
public class DataSourceConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		try {
			em.setDataSource(dataSource());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.setPackagesToScan(new String[] { "com.iotapi.restapp.model" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}

	@Primary
	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		try {
			sessionFactory.setDataSource(dataSource());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sessionFactory.setPackagesToScan(new String[] { "com.iotapi.restapp.model" });

		// add your POJO classes to this method seperated by comma
		// sessionFactory.setAnnotatedClasses(User.class,Customer.class,Page.class,Video.class);
		sessionFactory.setAnnotatedClasses(BaseEntity.class);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;

	}

	private Properties hibernateProperties() {

		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "false");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;

	}

	@Bean
	public DriverManagerDataSource dataSource() throws SQLException {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		/*
		 * dataSource.setUrl(environment.getProperty("jdbc.url"));
		 * dataSource.setUsername(environment.getProperty("jdbc.username"));
		 * dataSource.setPassword(environment.getProperty("jdbc.password"));
		 * dataSource.setDriverClassName(environment.getProperty("jdbc.drivername"));
		 */

		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/

		dataSource.setUrl("jdbc:mysql://" + System.getProperty("RDS_HOSTNAME") + ":" + System.getProperty("RDS_PORT")
				+ "/" + System.getProperty("RDS_DB_NAME")+"?useSSL=false");
		System.out.println("--------------------------"+System.getProperty("RDS_USERNAME"));
		System.out.println("--------------------------"+System.getProperty("RDS_PASSWORD"));
		System.out.println("--------------------------"+System.getProperty("RDS_HOSTNAME"));
		System.out.println("--------------------------"+System.getProperty("RDS_PORT"));
		System.out.println("--------------------------"+System.getProperty("RDS_DB_NAME"));
		System.out.println("+++++"+System.getProperty("RDS_USERNAME"));
		dataSource.setUsername(System.getProperty("RDS_USERNAME"));
		dataSource.setPassword(System.getProperty("RDS_PASSWORD"));
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		System.out.println(dataSource.getConnection());
		return dataSource;

	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory s) {

		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;

	}

}
