package fr.clems.cv.CV.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.clems.cv.CV.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Sécurise les méthodes sensibles (post, delete et put) et laisse les autres aux anonymes
		
		// http
		//	.authorizeRequests().antMatchers(HttpMethod.POST, "/ligne/", "/categorie/").hasRole("USER")
		//	.and()
		//	.authorizeRequests().antMatchers(HttpMethod.DELETE, "/ligne/\\d+", "/categorie/\\d+").hasRole("USER")
		//	.and()
		//	.authorizeRequests().antMatchers(HttpMethod.PUT).hasRole("USER")
		//	.and()
		//	.authorizeRequests().antMatchers("/h2/").hasRole("USER")
		//	.and()
		//	.authorizeRequests().antMatchers("/categorie/").hasRole("User")
		//	.and()
		//  .authorizeRequests().antMatchers("/**", "/**/\\d+").permitAll()
		//	.and()
		//	.formLogin()
		//	.and()
		//	.httpBasic();
			
		http
			.authorizeRequests().antMatchers(HttpMethod.POST, "*").hasRole("USER")
			.and()
			.authorizeRequests().antMatchers(HttpMethod.DELETE, "*").hasRole("USER")
			.and()
			.authorizeRequests().anyRequest().permitAll()
			.and()
			.httpBasic();
			
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.
			inMemoryAuthentication().
				withUser("clems").password("clems").roles("USER").and().
				withUser("clemsAdmin").password("clems").roles("ADMIN").and().
				withUser("clemsSup").password("clems").roles("SUPERADMIN");*/
		auth.userDetailsService(this.userServiceImpl).
			passwordEncoder(new BCryptPasswordEncoder());
	}

	/*
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().
				username("clems").
				password("clems").
				roles("USER").
				build();
		return new InMemoryUserDetailsManager(user);
	}*/
}
