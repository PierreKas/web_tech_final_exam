package com.pharmacy.management.pharmacy_management_app.controller;

import com.pharmacy.management.pharmacy_management_app.models.Transaction;
import com.pharmacy.management.pharmacy_management_app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){

        Transaction savedTransaction = transactionService.addTransaction(transaction);
        return new  ResponseEntity<>(savedTransaction, HttpStatus.CREATED);

    }
    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactionList = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    // Endpoint to get transactions by date
    @GetMapping("/by-date")
    public ResponseEntity<List<Transaction>> getTransactionsByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<Transaction> transactions = transactionService.getTransactionsByDate(date);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // Endpoint to get transactions by selling point
    @GetMapping("/by-selling-point/{sellingPointId}")
    public ResponseEntity<List<Transaction>> getTransactionsBySellingPoint(
            @PathVariable Long sellingPointId) {
        List<Transaction> transactions = transactionService.getTransactionsBySellingPoint(sellingPointId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
