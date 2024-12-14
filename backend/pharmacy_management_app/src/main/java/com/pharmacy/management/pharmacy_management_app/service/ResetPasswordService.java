package com.pharmacy.management.pharmacy_management_app.service;

import com.pharmacy.management.pharmacy_management_app.models.Users;
import com.pharmacy.management.pharmacy_management_app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean initiatePasswordReset(String email) {
        // Find user by email
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Generate OTP
        String otp = otpService.generateOtp();

        // Store OTP
        otpService.storeOtp(email, otp);

        // Send OTP via email
        emailService.sendOtpForResetingPassword(email, otp);

        return true;
    }

    public boolean resetPassword(String email, String otp, String newPassword) {
        // Validate OTP
        if (!otpService.validateOtp(email, otp)) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        // Find user by email
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Encode new password
        String encodedPassword = passwordEncoder.encode(newPassword);

        // Update password
        user.setPassword(encodedPassword);
        usersRepository.save(user);

        return true;
    }
}