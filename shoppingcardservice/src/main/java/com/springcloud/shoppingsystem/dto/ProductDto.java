package com.springcloud.shoppingsystem.dto;

import lombok.Data;


@Data
//Sadece deneme amacıyla oluşturuldu.
public class ProductDto {
    private Long id;
    private String name;
    private Integer count;
    private boolean isProductAvailable;
}
