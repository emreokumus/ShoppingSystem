package com.springcloud.shoppingsystem.service;

import com.springcloud.shoppingsystem.entity.Product;
import com.springcloud.shoppingsystem.exceptions.AvailableProductFound;
import com.springcloud.shoppingsystem.exceptions.ProductNotFoundException;
import com.springcloud.shoppingsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product productTotalPriceByID(Long id,Integer count) throws RuntimeException {
        Product product=productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Element not found by id!"));
        product.setPrice(product.getPrice()*count);
        return product;
    }

    @Override
    public Product productByID(Long id) throws RuntimeException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Element not found by id!"));
    }

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        if(!productRepository.existsProductByNameEqualsAndPriceNot(product.getName(),product.getPrice())){
            return productRepository.save(product);
        }else {
            throw new AvailableProductFound("Product exist with different price. It's not possible");
        }

    }
}
