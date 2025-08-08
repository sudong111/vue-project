package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class GuitarDto {
    private String name;
    private int subtype_id;
    private String brand;
    private int price;
    private String image_url;
    private String description;
    private int stock;
}