package com.pharmacy.management.pharmacy_management_app.controller;

import com.pharmacy.management.pharmacy_management_app.models.Product;
import com.pharmacy.management.pharmacy_management_app.models.SellingPoint;
import com.pharmacy.management.pharmacy_management_app.service.ProductService;
import com.pharmacy.management.pharmacy_management_app.service.SellingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class ProductController {
    @Autowired
    private ProductService productService;

    //This is the end point to add a new  product
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product savedProduct = productService.addProduct(product);
        return new  ResponseEntity<>(savedProduct, HttpStatus.CREATED);

    }

    // Endpoint to get all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
