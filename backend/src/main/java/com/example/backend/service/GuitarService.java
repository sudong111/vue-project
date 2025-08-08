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

    public GuitarService(GuitarRepository guitarRepository, GuitarCategoriesRepository guitarCategoriesRepository, GuitarSubtypesRepository guitarSubtypesRepository) {
        this.guitarRepository = guitarRepository;
        this.guitarCategoriesRepository = guitarCategoriesRepository;
        this.guitarSubtypesRepository = guitarSubtypesRepository;
    }

    public ResponseDto<Void> insert(GuitarDto guitar, MultipartFile imageFile) {
        try{
            String originalFileName = imageFile.getOriginalFilename();
            ResponseDto<Void> result = duplication(guitar);

            if(!result.isResult() || originalFileName == null) {
                return result;
            }

            Category category = guitarCategoriesRepository.findBySubtypeId(guitar.getSubtype_id());
            String staticPath = new File("src/main/resources/static/" + category.getName()).getAbsolutePath();

            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = UUID.randomUUID() + extension;

            Path filePath = Paths.get(staticPath, fileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/" + category + "/" + fileName;
            guitar.setImage_url(imageUrl);
            //Todo 이미지 캐싱하는걸로 바꾸던가 해야함.

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
