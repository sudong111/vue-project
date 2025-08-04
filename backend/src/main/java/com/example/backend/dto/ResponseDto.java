package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto<T> {

    private boolean result;
    private String message;
    private T data;

    // 성공 응답을 생성하는 정적 팩토리 메소드
    public static <T> ResponseDto<T> success(String message, T data) {
        return new ResponseDto<>(true, message, data);
    }

    // 실패 응답을 생성하는 정적 팩토리 메소드
    public static <T> ResponseDto<T> fail(String message, T data) {
        return new ResponseDto<>(false, message, data);
    }

    // 데이터가 없는 성공 응답을 생성하는 정적 팩토리 메소드
    public static ResponseDto<Void> success(String message) {
        return new ResponseDto<>(true, message, null);
    }

    // 데이터가 없는 실패 응답을 생성하는 정적 팩토리 메소드
    public static ResponseDto<Void> fail(String message) {
        return new ResponseDto<>(false, message, null);
    }
}
