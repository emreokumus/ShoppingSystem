package com.springcloud.shoppingsystem.repository;

import com.springcloud.shoppingsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsProductByName(String name);
    boolean existsProductByIdAndName(Long id, String name);

}
