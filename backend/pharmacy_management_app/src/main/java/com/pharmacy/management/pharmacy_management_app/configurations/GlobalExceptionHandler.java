//package com.pharmacy.management.pharmacy_management_app.configurations;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//import org.slf4j.Logger;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleAllUncaughtExceptions(Exception ex) {
//        logger.error("Unknown error occurred", ex);
//
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("message", "The stock is not enough");
//        errorResponse.put("error", ex.getMessage());
//        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        errorResponse.put("timestamp", LocalDateTime.now());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // More specific exception handlers can be added here
//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<Map<String, Object>> handleSQLException(SQLException ex) {
//        logger.error("SQL Exception occurred", ex);
//
//        Map<String, Object> errorResponse = new HashMap<>();
//        errorResponse.put("message", "The stock is not enough");
//        errorResponse.put("error", ex.getMessage());
//        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        errorResponse.put("timestamp", LocalDateTime.now());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
package com.pharmacy.management.pharmacy_management_app.configurations;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, Object>> handleSQLException(SQLException ex) {
        logger.error("SQL Exception occurred", ex);

        Map<String, Object> errorResponse = new HashMap<>();

        // Check for specific MySQL trigger error messages
        if (ex.getMessage().contains("Few items remain in the stock")) {
            errorResponse.put("message", "Few items remain in the stock");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ex.getMessage().contains("The stock is not enough")) {
            errorResponse.put("message", "The stock is not enough");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ex.getMessage().contains("User account is not approved")) {
            errorResponse.put("message", "User account is not approved");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ex.getMessage().contains("Invalid or expired OTP")) {
            errorResponse.put("message", "Invalid or expired OTP");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ex.getMessage().contains("Invalid password")) {
            errorResponse.put("message", "Invalid password");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        if (ex.getMessage().contains("Invalid OTP")) {
            errorResponse.put("message", "Invalid OTP");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Default SQL error handling
        errorResponse.put("message", "Database operation failed");
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
        logger.error("Runtime Exception occurred", ex);

        Map<String, Object> errorResponse = new HashMap<>();

        // Specific error message handling
        if (ex.getMessage().contains("Few items remain in the stock")) {
            errorResponse.put("message", "Few items remain in the stock");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ex.getMessage().contains("The stock is not enough")) {
            errorResponse.put("message", "The stock is not enough");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Default runtime error handling
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("error", "An error occurred during processing");
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllUncaughtExceptions(Exception ex) {
        logger.error("Unknown error occurred", ex);

        Map<String, Object> errorResponse = new HashMap<>();

        // Specific error message handling
        if (ex.getMessage().contains("Few items remain in the stock")) {
            errorResponse.put("message", "Few items remain in the stock");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ex.getMessage().contains("The stock is not enough")) {
            errorResponse.put("message", "The stock is not enough");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Default error handling
        errorResponse.put("message", "An unexpected error occurred");
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}