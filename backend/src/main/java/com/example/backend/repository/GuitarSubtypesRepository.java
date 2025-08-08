package com.example.backend.repository;


import com.example.backend.model.Subtype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GuitarSubtypesRepository {

    void insertGuitarSubtypes(Subtype subtypes);

    List<Subtype> selectAll();

    Subtype findByName(@Param("name") String name);

    Subtype findById(@Param("id") int id);

}
