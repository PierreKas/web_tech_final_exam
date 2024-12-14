package com.pharmacy.management.pharmacy_management_app.repository;

import com.pharmacy.management.pharmacy_management_app.models.SellingPoint;
import com.pharmacy.management.pharmacy_management_app.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    // Method to find transactions by a specific date
    //List<Transaction> findBySellingDate(Date date);
    @Query("SELECT t FROM Transaction t WHERE DATE(t.sellingDate) = DATE(:specificDate)")
    List<Transaction> findBySellingDate(@Param("specificDate") Date specificDate);

    // Method to find transactions by selling point
    List<Transaction> findBySellingPoint(SellingPoint sellingPoint);


}
