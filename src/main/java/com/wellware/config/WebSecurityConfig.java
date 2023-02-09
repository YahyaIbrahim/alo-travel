package com.wellware.config;

import com.wellware.config.security.CustomAuthenticationProvider;
import com.wellware.config.security.UserDetailsServiceImpl;
import com.wellware.exceptions.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .requestMatchers("/css/**",
                        "/js/**",
                        "/img/**",
                        "/fonts/**",
                        "/calender/**",
                        "/timing/**",
                        "/favicon.ico");

    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers("/actuator/**").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/").anonymous())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/signup").anonymous())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/register").anonymous())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/search").anonymous())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/home").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/profile/**").authenticated())



                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home",true)
                .permitAll()


                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()


                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .build();

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
