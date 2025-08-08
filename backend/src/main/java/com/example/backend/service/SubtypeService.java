package com.example.backend.service;

import com.example.backend.dto.ResponseDto;
import com.example.backend.model.Category;
import com.example.backend.model.Subtype;
import com.example.backend.repository.GuitarCategoriesRepository;
import com.example.backend.repository.GuitarSubtypesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubtypeService {
    private final GuitarSubtypesRepository guitarSubtypesRepository;

    public SubtypeService(GuitarSubtypesRepository guitarSubtypesRepository) {
        this.guitarSubtypesRepository = guitarSubtypesRepository;
    }

    public ResponseDto<List<Subtype>> selectAllSubtype() {
        try {
            List<Subtype> subtypes = guitarSubtypesRepository.selectAll();
            return ResponseDto.success("카테고리 조회에 성공했습니다.",subtypes);

        } catch (Exception e) {
            return ResponseDto.fail(e.getMessage(), new ArrayList<>());
        }
    }
}
