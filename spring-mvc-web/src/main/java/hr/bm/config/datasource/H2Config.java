package hr.bm.config.datasource;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

import hr.bm.annotation.DevelopmentProfile;
import liquibase.integration.spring.SpringLiquibase;

@PropertySources({ @PropertySource("classpath:/properties/config/datasource.properties") })
@Configuration
public class H2Config {

	@Autowired
	Environment env;

	@DevelopmentProfile
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(env.getProperty("datasource.url")); // Runtime
																// injection
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		return dataSource;
	}

	@Bean
	public SpringLiquibase springLiquibase() {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource());
		springLiquibase.setChangeLog("classpath:config/liquibase/db-changelog.xml");
		return springLiquibase;
	}

}
