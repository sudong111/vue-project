package com.example.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuitarDto {
    private int id;
    private String name;
    private int subtype_id;
    private String brand;
    private int price;
    private String image_url;
    private String description;
    private int stock;
}