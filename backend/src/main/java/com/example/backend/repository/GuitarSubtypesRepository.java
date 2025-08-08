package com.example.backend.repository;


import com.example.backend.model.GuitarSubtype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GuitarSubtypesRepository {

    void insertGuitarSubtypes(GuitarSubtype subtypes);

    GuitarSubtype findByName(@Param("name") String name);

}
