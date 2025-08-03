package com.example.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 전역 CORS 설정을 위한 빈 추가
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 모든 출처(Origin)를 허용합니다.
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        // 모든 HTTP 메서드(GET, POST, PUT, DELETE 등)를 허용합니다.
        configuration.setAllowedMethods(Arrays.asList("*"));
        // 모든 헤더를 허용합니다.
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // 자격 증명(쿠키, 인증 헤더 등)을 허용합니다.
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 URL 패턴에 대해 위 설정을 적용합니다.
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Spring Security 필터 체인 구성
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource())); // CORS 설정 적용
        // 기타 보안 설정...
        return http.build();
    }
}