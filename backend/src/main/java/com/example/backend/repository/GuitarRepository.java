package com.example.backend.repository;


import com.example.backend.dto.GuitarDto;
import com.example.backend.model.Guitar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GuitarRepository {

    void insertGuitar(GuitarDto guitar);

    /**
     * name 과 brand로 기타를 찾습니다.
     * @param name 찾을 기타의 name
     * @param brand 찾을 기타의 brand
     * @return 찾은 Guitar 객체, 없으면 null 반환
     */
    Guitar findByNameAndBrand(@Param("name") String name, @Param("brand") String brand);


}
