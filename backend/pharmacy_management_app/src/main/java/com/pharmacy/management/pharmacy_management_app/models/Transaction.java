package com.pharmacy.management.pharmacy_management_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String billCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "selling_date", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date sellingDate;

    private double totalPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "selling_point_id")
    private SellingPoint sellingPoint;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;
}