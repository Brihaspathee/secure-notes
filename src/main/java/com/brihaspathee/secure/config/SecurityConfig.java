package com.brihaspathee.secure.config;

import com.brihaspathee.secure.constants.AppRole;
import com.brihaspathee.secure.domain.entity.Role;
import com.brihaspathee.secure.domain.entity.User;
import com.brihaspathee.secure.domain.repository.RoleRepository;
import com.brihaspathee.secure.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.time.LocalDate;

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
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
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

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            Role userRole = roleRepository.findByRoleName(AppRole.ROLE_USER)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_USER)));
            Role adminsRole = roleRepository.findByRoleName(AppRole.ROLE_ADMIN)
                    .orElseGet(() -> roleRepository.save(new Role(AppRole.ROLE_ADMIN)));
            if(!userRepository.existsByUserName("user1")){
                User user1 = User.builder()
                        .userName("user1")
                        .email("user1@example.com")
                        .password("{noop}password1")
                        .accountNonLocked(false)
                        .accountNonExpired(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .credentialsExpiryDate(LocalDate.now().plusYears(3))
                        .accountExpiryDate(LocalDate.now().plusYears(3))
                        .isTwoFactorEnabled(false)
                        .signUpMethod("email")
                        .role(userRole)
                        .build();
                userRepository.save(user1);
            }
            if(!userRepository.existsByUserName("admin1")){
                User user1 = User.builder()
                        .userName("admin1")
                        .email("admin1@example.com")
                        .password("{noop}adminpass1")
                        .accountNonLocked(false)
                        .accountNonExpired(true)
                        .credentialsNonExpired(true)
                        .enabled(true)
                        .credentialsExpiryDate(LocalDate.now().plusYears(3))
                        .accountExpiryDate(LocalDate.now().plusYears(3))
                        .isTwoFactorEnabled(false)
                        .signUpMethod("email")
                        .role(userRole)
                        .build();
                userRepository.save(user1);
            }
        };
    }
}
