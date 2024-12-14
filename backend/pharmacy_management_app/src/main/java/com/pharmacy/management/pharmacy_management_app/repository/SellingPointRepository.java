package com.pharmacy.management.pharmacy_management_app.repository;

import com.pharmacy.management.pharmacy_management_app.models.SellingPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellingPointRepository extends JpaRepository<SellingPoint, Long> {

    //This line select names and addresses of selling points from the db
    Optional<SellingPoint> findByNameAndAddress(String name, String address);
}
