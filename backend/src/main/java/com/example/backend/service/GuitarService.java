package com.example.backend.service;

import com.example.backend.dto.GuitarDto;
import com.example.backend.model.Guitar;
import com.example.backend.dto.ResponseDto;
import com.example.backend.repository.GuitarCategoriesRepository;
import com.example.backend.repository.GuitarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GuitarService {

    private final GuitarRepository guitarRepository;

    public GuitarService(GuitarRepository guitarRepository, GuitarCategoriesRepository guitarCategoriesRepository) {
        this.guitarRepository = guitarRepository;
    }

    public ResponseDto<Void> insert(GuitarDto guitar, MultipartFile imageFile) {
        try{
            ResponseDto<Void> result = duplication(guitar);
            if(!result.isResult()) {
                return result;
            }
            guitarRepository.insertGuitar(guitar);

            return ResponseDto.success("기타 제품 추가에 성공했습니다.");
        } catch (Exception e) {
            return ResponseDto.fail(e.getMessage());
        }
    }

    public ResponseDto<Void> duplication(GuitarDto guitar) {
        try{
            if(guitarRepository.findByNameAndBrand(guitar.getName(),guitar.getBrand()) == null) {
                return ResponseDto.success("등록 가능한 기타입니다.");
            }

            return ResponseDto.fail("중복된 기타가 존재합니다.");
        } catch (Exception e) {
            return ResponseDto.fail(e.getMessage());
        }

    }
}
