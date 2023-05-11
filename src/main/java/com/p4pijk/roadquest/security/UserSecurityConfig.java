package com.p4pijk.roadquest.security;

import com.p4pijk.roadquest.security.handler.RoleAuthenticationSuccessHandler;
import com.p4pijk.roadquest.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class UserSecurityConfig {

    private final UserDetailsServiceImpl service;

    private static final String [] WHITE_LIST = {
            "/css/**",
            "/img/**",
            "/signup",
            "/",
            "index.html"
    };

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        daoAuthenticationProvider.setUserDetailsService(service);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
//                .defaultSuccessUrl("/",true)
                .failureForwardUrl("/login?error=true")
                .permitAll()
                .and()
                .logout(logout->logout.logoutSuccessUrl("/").permitAll());

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/manager").hasAuthority("MANAGER")
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                );
        http.httpBasic();
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new RoleAuthenticationSuccessHandler();
    }
}
