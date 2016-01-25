package mos.edu.server.fancinema.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("mos.edu.server.fancinema.repository")
@PropertySource("classpath:db.properties")
@ComponentScan("mos.edu.server.fancinema")
public class DatabaseConfig {
	
	@Resource
	private Environment env;
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
		basicDataSource.setUrl(env.getRequiredProperty("db.url"));
		basicDataSource.setUsername(env.getRequiredProperty("db.user"));
		basicDataSource.setPassword(env.getRequiredProperty("db.password"));
		
		basicDataSource.setInitialSize(Integer.valueOf(env.getRequiredProperty("db.initialSize")));
		basicDataSource.setMinIdle(Integer.valueOf(env.getRequiredProperty("db.minIdle")));
		basicDataSource.setMaxIdle(Integer.valueOf(env.getRequiredProperty("db.maxIdle")));
		basicDataSource.setTimeBetweenEvictionRunsMillis(
				Long.valueOf(env.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
		basicDataSource.setMinEvictableIdleTimeMillis(
				Long.valueOf(env.getRequiredProperty("db.minEvictableIdleTimeMillis")));
		basicDataSource.setTestOnBorrow(Boolean.valueOf(env.getRequiredProperty("db.testOnBorrow")));
		basicDataSource.setValidationQuery(env.getRequiredProperty("db.validationQuery"));
		return basicDataSource;
	}
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
			properties.load(is);
		} catch (IOException e) {
			throw new IllegalArgumentException("Can`t find 'hibernate.properties' in classpath!", e);
		}
		return properties;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager =
				new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(getDataSource());
		entityManager.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
		entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManager.setJpaProperties(getHibernateProperties());
		return entityManager;
	}
	
	@Bean
	public PlatformTransactionManager getTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
		return transactionManager;
	}
}
