package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.dto.UserDto;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import com.example.backend.util.ValidationGroups;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 사용자 로그인 요청을 처리하는 API
     * @param user 사용자의 username과 password를 담고 있는 객체
     * @return 로그인 성공 시 User 객체, 실패 시 에러 메시지 반환
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Validated(ValidationGroups.LoginGroup.class)
            @RequestBody UserDto user) {
        ResponseDto<User> result = userService.authenticate(user.getUsername(), user.getPassword());

        if (result.isResult()) {
            return ResponseEntity.ok(result.getData());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.getMessage());
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