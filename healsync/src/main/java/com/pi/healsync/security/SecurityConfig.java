package com.pi.healsync.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    "/h2-console/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                )
                .disable()
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",
                    "/signup",
                    "/h2-console/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        
        // Configuração aprimorada de origens permitidas
        config.setAllowedOriginPatterns(List.of(
            "http://localhost:[*]",
            "https://healsync-f.vercel.app",
            "https://healsync-f-*.vercel.app" // Padrão para todos os subdomínios
        ));
        
        // Métodos explícitos (evitar usar *)
        config.setAllowedMethods(List.of(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));
        
        // Headers explícitos
        config.setAllowedHeaders(List.of(
            "Authorization", "Content-Type", "Accept", "Origin",
            "X-Requested-With", "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        ));
        
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);
        
        // Configuração para tratar requisições OPTIONS
        config.addExposedHeader("Access-Control-Allow-Origin");
        config.addExposedHeader("Access-Control-Allow-Credentials");
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}