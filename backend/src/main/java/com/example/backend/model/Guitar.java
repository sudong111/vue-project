package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guitar {
    private Long id;
    private String name;
    private Long subtype_id;
    private String brand;
    private int price;
    private String image_url;
    private String description;
    private int stock;
    private Date created_at;
    private Date updated_at;
}
