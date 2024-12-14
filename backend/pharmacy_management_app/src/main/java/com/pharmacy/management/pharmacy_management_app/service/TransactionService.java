package com.pharmacy.management.pharmacy_management_app.service;

import com.pharmacy.management.pharmacy_management_app.models.*;
import com.pharmacy.management.pharmacy_management_app.repository.ClientRepository;
import com.pharmacy.management.pharmacy_management_app.repository.ProductRepository;
import com.pharmacy.management.pharmacy_management_app.repository.SellingPointRepository;
import com.pharmacy.management.pharmacy_management_app.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    public TransactionRepository transactionRepository;
    @Autowired
    public SellingPointRepository sellingPointRepository;
    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public ClientRepository clientRepository;

    @Transactional
    public Transaction addTransaction(Transaction transaction){
        /// Selling point
        // Here I find the SellingPoint by ID from the input
        SellingPoint sellingPoint = sellingPointRepository.findById(transaction.getSellingPoint().getSellingPointId())
                .orElseThrow(() -> new RuntimeException("Selling Point not found with ID: " + transaction.getSellingPoint().getSellingPointId()));

        // Here it ensure the user's sellingPoint is set to the retrieved SellingPoint object
        transaction.setSellingPoint(sellingPoint);

        ////Product
        Product product = productRepository.findById(transaction.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + transaction.getProduct().getId()));

         transaction.setProduct(product);

        //////Client
        Client client = clientRepository.findById(transaction.getClient().getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with ID: " + transaction.getClient().getClientId()));

        transaction.setClient(client);
        ///// Date
        transaction.setSellingDate(new Date());
        return transactionRepository.save(transaction);
    }
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    // Method to get transactions by a specific date
    public List<Transaction> getTransactionsByDate(Date date) {
        return transactionRepository.findBySellingDate(date);
    }

    // Method to get transactions by selling point ID
    public List<Transaction> getTransactionsBySellingPoint(Long sellingPointId) {
        // Retrieve the SellingPoint
        SellingPoint sellingPoint = sellingPointRepository.findById(sellingPointId)
                .orElseThrow(() -> new RuntimeException("Selling Point not found with ID: " + sellingPointId));

        // Find transactions for this selling point
        return transactionRepository.findBySellingPoint(sellingPoint);
    }
}
