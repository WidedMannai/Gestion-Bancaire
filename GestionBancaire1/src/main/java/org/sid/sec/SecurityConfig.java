package org.sid.sec;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration //c est une classe de configuration
@EnableWebSecurity // pr activer la securité web
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*
	
	@Autowired                             // ahaya f commentaire l 5edma 
	private DataSource dataSource;
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
		
	}
	@Autowired
	private UserDetailsService UserDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		System.err.println("hello");
		auth.userDetailsService(UserDetailsService);
	
	*/
/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
	http.authorizeRequests().anyRequest().authenticated();
}
	*/
	

 /* @Autowired
	private DataSource datasource;*/
	
  @Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// on va definir la maniere avec laquelle on va chercher les users
	    // les users sont stockés en mémoire
	    
		auth.inMemoryAuthentication()
		 .withUser("user").password("{noop}user").roles( "USER")
		.and()
		.withUser("admin").password("{noop}123").roles("ADMIN","USER");
		auth.inMemoryAuthentication()
       .withUser("client").password("{noop}1129").roles("CLIENT");//.and().withUser("admin").password("{noop}password").roles("ADMIN");
	
	/*  auth.jdbcAuthentication().dataSource(dataSource)
	   .usersByUsernameQuery("select username as principal , password as credentials , active from user where username=?") //spring connect sur la Bd pr savoir si l user existe
	    .authoritiesByUsernameQuery("select username as principal ,role as role from role where username=?")
	    .rolePrefix("Role_")
	    .passwordEncoder(new Md5PasswordEncoder());
	
	}*/
  }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/operation","/client","/saveNouveauCompte","/saveOperation","/activerDesactiver","/consulterClient").access("hasRole('USER') or hasRole('ADMIN')")
		.antMatchers("/opgenerale").access("hasRole('ADMIN')")                   
		.and()
		.formLogin()
		.loginPage("/login")
		;
		http.exceptionHandling().accessDeniedPage("/403");
	}


}
	




