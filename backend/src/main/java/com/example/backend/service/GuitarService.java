package com.example.backend.service;

import com.example.backend.model.Guitar;
import com.example.backend.dto.ResponseDto;
import com.example.backend.repository.GuitarRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class GuitarService {

    private final GuitarRepository guitarRepository;

    public GuitarService(GuitarRepository guitarRepository) {
        this.guitarRepository = guitarRepository;
    }

    public ResponseDto<Void> insert(Guitar guitar) {
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

    public ResponseDto<Void> duplication(Guitar guitar) {
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
