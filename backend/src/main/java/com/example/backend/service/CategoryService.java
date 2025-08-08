package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.model.Category;
import com.example.backend.repository.GuitarCategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final GuitarCategoriesRepository guitarCategoriesRepository;

    public CategoryService(GuitarCategoriesRepository guitarCategoriesRepository) {
        this.guitarCategoriesRepository = guitarCategoriesRepository;
    }

    public ResponseDto<List<Category>> selectAllCategory() {
        try {
            List<Category> categories = guitarCategoriesRepository.selectAll();
            return ResponseDto.success("서브타입 조회에 성공했습니다.",categories);

        } catch (Exception e) {
            return ResponseDto.fail(e.getMessage(), new ArrayList<>());
        }
    }
}
