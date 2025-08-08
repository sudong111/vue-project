package com.example.backend.repository;

import com.example.backend.model.GuitarCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GuitarCategoriesRepository {

    void insertGuitarCategory(GuitarCategory category);

    GuitarCategory findByName(@Param("name") String name);

}
