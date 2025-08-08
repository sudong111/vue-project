package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.model.Guitar;
import com.example.backend.service.GuitarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guitar")
@RequiredArgsConstructor
public class GuitarController {

    private final GuitarService guitarService;

    /**
     * 기타 제품 Insert 요청을 처리하는 API
     * @param guitar 제품의 데이터를 담고있는 객체
     * @return 기타 insert 성공 시 responseDto, 실패 시 에러 메시지 반환
     */
    @PostMapping("/insert")
    public ResponseEntity<?> insertGuitar(@RequestBody Guitar guitar) {

        ResponseDto<Void> result = guitarService.insert(guitar);

        if (result.isResult()) {
            return ResponseEntity.ok(result.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.getMessage());
        }
    }
}
