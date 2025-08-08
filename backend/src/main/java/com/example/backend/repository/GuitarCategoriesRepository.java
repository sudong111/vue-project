package com.example.backend.repository;

import com.example.backend.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GuitarCategoriesRepository {

    void insertGuitarCategory(Category category);

    List<Category> selectAll();

    Category findByName(@Param("name") String name);

    Category findBySubtypeId(@Param("subtype_id") int subtypeId);

}
