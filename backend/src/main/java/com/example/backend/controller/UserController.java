package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.UserDto;
import com.example.backend.dto.LoginResponseDto;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import com.example.backend.util.JwtTokenProvider;
import com.example.backend.util.ValidationGroups;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor가 생성자 주입을 대신해줍니다.
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 사용자 로그인 요청을 처리하는 API
     * @param user 사용자의 username과 password를 담고 있는 객체
     * @return 로그인 성공 시 LoginResponseDto, 실패 시 에러 메시지 반환
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Validated(ValidationGroups.LoginGroup.class)
            @RequestBody UserDto user) {

        // userService의 authenticate 메소드로 사용자 인증
        ResponseDto<User> authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());

        if (authenticatedUser.isResult()) {
            String jwtToken = jwtTokenProvider.generateToken(authenticatedUser.getData().getUsername());

            // ⭐ LoginResponseDto에 토큰과 사용자 정보 담아서 반환
            LoginResponseDto response = LoginResponseDto.builder()
                    .token(jwtToken)
                    .expiresIn(jwtTokenProvider.getTokenValidityInMilliseconds())
                    .userId(authenticatedUser.getData().getUsername())
                    .build();

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }

    /**
     * 사용자 회원가입 요청을 처리하는 API
     * @param user 사용자의 정보를 담고 있는 객체
     * @return 회원가입 성공 시 성공 메세지, 실패 시 에러 메시지 반환
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        ResponseDto<Void> result = userService.signUp(user);

        if (result.isResult()) {
            return ResponseEntity.ok(result.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.getMessage());
        }
    }

    /**
     * 사용자 회원가입 요청을 처리하는 API
     * @param user 사용자의 정보를 담고 있는 객체
     * @return 회원가입 성공 시 성공 메세지, 실패 시 에러 메시지 반환
     */
    @PostMapping("/user/duplication")
    public ResponseEntity<?> duplication(@RequestBody User user) {
        ResponseDto<Void> result = userService.duplication(user);

        if (result.isResult()) {
            return ResponseEntity.ok(result.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.getMessage());
        }
    }
}
