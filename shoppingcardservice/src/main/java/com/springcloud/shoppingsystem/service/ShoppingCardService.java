package com.springcloud.shoppingsystem.service;

import com.springcloud.shoppingsystem.entity.Product;
import com.springcloud.shoppingsystem.entity.ShoppingCard;

import java.util.List;
import java.util.Map;

public interface ShoppingCardService {
    ShoppingCard create(ShoppingCard shoppingCard);
    ShoppingCard addProducts(Long shoppingCardId, List<Product> products);
    Map<String,String> getShoppingCardTotalPrice(Long shoppingCardId);
}
