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
	private static final String PROP_DB_DRIVER = "db.driver";
	private static final String PROP_DB_URL = "db.url";
	private static final String PROP_DB_USER = "db.user";
	private static final String PROP_DB_PASSWORD = "db.password";
	private static final String PROP_DB_ENTITY_PACKAGE = "db.entity.package";
	
	private static final String PROP_DB_INITIAL_SIZE = "db.initialSize";
	private static final String PROP_DB_MIN_IDLE = "db.minIdle";
	private static final String PROP_DB_MAX_IDLE = "db.maxIdle";
	private static final String PROP_DB_TIME_BETWEEN_EVICTION_RUNS_MILLIS = "db.timeBetweenEvictionRunsMillis";
	private static final String PROP_DB_MIN_EVICTABLE_IDLE_TIME_MILLIS = "db.minEvictableIdleTimeMillis";
	private static final String PROP_DB_TEST_ON_BORROW = "db.testOnBorrow";
	private static final String PROP_DB_VALIDATION_QUERY = "db.validationQuery";
	
	@Resource
	private Environment env;
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(env.getRequiredProperty(PROP_DB_DRIVER));
		basicDataSource.setUrl(env.getRequiredProperty(PROP_DB_URL));
		basicDataSource.setUsername(env.getRequiredProperty(PROP_DB_USER));
		basicDataSource.setPassword(env.getRequiredProperty(PROP_DB_PASSWORD));
		
		basicDataSource.setInitialSize(Integer.valueOf(env.getRequiredProperty(PROP_DB_INITIAL_SIZE)));
		basicDataSource.setMinIdle(Integer.valueOf(env.getRequiredProperty(PROP_DB_MIN_IDLE)));
		basicDataSource.setMaxIdle(Integer.valueOf(env.getRequiredProperty(PROP_DB_MAX_IDLE)));
		basicDataSource.setTimeBetweenEvictionRunsMillis(
				Long.valueOf(env.getRequiredProperty(PROP_DB_TIME_BETWEEN_EVICTION_RUNS_MILLIS)));
		basicDataSource.setMinEvictableIdleTimeMillis(
				Long.valueOf(env.getRequiredProperty(PROP_DB_MIN_EVICTABLE_IDLE_TIME_MILLIS)));
		basicDataSource.setTestOnBorrow(Boolean.valueOf(env.getRequiredProperty(PROP_DB_TEST_ON_BORROW)));
		basicDataSource.setValidationQuery(env.getRequiredProperty(PROP_DB_VALIDATION_QUERY));
		
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
		entityManager.setPackagesToScan(env.getRequiredProperty(PROP_DB_ENTITY_PACKAGE));
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
