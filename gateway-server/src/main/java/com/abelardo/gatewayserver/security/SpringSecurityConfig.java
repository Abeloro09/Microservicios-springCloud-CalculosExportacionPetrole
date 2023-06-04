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


    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("/security/oauth/**").permitAll()
                .pathMatchers(HttpMethod.GET,"/analisis",
                        "/analisis/byId/{id}",
                        "/infotank",
                        "/infotank/byId/{id},",
                        "/liquidacion/cargues",
                        "/liquidacion/cargueById/{cargueId}",
                        "/liquidacion/movimientos",
                        "/liquidacion/movimientoById/{id}",
                        "/usuarios/usuarios").permitAll()
                .pathMatchers(HttpMethod.POST, "/analisis",
                        "/infotank",
                        "/liquidacion/crearmovimiento/{cargueId}").permitAll()
                .pathMatchers(HttpMethod.PUT, "/analisis/update/{id}",
                        "/infotank/update/{id}",
                        "/liquidacion/calcularLiquidacion/{id}/",
                        "/liquidacion/editarLiquidacion/{id}/").permitAll()
                .pathMatchers(HttpMethod.DELETE, "/analisis/delete/{id}",
                        "/infotank/delete/{id}",
                        "/liquidacion/eliminarMovimiento/{id}/").permitAll()
                .pathMatchers(HttpMethod.GET, "/usuarios/usuarios/{id}").hasAnyRole("ADMIN", "USER")
                .pathMatchers("/liquidacion/cargues").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and().addFilterAt(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                .build();
    }

}