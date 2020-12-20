package com.springcloud.shoppingsystem.repository;

import com.springcloud.shoppingsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsProductByNameEqualsAndPriceNot(String name,Long price);

}
