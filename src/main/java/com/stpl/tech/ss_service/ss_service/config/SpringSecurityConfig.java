package com.stpl.tech.ss_service.ss_service.config;

import com.stpl.tech.ss_service.ss_service.config.jwtConfig.JWTFilter;
import com.stpl.tech.ss_service.ss_service.exception.CustomAccessDeniedHandler;
import com.stpl.tech.ss_service.ss_service.resource.SSResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
    private static final String NO_AUTHORIZATION_REQUIRED_PATH = SSResourceUtil.BASE_PATH + "/" + SSResourceUtil.AUTH_ROOT_CONTEXT + "/**";

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private JWTFilter jwtFilter;

    @Bean("BCryptPasswordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("securityFilterChain")
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(configurationSource()))
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        addingAllHttpRequestMatchers(security);

        security
                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }


    private void addingAllHttpRequestMatchers(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(request -> request
                .requestMatchers(NO_AUTHORIZATION_REQUIRED_PATH).permitAll()
                .anyRequest().authenticated());
    }

    public CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(List.of("*"));
        corsConfig.setAllowedMethods(List.of("*"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
        return urlBasedCorsConfigurationSource;
    }

}
