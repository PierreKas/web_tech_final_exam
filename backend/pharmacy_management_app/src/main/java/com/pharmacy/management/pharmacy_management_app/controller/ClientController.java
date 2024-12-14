package com.pharmacy.management.pharmacy_management_app.controller;

import com.pharmacy.management.pharmacy_management_app.models.Client;
import com.pharmacy.management.pharmacy_management_app.models.Product;
import com.pharmacy.management.pharmacy_management_app.service.ClientService;
import com.pharmacy.management.pharmacy_management_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class ClientController {
    @Autowired
    private ClientService clientService;

    //This is the end point to add a new  product
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client savedClient = clientService.addClient(client);
        return new  ResponseEntity<>(savedClient, HttpStatus.CREATED);

    }

    // Endpoint to get all products
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clientList = clientService.getAllClients();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }
}
