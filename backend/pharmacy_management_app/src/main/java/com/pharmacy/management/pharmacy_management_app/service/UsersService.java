package com.pharmacy.management.pharmacy_management_app.service;

import com.pharmacy.management.pharmacy_management_app.data_transfer_object.LoginRequest;
import com.pharmacy.management.pharmacy_management_app.data_transfer_object.LoginResponse;
import com.pharmacy.management.pharmacy_management_app.models.SellingPoint;
import com.pharmacy.management.pharmacy_management_app.models.Users;
import com.pharmacy.management.pharmacy_management_app.repository.SellingPointRepository;
import com.pharmacy.management.pharmacy_management_app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SellingPointRepository sellingPointRepository;
    @Autowired
    public EmailService emailService;
    @Autowired
    public OtpService otpService;


    public Users addUser(Users user){
        //Encryption of password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Here I find the SellingPoint by ID from the input
        SellingPoint sellingPoint = sellingPointRepository.findById(user.getSellingPoint().getSellingPointId())
                .orElseThrow(() -> new RuntimeException("Selling Point not found with ID: " + user.getSellingPoint().getSellingPointId()));

        // Here it ensure the user's sellingPoint is set to the retrieved SellingPoint object
        user.setSellingPoint(sellingPoint);

        user.setUserStatus("DENIED");
        return usersRepository.save(user);
    }
    public List<Users> getAllUsers(){

        return usersRepository.findAll();
    }

//    public LoginResponse login(LoginRequest loginRequest) {
//        // Find user by email
//        Users user = usersRepository.findByEmail(loginRequest.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequest.getEmail()));
//
//        // Check password
//        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        // Check user status
//        if ("DENIED".equals(user.getUserStatus())) {
//            throw new RuntimeException("User account is not approved");
//        }
//
//        // Convert to LoginResponse
//        LoginResponse response = new LoginResponse();
//        response.setUserId(user.getUserId());
//        response.setFullName(user.getFullName());
//        response.setEmail(user.getEmail());
//        response.setRole(user.getRole());
//        response.setUserStatus(user.getUserStatus());
//
//        return response;
//    }

    public LoginResponse login(LoginRequest loginRequest) {
        // Find user by email
        Users user = usersRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequest.getEmail()));

        // Check password
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Check user status
        if ("DENIED".equals(user.getUserStatus())) {
            throw new RuntimeException("User account is not approved");
        }

        // Generate OTP
        String generatedOtp = otpService.generateOtp();

        // Store OTP
        otpService.storeOtp(user.getEmail(), generatedOtp);

        // Send OTP via email
        emailService.sendOtpEmail(user.getEmail(), generatedOtp);

        // Prepare login response
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setUserStatus(user.getUserStatus());
        response.setOtpToken("PENDING"); // Indicates OTP verification is pending

        return response;
    }

    public LoginResponse verifyOtp(String email, String otp) {
        // Find user by email
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Validate OTP
        if (otpService.validateOtp(email, otp)) {
            // Prepare successful login response
            LoginResponse response = new LoginResponse();
            response.setUserId(user.getUserId());
            response.setFullName(user.getFullName());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());
            response.setUserStatus(user.getUserStatus());
            response.setOtpToken("VERIFIED"); // Indicates successful OTP verification

            return response;
        } else {
            throw new RuntimeException("Invalid OTP");
        }
    }

    public LoginResponse resendOtp(String email) {
        // Find user by email
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Invalidate previous OTP
        otpService.invalidateOtp(email);

        // Generate new OTP
        String generatedOtp = otpService.generateOtp();

        // Store new OTP
        otpService.storeOtp(user.getEmail(), generatedOtp);

        // Send new OTP via email
        emailService.sendOtpEmail(user.getEmail(), generatedOtp);

        // Prepare login response
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setUserStatus(user.getUserStatus());
        response.setOtpToken("PENDING"); // Indicates OTP verification is pending

        return response;
    }

    public Users updateUserStatus(Long userId, String userStatus){
        Users user= usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID" + userId));
        user.setUserStatus(userStatus);
        return usersRepository.save(user);
    }


}
