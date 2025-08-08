package com.example.backend.controller;

import com.example.backend.dto.ResponseDto;
import com.example.backend.model.Category;
import com.example.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/selectAll")
    public ResponseEntity<?> selectAllCategory() {
        ResponseDto<List<Category>> result = categoryService.selectAllCategory();
        if (result.isResult()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result.getMessage());
        }
    }

}
