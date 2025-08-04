package com.example.backend.dto;

import com.example.backend.util.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// UserDto는 모든 요청에 사용됩니다.
@Getter
@Setter
public class UserDto {

    // 회원가입, 로그인에 필요 (아이디)
    @NotBlank(groups = {ValidationGroups.LoginGroup.class, ValidationGroups.SignUpGroup.class}, message = "아이디는 필수 입력값입니다.")
    @Size(min = 4, groups = {ValidationGroups.LoginGroup.class, ValidationGroups.SignUpGroup.class}, message = "아이디는 최소 4자 이상이어야 합니다.")
    @Size(max = 50, groups = {ValidationGroups.LoginGroup.class, ValidationGroups.SignUpGroup.class}, message = "아이디는 최대 50자 이하이어야 합니다.")
    private String username;

    // 회원가입, 로그인, 정보수정에 필요 (비밀번호)
    @NotBlank(groups = {ValidationGroups.SignUpGroup.class, ValidationGroups.LoginGroup.class}, message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,32}$",
            groups = {ValidationGroups.SignUpGroup.class, ValidationGroups.LoginGroup.class},
            message = "비밀번호는 6~32자의 영문, 숫자, 특수문자를 조합하여 입력해야 합니다.")
    private String password;

    // 회원가입, 정보수정에 필요 (이메일)
    @NotBlank(groups = {ValidationGroups.SignUpGroup.class}, message = "이메일은 필수 입력값입니다.")
    @Email(groups = {ValidationGroups.SignUpGroup.class, ValidationGroups.UpdateGroup.class}, message = "올바른 이메일 형식이 아닙니다.")
    private String email;
}
