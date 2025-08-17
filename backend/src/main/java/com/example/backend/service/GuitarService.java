package com.example.backend.service;

import com.example.backend.dto.GuitarDto;
import com.example.backend.model.Category;
import com.example.backend.dto.ResponseDto;
import com.example.backend.repository.GuitarCategoriesRepository;
import com.example.backend.repository.GuitarRepository;
import com.example.backend.repository.GuitarSubtypesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
            Category category;
            String originalFileName = imageFile.getOriginalFilename();
            ResponseDto<Void> result = duplication(guitar);

            if(!result.isResult() || originalFileName == null) {
                return result;
            }

            if(guitar.getSubtype_id() == 0) {
                category = guitarCategoriesRepository.findByName("base");
                guitar.setSubtype_id(Math.toIntExact(guitarSubtypesRepository.findByName("Base").getId()));
            }
            else {
                category = guitarCategoriesRepository.findBySubtypeId(guitar.getSubtype_id());
            }

            String imageUrl = s3Service.uploadFile(imageFile, category.getName());
            guitar.setImage_url(imageUrl);

            guitarRepository.insertGuitar(guitar);

            return ResponseDto.success("기타 제품 추가에 성공했습니다.");
        } catch (Exception e) {
            return ResponseDto.fail(e.getMessage());
        }
    }

    public ResponseDto<GuitarDto[]> get(String category, int subtype_id) {
        try {
            if(subtype_id == 0 && category.equals("All")) {
                return ResponseDto.success("", guitarRepository.getGuitarAll());
            }
            else if(subtype_id > 0) {
                return ResponseDto.success("", guitarRepository.getGuitarBySubtypeId(subtype_id));
            }
            else {
                return ResponseDto.success("", guitarRepository.getGuitarByCategoryName(category));
            }
        } catch (Exception e) {
            return ResponseDto.fail(e.getMessage(), new GuitarDto[0]);
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
