package com.pharmacy.management.pharmacy_management_app.data_transfer_object;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Long userId;
    private String fullName;
    private String email;
    private String role;
    private String userStatus;
    private String otpToken;
}
