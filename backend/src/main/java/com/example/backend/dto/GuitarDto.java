package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuitarDto {
    private String name;
    private long subtype_id;
    private String brand;
    private long price;
    private String description;
    private long stock;
}