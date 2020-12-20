package com.springcloud.shoppingsystem.dto;

import lombok.Data;


@Data
public class ProductDto {
    private Long id;
    private String name;
    private Integer count;
    private boolean isProductAvailable;
}
