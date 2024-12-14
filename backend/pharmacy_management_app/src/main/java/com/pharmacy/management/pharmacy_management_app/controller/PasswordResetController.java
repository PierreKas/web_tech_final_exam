package com.pharmacy.management.pharmacy_management_app.controller;

import com.pharmacy.management.pharmacy_management_app.service.ResetPasswordService;
import com.pharmacy.management.pharmacy_management_app.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pwd")
public class PasswordResetController {
    @Autowired
    private ResetPasswordService resetPasswordService;

    // Initiate password reset - send OTP
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {
            resetPasswordService.initiatePasswordReset(email);
            return ResponseEntity.ok("OTP sent successfully to " + email);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Verify OTP and reset password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String newPassword) {
        try {
            resetPasswordService.resetPassword(email, otp, newPassword);
            return ResponseEntity.ok("Password reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}