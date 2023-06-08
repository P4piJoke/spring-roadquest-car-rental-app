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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class UserSecurityConfig {

    private static final String[] WHITE_LIST = {
            "/css/**",
            "/img/**",
            "/signup",
            "/",
            "/sendMail",
            "/about",
            "index.html"
    };

    private static final String[] ADMIN_LIST = {
            "/admin",
            "/admin/addCar",
            "/admin/editCar",
            "/admin/saveCar",
            "/admin/deleteCar",
            "/admin/changeUser",
            "/admin/addManager",
            "/admin/saveManager",
            "/admin/deleteManager",
            "/admin/changePrice"
    };

    private static final String[] RESOURCE_LIST = {
            "/admin/**",
            "/manager/**",
            "/rent/**",
            "/profile/**"
    };

    private static final String[] MANAGER_LIST = {
            "/manager",
            "/manager/inspectOrder",
            "/manager/declineApplication",
            "/manager/acceptApplication"
    };
    private final UserDetailsServiceImpl service;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder());
        daoAuthenticationProvider.setUserDetailsService(service);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
                .failureForwardUrl("/login?error=true")
                .permitAll()
                .and()
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll());

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(MANAGER_LIST).hasAuthority("MANAGER")
                        .requestMatchers(ADMIN_LIST).hasAuthority("ADMIN")
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
        );
        http.httpBasic();
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new RoleAuthenticationSuccessHandler();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry
                        .addResourceHandler(RESOURCE_LIST)
                        .addResourceLocations("classpath:/static/");
            }
        };
    }
}
