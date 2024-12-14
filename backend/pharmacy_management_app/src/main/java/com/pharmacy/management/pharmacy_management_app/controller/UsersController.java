package com.pharmacy.management.pharmacy_management_app.controller;

import com.pharmacy.management.pharmacy_management_app.data_transfer_object.LoginRequest;
import com.pharmacy.management.pharmacy_management_app.data_transfer_object.LoginResponse;
import com.pharmacy.management.pharmacy_management_app.models.SellingPoint;
import com.pharmacy.management.pharmacy_management_app.models.Users;
import com.pharmacy.management.pharmacy_management_app.repository.SellingPointRepository;
import com.pharmacy.management.pharmacy_management_app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
public class UsersController {
    @Autowired
    private UsersService usersService;


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> addUser(@RequestBody Users user){

        Users savedUser = usersService.addUser(user);
        return new  ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersList = usersService.getAllUsers();
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @PostMapping(value = "/login")//, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = usersService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
    @PutMapping(value = "/status/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> updateUserStatus(@PathVariable Long userId,
                                                  @RequestBody String userStatus){
//            userStatus = userStatus.replaceAll("^\"|\"$", "");
            Users updatedUser= usersService.updateUserStatus(userId,userStatus.replaceAll("^\"|\"$", ""));
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }


    /// /OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<LoginResponse> verifyOtp(
            @RequestParam String email,
            @RequestParam String otp
    ) {
        LoginResponse response = usersService.verifyOtp(email, otp);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<LoginResponse> resendOtp(@RequestParam String email) {
        LoginResponse response = usersService.resendOtp(email);
        return ResponseEntity.ok(response);
    }
}
