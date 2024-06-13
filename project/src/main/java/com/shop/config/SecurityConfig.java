package com.shop.config;

import com.shop.security.CustomAuthenticationSuccessHandler;
import com.shop.service.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/logon").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/email").permitAll()
                        .requestMatchers("/send-verification-code").permitAll()
                        .requestMatchers("/forgot-password").permitAll()
                        .requestMatchers("/logout").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/cart").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/checkout").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/list-order").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/order").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/user-information").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/change-password").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/user-update").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/product/detail").hasAnyAuthority("ADMIN","USER")
                        .requestMatchers("/cart/add-product").hasAnyAuthority("ADMIN","USER")
                        .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/logon").loginProcessingUrl("/logon")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureUrl("/logon?error=true"))
                .logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"))
        ;
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**", "/fe/**", "/assets/**","/uploads/**");
    }
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
}
