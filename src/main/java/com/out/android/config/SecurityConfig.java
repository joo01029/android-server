package com.out.android.config;

import com.out.android.config.filter.JwtFilter;
import com.out.android.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final HandlerExceptionResolver handlerExceptionResolver;
	private final JwtProvider jwtProvider;
	//시큐리티 무시
	@Override
	public void configure(WebSecurity webSecurity){
		webSecurity
				.ignoring()
				.antMatchers("/swagger-ui.html");
	}

	@Override
	public void configure(HttpSecurity httpSecurity){
		try {
			httpSecurity
					.httpBasic().disable()
					.cors().configurationSource(corsConfig())
					.and()
					.csrf().disable()
					.authorizeRequests()
					.antMatchers("/auth","/auth/**").permitAll()
					.antMatchers(HttpMethod.POST, "/problem").hasRole("AUTH");


			JwtFilter jwtFilter = new JwtFilter(handlerExceptionResolver, jwtProvider);
			httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public CorsConfigurationSource corsConfig(){
		CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
}
