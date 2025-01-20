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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

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

    // test comment
    // test comment 3
    // test comment 4
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

    /**
     * test
     * @param http
     * @return
     * @throws Exception
     */
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

    /**
     * Test again
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        if(!manager.userExists("balaji")){
            manager.createUser(User.withUsername("balaji")
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
