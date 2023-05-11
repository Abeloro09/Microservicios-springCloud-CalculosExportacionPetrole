package com.abelardo.gatewayserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {


    //private JwtAuthenticationFilter authenticationFilter;


    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("/security/oauth/**").permitAll()
                .pathMatchers(HttpMethod.GET, "/infotank",
                        "/infotank/byId/{id}").permitAll()
                .anyExchange().authenticated()
                .and().csrf().disable()
                .build();
    }

}