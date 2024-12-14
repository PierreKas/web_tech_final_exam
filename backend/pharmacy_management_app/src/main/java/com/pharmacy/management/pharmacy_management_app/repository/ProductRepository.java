package com.pharmacy.management.pharmacy_management_app.repository;

import com.pharmacy.management.pharmacy_management_app.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
