package hr.bm.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CommonSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("bmlikota").password("12345").roles("USER").and()
		.withUser("admin").password("12345").roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // TODO pogledati sto je csrf
		.authorizeRequests()
		.antMatchers("/home")
//		.authenticated().antMatchers(HttpMethod.POST, "/add-spittle")
		.authenticated().anyRequest().permitAll();
//		.and()
//		.formLogin().and()
//		.httpBasic();
	}
}
