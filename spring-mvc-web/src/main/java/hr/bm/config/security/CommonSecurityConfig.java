package hr.bm.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import hr.bm.utils.CommonPasswordEncoder;

@Configuration
@EnableWebSecurity
public class CommonSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.inMemoryAuthentication()
	// .withUser("bmlikota").password("12345").roles("USER").and()
	// .withUser("admin").password("12345").roles("USER", "ADMIN");
	// }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username = ?")
				.passwordEncoder(new CommonPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // TODO pogledati sto je csrf; thymeleaf
								// ${_csrf.token}
				.authorizeRequests().antMatchers("/home")
				// .authenticated().antMatchers(HttpMethod.POST, "/add-spittle")
				.authenticated().antMatchers("/spittr/**").authenticated().anyRequest().permitAll().and().formLogin()
				.and().httpBasic();
	}
}
