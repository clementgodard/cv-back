package fr.clems.cv.CV.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Sécurise les méthodes sensibles (post, delete et put) et laisse les autres aux anonymes
		http
			.authorizeRequests().antMatchers(HttpMethod.POST).hasRole("USER")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole("USER")
			.and()
			.authorizeRequests().antMatchers("/h2/**").hasRole("USER")
			.and()
			.authorizeRequests().antMatchers("/**", "/**/\\d+").permitAll()
			.and()
			.formLogin()
			.and()
			.httpBasic();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	
	/*
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			inMemoryAuthentication().
				withUser("clems").password("clems").roles("USER").and().
				withUser("clemsAdmin").password("clems").roles("ADMIN").and().
				withUser("clemsSup").password("clems").roles("SUPERADMIN");
	}
	*/

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().
				username("clems").
				password("clems").
				roles("USER").
				build();
		return new InMemoryUserDetailsManager(user);
	}
}
