package com.connect.brick.config;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import com.connect.brick.handler.UserLoginFailureHandler;
import com.connect.brick.handler.UserLoginSuccessHandler;


@ComponentScan("com.connect.brick.handler")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	//@Autowired
	//private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.headers()
			.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
		
		http.headers().frameOptions().disable();
		
		http.cors().and().csrf().disable()
			//.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
			//.and()
			.authorizeRequests()
				//허용 URL - 리소스
				.antMatchers("/lib/**", "/assets/**", "/image_storage/**", "/resources/**", "/favicon.ico").permitAll()
				//access 관련 
				.antMatchers("/access/**", "/loginError", "/error/**").permitAll()
				//비회원 접근
				.antMatchers("/", "/main", "/main/user", 
						"/service/policy/**",
						"/service/details/**", "/service/qna/**", "/service/estimate/**",
						"/service/consreview/**",
						"/service/guide/**", "/view/**", "/service/ajax/**",
						"/image/**", "/service/testboard/**","/service/form/**").permitAll()
				//비회원 접근 -> main redirect
				.antMatchers("/index").permitAll()
				//.antMatchers("/service/company/**").hasAuthority("ROLE_MANAGER")
				.antMatchers("/master/**", "/dropzone/**", "/ajax/**").hasAuthority("ROLE_MANAGER")
				//4. ROLE_ADMIN
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.anyRequest()
				.denyAll();
		
		http.formLogin()
				.loginPage(RequestMappingConstants._ACCESS_LOGIN)
				.loginProcessingUrl("/login")
				.usernameParameter("id")
				.passwordParameter("password")
				.successHandler(new UserLoginSuccessHandler("/master/material/list")) // 로그인 성공 핸들러
				.failureHandler(new UserLoginFailureHandler()) // 로그인 실패 핸들러
				.permitAll()
				.and()
				.sessionManagement()
				.maximumSessions(-1)
				.sessionRegistry(sessionRegistry())
				.expiredUrl(RequestMappingConstants._ACCESS_LOGIN)
				.maxSessionsPreventsLogin(true);
		
		http.logout()
			.permitAll()
			.and()
			.exceptionHandling()
			.accessDeniedPage(RequestMappingConstants._ERROR_PATH_CODE + HttpServletResponse.SC_FORBIDDEN); // 권한이 없을경우 해당 url로 이동
		
		//http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
	/*
	 * @Bean public CorsConfigurationSource corsConfigurationSource() {
	 * 
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * 
	 * configuration.addAllowedOrigin("http://localhost:3000");
	 * configuration.addAllowedHeader("*"); configuration.addAllowedMethod("*");
	 * configuration.setAllowCredentials(true);
	 * 
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration);
	 * 
	 * return source; }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    return new UserLoginSuccessHandler("/");
	}
	
	@Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();

    }
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	} 
	
	/*
	 * @Bean public JwtAuthenticationFilter jwtAuthenticationFilter() { return new
	 * JwtAuthenticationFilter(); }
	 */

}
