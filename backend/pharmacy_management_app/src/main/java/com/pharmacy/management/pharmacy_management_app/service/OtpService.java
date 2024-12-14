package com.pharmacy.management.pharmacy_management_app.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {
    // Store OTPs with their associated email
    private Map<String, String> otpStorage = new HashMap<>();

    public String generateOtp() {
        // Generate a 6-digit OTP
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public void storeOtp(String email, String otp) {
        // Remove any existing OTP for this email before storing new one
        otpStorage.remove(email);
        otpStorage.put(email, otp);
    }

    public boolean validateOtp(String email, String userProvidedOtp) {
        // Check if OTP exists for the email
        String storedOtp = otpStorage.get(email);

        // Validate and remove the OTP after use
        if (storedOtp != null && storedOtp.equals(userProvidedOtp)) {
            otpStorage.remove(email);
            return true;
        }

        return false;
    }

    public void invalidateOtp(String email) {
        // Remove OTP for the given email
        otpStorage.remove(email);
    }
}