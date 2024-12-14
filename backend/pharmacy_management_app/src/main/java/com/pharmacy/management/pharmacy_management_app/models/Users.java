package com.pharmacy.management.pharmacy_management_app.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
    private  String userStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "selling_point_id")
    private  SellingPoint sellingPoint;

}
