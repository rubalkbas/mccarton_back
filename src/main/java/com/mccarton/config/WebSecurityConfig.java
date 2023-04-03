package com.mccarton.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
	
	private final ClienteDetailServiceImpl clienteDetailsService;
	private final UsuarioDetailsServiceImpl usuarioDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	SecurityFilterChain filterChainUser(HttpSecurity http, @Qualifier("clienteAuthManager") AuthenticationManager clienteAuthManager, @Qualifier("usuarioAuthManager") AuthenticationManager authManagerUsuario) throws Exception {
		
		JWTAuthenticationFilterUsuario userJwtAuthenticationFilter = new JWTAuthenticationFilterUsuario();
		userJwtAuthenticationFilter.setAuthenticationManager(authManagerUsuario);
		userJwtAuthenticationFilter.setFilterProcessesUrl("/loginUsuario");
		
		JWTAuthenticationFilter clientJwtAuthenticationFilter = new JWTAuthenticationFilter();
	    clientJwtAuthenticationFilter.setAuthenticationManager(clienteAuthManager);
	    clientJwtAuthenticationFilter.setFilterProcessesUrl("/loginCliente");
		
		return http
				.csrf().disable()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(clientJwtAuthenticationFilter)
				.addFilter(userJwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
	
	@Bean(name = "clienteAuthManager")
	@Primary
	AuthenticationManager authManagerClient(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(clienteDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	@Bean(name = "usuarioAuthManager")
	AuthenticationManager authManagerUsuario(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(usuarioDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	SecurityFilterChain filterChainClient(HttpSecurity http, AuthenticationManager autManager) throws Exception {
//		
//				JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
//				jwtAuthenticationFilter.setAuthenticationManager(autManager);
//				jwtAuthenticationFilter.setFilterProcessesUrl("/loginCliente");
//		
//				return http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic().and()
//						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//						.addFilter(jwtAuthenticationFilter)
//						.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class).build();
//			}
	
//	@Bean
//	UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(User.withUsername("admin")
//				.password(passwordEncoder().encode("admin"))
//				.roles()
//				.build());
//		return manager;
//	}
	
	

	
}




















