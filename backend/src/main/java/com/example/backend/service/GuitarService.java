package com.example.backend.service;

import com.example.backend.dto.GuitarDto;
import com.example.backend.model.Category;
import com.example.backend.model.Guitar;
import com.example.backend.dto.ResponseDto;
import com.example.backend.model.Subtype;
import com.example.backend.repository.GuitarCategoriesRepository;
import com.example.backend.repository.GuitarRepository;
import com.example.backend.repository.GuitarSubtypesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

@Service
public class GuitarService {

    private final GuitarRepository guitarRepository;
    private final GuitarCategoriesRepository guitarCategoriesRepository;
    private final GuitarSubtypesRepository guitarSubtypesRepository;
    private final S3Service s3Service;

    public GuitarService(GuitarRepository guitarRepository, GuitarCategoriesRepository guitarCategoriesRepository, GuitarSubtypesRepository guitarSubtypesRepository, S3Service s3Service) {
        this.guitarRepository = guitarRepository;
        this.guitarCategoriesRepository = guitarCategoriesRepository;
        this.guitarSubtypesRepository = guitarSubtypesRepository;
        this.s3Service = s3Service;
    }

    public ResponseDto<Void> insert(GuitarDto guitar, MultipartFile imageFile) {
        try{
            String originalFileName = imageFile.getOriginalFilename();
            ResponseDto<Void> result = duplication(guitar);

            if(!result.isResult() || originalFileName == null) {
                return result;
            }

            Category category = guitarCategoriesRepository.findBySubtypeId(guitar.getSubtype_id());

            String imageUrl = s3Service.uploadFile(imageFile, category.getName());
            guitar.setImage_url(imageUrl);

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
