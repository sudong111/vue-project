package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 사용자 이름과 비밀번호를 사용하여 인증을 시도합니다.
     * @param username 사용자 이름
     * @param password 사용자가 입력한 비밀번호
     * @return ResponseDto<User>
     */
    public ResponseDto<User> authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            user.setPassword(null);
            return ResponseDto.success("로그인에 성공했습니다.", user);
        }

        return ResponseDto.fail("존재하지 않는 계정이거나 비밀번호가 틀렸습니다.", user);
    }

    /**
     * 사용자의 정보로 회원가입을 시도합니다.
     * @param user 사용자 정보
     * @return ResponseDto<Void>
     */
    public ResponseDto<Void> signUp(User user) {
        if(!duplication(user).isResult()) {
            return ResponseDto.fail("중복된 계정이 존재합니다.");
        }
        user.setAuthority("user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.insertUser(user);

        return ResponseDto.success("회원가입에 성공했습니다.");
    }

    /**
     * 입력한 username 이 중복되는지 체크합니다.
     * @param user 사용자 정보
     * @return ResponseDto<Void>
     */
    public ResponseDto<Void> duplication(User user) {
        if(userRepository.findByUsername(user.getUsername()) == null) {
            return ResponseDto.success("사용가능한 아이디입니다.");
        }

        return ResponseDto.fail("중복된 계정이 존재합니다.");
    }
}