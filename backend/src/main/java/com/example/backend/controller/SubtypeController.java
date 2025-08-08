package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.model.Subtype;
import com.example.backend.service.SubtypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subtype")
@RequiredArgsConstructor
public class SubtypeController {
    private final SubtypeService subtypeService;

    @GetMapping("/selectAll")
    public ResponseEntity<?> selectAllSubtype() {
        ResponseDto<List<Subtype>> result = subtypeService.selectAllSubtype();
        if (result.isResult()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.getMessage());
        }
    }

}
