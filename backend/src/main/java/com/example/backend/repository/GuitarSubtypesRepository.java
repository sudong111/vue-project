package com.example.backend.repository;


import com.example.backend.model.GuitarSubtype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GuitarSubtypesRepository {

    void insertGuitarSubtypes(GuitarSubtype subtypes);

    /**
     * name 과 brand로 기타를 찾습니다.
     * @param id 찾을 기타의 name
     * @return 찾은 Guitar 객체, 없으면 null 반환
     */
    GuitarSubtype findById(@Param("id") Long id);

    GuitarSubtype findByCategoryId(@Param("category_id") Long category_id);

}
