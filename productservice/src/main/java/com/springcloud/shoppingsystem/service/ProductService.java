package com.springcloud.shoppingsystem.service;

import com.springcloud.shoppingsystem.entity.Product;

import java.util.List;

public interface ProductService {
    Product productTotalPriceByID(Long id,Integer count);
    Product productByID(Long id);
    List<Product> listAllProducts();
    Product save(Product product);

}
