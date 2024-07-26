package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
			.cors((cors) -> cors.disable())
			.authorizeHttpRequests(request -> request
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
				.requestMatchers("/guest/**").permitAll()
				.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			);
		http.formLogin((formLogin) -> formLogin
				.loginPage("/loginForm")
				.loginProcessingUrl("/j_spring_security_check")
//				.failureUrl("/loginForm?error")
				.failureHandler(authenticationFailureHandler)
				.usernameParameter("j_username")
				.passwordParameter("j_password")
				.permitAll()
		);
		
		http.logout((logout) ->logout
				.logoutUrl("/logout/")
				.logoutSuccessUrl("/")
				.permitAll()
		);
		return http.build();			
	}
	
//	@Bean
//	protected UserDetailsService users() {
//		UserDetails user = User.builder()
//				.username("user")
//				.password(passwordEncoder().encode("1234"))
//				.roles("USER")
//				.build();
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password(passwordEncoder().encode("1234"))
//				.roles("USER", "ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user, admin);
//	}
//	
//	public PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT name AS userName, password, enabled"
								+ " FROM user_list WHERE name = ?")
			.authoritiesByUsernameQuery("SELECT name AS userName, authority "
								+ " FROM user_list WHERE name = ?")
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
