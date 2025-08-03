package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 사용자 이름과 비밀번호를 사용하여 인증을 시도합니다.
     * @param username 사용자 이름
     * @param password 사용자가 입력한 비밀번호
     * @return 인증 성공 시 User 객체, 실패 시 null 반환
     */
    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(null);
            return user;
        }

        return null;
    }
}