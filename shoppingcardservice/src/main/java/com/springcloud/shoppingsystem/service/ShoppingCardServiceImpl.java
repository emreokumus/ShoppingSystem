package com.springcloud.shoppingsystem.service;

import com.springcloud.shoppingsystem.entity.Product;
import com.springcloud.shoppingsystem.entity.ShoppingCard;
import com.springcloud.shoppingsystem.exceptions.AvailableCardException;
import com.springcloud.shoppingsystem.exceptions.ShoppingCardNotFoundException;
import com.springcloud.shoppingsystem.repository.ProductRepository;
import com.springcloud.shoppingsystem.repository.ShoppingCardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
public class ShoppingCardServiceImpl implements ShoppingCardService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShoppingCardRepository shoppingCardRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ShoppingCard create(ShoppingCard shoppingCard) throws AvailableCardException {
        if(shoppingCardRepository.existsByShoppingCardName(shoppingCard.getShoppingCardName())==false){
            return shoppingCardRepository.save(shoppingCard);
        }else {
            throw new AvailableCardException("Bu isime sahip 'Shopping Card' mevcut."); //Unchecked exception
        }
    }

    @Override
    public ShoppingCard addProducts(Long shoppingCardId, List<Product> products) throws ShoppingCardNotFoundException {
        ShoppingCard shoppingCard=
                shoppingCardRepository
                        .findById(shoppingCardId)
                        .orElseThrow(()-> new ShoppingCardNotFoundException("Verilen id ile eşleşen 'Shopping Card' bulunamadı."));//Unchecked exception

        //Productları kaydeder.
        products.forEach(product -> productRepository.saveAndFlush(product));

        //Tüm ürünleri mevcut ürünlerede ekleyıp tekrar setleyip kaydeder
        Set<Product> newProducts=new HashSet<>(products);
        /*Set<Product> allProducts = Stream.of(newProducts, shoppingCard.getProducts()).flatMap(Set::stream)
                .collect(toSet());*/
        shoppingCard.setProducts(newProducts);
        return shoppingCardRepository.save(shoppingCard);
    }

    @Override
    public Map<String, String> getShoppingCardTotalPrice(Long shoppingCardId) throws ShoppingCardNotFoundException {
        Map<String, String> response = new HashMap<>();

        ShoppingCard shoppingCard= shoppingCardRepository
                .findById(shoppingCardId)
                .orElseThrow(()->new ShoppingCardNotFoundException("Verilen id ile eşleşen 'Shopping Card' bulunamadı."));//Unchecked exception
        RestTemplate restTemplate =new RestTemplate();

        int totalPrice= shoppingCard.getProducts().stream()//Product Stream
                .map(product -> restTemplate.getForObject("http://localhost:8082/api/p/product/"+product.getId()+"/"+product.getCount(), HashMap.class)) //HashMap stream'im var.
                .mapToInt(productResponse-> (int) productResponse.get("price")) //HashMap içindeki price'ları alıp int stream oluştururuz.
                .sum(); //int stream'in sum metodu ile toplarız.

        response.put("Total Price",Integer.toString(totalPrice));
        return response;

    }
}
