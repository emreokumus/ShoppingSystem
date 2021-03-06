package com.springcloud.shoppingsystem.controller;

import com.springcloud.shoppingsystem.entity.Product;
import com.springcloud.shoppingsystem.entity.ShoppingCard;
import com.springcloud.shoppingsystem.exceptions.AvailableCardException;
import com.springcloud.shoppingsystem.exceptions.AvailableProductNotFoundException;
import com.springcloud.shoppingsystem.exceptions.ShoppingCardNotFoundException;
import com.springcloud.shoppingsystem.service.ShoppingCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoppingCard")
public class ShoppingCardController {
    @Autowired
    private ShoppingCardService shoppingCardService;

    private static Logger log = LoggerFactory.getLogger(ShoppingCardController.class);

    @RequestMapping(method = RequestMethod.POST,value = "createShoppingCard")
    public ResponseEntity<?> create(@RequestBody ShoppingCard shoppingCard){
        log.info("POST shoppingCard/createShoppingCard"); //sleuth-zipkin
        try {
            ShoppingCard createdShoppingCard = shoppingCardService.create(shoppingCard);
            return ResponseEntity.ok(createdShoppingCard);
        }catch (AvailableCardException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST,value = "addProducts/{id}")
    public ResponseEntity<?> addProducts(@PathVariable("id") Long shoppingCardId, @RequestBody List<Product> products){
        log.info("POST shoppingCard/addProducts/"+shoppingCardId); //sleuth-zipkin
        try{
            return ResponseEntity.ok(shoppingCardService.addProducts(shoppingCardId,products));
        }catch (ShoppingCardNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (AvailableProductNotFoundException ex){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "getShoppingCardTotalPrice/{id}")
    public ResponseEntity<?> shoppingCardTotalPrice(@PathVariable("id") Long shoppingCardId){
        log.info("GET shoppingCard/getShoppingCardTotalPrice/"+shoppingCardId); //sleuth-zipkin
        try{
            return ResponseEntity.ok(shoppingCardService.getShoppingCardTotalPrice(shoppingCardId));
        }catch (ShoppingCardNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
