package com.pharmacy.management.pharmacy_management_app.service;

import com.pharmacy.management.pharmacy_management_app.models.Product;
import com.pharmacy.management.pharmacy_management_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
