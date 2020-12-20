package com.springcloud.shoppingsystem.controller;

import com.springcloud.shoppingsystem.entity.Product;
import com.springcloud.shoppingsystem.exceptions.AvailableProductFound;
import com.springcloud.shoppingsystem.exceptions.ProductNotFoundException;
import com.springcloud.shoppingsystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET,value = "{id}/{count}")
    public ResponseEntity<?> productTotalPriceByID(@PathVariable("id") Long id,@PathVariable Integer count) {
        try {
            Product product = productService.productTotalPriceByID(id,count);
            return ResponseEntity.ok(product);
        }catch (ProductNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "{id}")
    public ResponseEntity<?> productByID(@PathVariable("id") Long id) {
        try {
            Product product = productService.productByID(id);
            return ResponseEntity.ok(product);
        }catch (ProductNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
            List<Product> products=productService.listAllProducts();
            return ResponseEntity.ok(products);
    }

    @RequestMapping(method = RequestMethod.POST,value = "saveProduct")
    public ResponseEntity<?> save(@RequestBody Product product) {
        try {
            Product registeredProduct= productService.save(product);
            return ResponseEntity.ok(registeredProduct);
        }catch (AvailableProductFound ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }
}
