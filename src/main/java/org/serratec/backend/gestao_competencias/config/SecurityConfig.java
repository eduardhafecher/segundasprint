package org.serratec.backend.gestao_competencias.config;

import org.serratec.backend.gestao_competencias.security.JwtAuthenticationFilter;
import org.serratec.backend.gestao_competencias.security.JwtAuthorizationFilter;
import org.serratec.backend.gestao_competencias.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/usuarios").permitAll()
                        //.requestMatchers(HttpMethod.GET,"/funcionarios").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()


//                        // liberando pra popular o banco
//                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/projetos/").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/metas").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/analises").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/colaboradores").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/colaboradores/*/hardskills").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/hardskill").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/analise").permitAll()

                        .requestMatchers(HttpMethod.GET, "/funcionarios/faixa", "/funcionarios/pagina", "/funcionarios/nome")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/funcionarios").hasAnyRole("ADMIN", "USER","RH")
                        .requestMatchers(HttpMethod.GET, "/funcionarios/*/foto").hasAnyRole("ADMIN", "USER","RH")
                        .requestMatchers(HttpMethod.POST, "/funcionarios").hasAnyRole("ADMIN", "USER","RH")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions().disable());

        http.addFilterBefore(new JwtAuthenticationFilter(
                        authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil),
                UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(new JwtAuthorizationFilter(
                        authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil, userDetailsService),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:2000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
        return source;
    }


}
