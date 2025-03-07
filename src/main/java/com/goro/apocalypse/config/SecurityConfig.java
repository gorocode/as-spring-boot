package com.goro.apocalypse.config;

import com.goro.apocalypse.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Value("${FRONTEND_URL}")
    private String frontendUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.anyRequest().permitAll();
                })
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl(frontendUrl + "/home",true)
                        .failureUrl(frontendUrl + "/login")
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }

}
