package com.toyshop.StoreToys_API.configuration;

import com.toyshop.StoreToys_API.filter.JWTAuthFilter;
import com.toyshop.StoreToys_API.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@Configuration
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SecurityConfig {

    private final AccountService aSer;
    private final JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers("/registry/**", "/registry", "/news").permitAll()
                        .requestMatchers(HttpMethod.GET, "/category", "/brand", "/product").permitAll()
                        .requestMatchers(HttpMethod.GET, "/category/**", "/brand/**", "/product/**", "/news/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/category/**", "/brand/**", "/product/**")
                            .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/category/**", "/brand/**", "/product/**")
                            .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/category/**", "/brand/**", "/product/**")
                            .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/user/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/news/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(aSer.userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
