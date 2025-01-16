package com.brihaspathee.secure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 13, January 2025
 * Time: 1:32 PM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.config
 * To change this template use File | Settings | File and Code Template
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",

            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/h2-console/**",
            "/host",
            "/zeus/jwt/authenticate",
            "/api/v1/zeus/welcome",
            "/swagger-ui.html",
            "/v3/api-docs.yaml",
            // other public endpoints of your API may be appended to this array
            "/public/**"
    };

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) ->
            authorizeRequests
                    .requestMatchers(AUTH_WHITELIST).permitAll()
                    .anyRequest().authenticated());
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        if(!manager.userExists("user1")){
            manager.createUser(User.withUsername("user1")
            .password("{noop}password")
                    .roles("USER")
                    .build());
        }
        if(!manager.userExists("admin")){
            manager
                    .createUser(User.withUsername("admin")
                    .password("{noop}password")
                            .roles("ADMIN")
                            .build());
        }
        return manager;
    }
}
