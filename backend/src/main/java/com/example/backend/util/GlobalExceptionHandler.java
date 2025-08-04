package com.example.backend.util;

import com.example.backend.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // 유효성 검사에 실패한 첫 번째 필드의 오류 메시지를 가져옵니다.
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = "유효성 검사 오류";
        if (fieldError != null) {
            errorMessage = fieldError.getDefaultMessage();
        }

        // ResponseDto.fail()에 오류 메시지를 담아 반환합니다.
        return ResponseDto.fail(errorMessage);
    }
}
