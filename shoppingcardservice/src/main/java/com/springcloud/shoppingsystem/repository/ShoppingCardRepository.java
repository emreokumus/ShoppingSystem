package com.springcloud.shoppingsystem.repository;

import com.springcloud.shoppingsystem.entity.ShoppingCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCardRepository extends JpaRepository<ShoppingCard,Long> {
    boolean existsByShoppingCardName(String name);
}
