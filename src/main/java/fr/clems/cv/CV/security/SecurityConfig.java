package fr.clems.cv.CV.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.clems.cv.CV.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private Environment environnement;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Sécurise les méthodes sensibles (post, delete et put) et laisse les autres aux anonymes
			
		http
		    .anonymous()
		    .and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/*").authenticated()
			.antMatchers(HttpMethod.DELETE, "/*").authenticated()
			.antMatchers(HttpMethod.PUT, "/*").authenticated()
			.antMatchers(HttpMethod.PATCH, "/*").authenticated()
			.antMatchers("/user/").authenticated()
			.anyRequest().permitAll()
			.and()
			.httpBasic();
		
		http.cors();
		 http
	     .sessionManagement()
	     .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		boolean dev = false;
		for(String profile : this.environnement.getActiveProfiles()) {
			if (profile.equals("dev")) {
				dev = true;
			}
		}
		
		if (dev) {
			http.headers().frameOptions().disable(); // Pour l'administration de h2
		}
		
		http.csrf().disable();
	}
	
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userServiceImpl).
			passwordEncoder(new BCryptPasswordEncoder());
	}
}
