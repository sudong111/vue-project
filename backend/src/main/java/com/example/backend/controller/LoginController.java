package com.example.backend.controller;

import com.example.backend.model.LoginRequest;
import com.example.backend.model.User;
import com.example.backend.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Vue.js 개발 서버의 주소를 허용합니다.
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 사용자 로그인 요청을 처리하는 API
     * @param loginRequest 사용자의 username과 password를 담고 있는 객체
     * @return 로그인 성공 시 User 객체, 실패 시 에러 메시지 반환
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 로그인 서비스에서 User 객체를 반환하도록 수정
        User authenticatedUser = loginService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (authenticatedUser != null) {
            // 성공 시 User 객체를 JSON 형태로 반환합니다.
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}