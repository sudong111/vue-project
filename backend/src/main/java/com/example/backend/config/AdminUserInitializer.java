package com.example.backend.config;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserInitializer {

    /**
     * 애플리케이션 시작 시 실행되는 CommandLineRunner 빈을 정의합니다.
     * 이 빈은 UserRepository와 PasswordEncoder를 주입받아 초기 관리자 계정을 생성합니다.
     * @param userRepository 사용자 정보를 관리하는 레포지토리
     * @param passwordEncoder 비밀번호 암호화 도구
     * @return CommandLineRunner 인스턴스
     */
    @Bean
    public CommandLineRunner initAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // "admin"이라는 username을 가진 사용자가 이미 존재하는지 확인합니다.
            if (userRepository.findByUsername("admin") == null) {
                System.out.println("기본 관리자 계정을 생성합니다.");

                // User 객체를 생성하고 비밀번호를 암호화하여 설정합니다.
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123!"));
                admin.setAuthority("admin");
                admin.setEmail("admin@example.com");

                // 데이터베이스에 관리자 계정을 저장합니다.
                userRepository.insertUser(admin);
                System.out.println("기본 관리자 계정 생성 성공");
            }
        };
    }
}
