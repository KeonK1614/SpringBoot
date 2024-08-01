package com.study.springboot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.study.springboot.oauth2.CustomOAuth2UserService;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
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
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.permitAll()
		);
		
		http.headers((headers) -> headers
				.frameOptions(frameOptions -> frameOptions.disable())
				);
		http.oauth2Login((oauth) -> oauth
				.userInfoEndpoint(endPoint -> endPoint
						.userService(customOAuth2UserService)
						)
				);
		
		return http.build();			
	}
	
	@Bean
	protected UserDetailsService users() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("1234"))
				.roles("USER")
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("1234"))
				.roles("USER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
