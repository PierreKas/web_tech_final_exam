package com.pharmacy.management.pharmacy_management_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String productCode;
    private String productName;
    private  double purchasePrice;
    private int quantity;
    private Date expiryDate;
    private double sellingPrice;
}
