package com.pharmacy.management.pharmacy_management_app.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "selling_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellingPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellingPointId;
    private String name;
    private String address;

}
