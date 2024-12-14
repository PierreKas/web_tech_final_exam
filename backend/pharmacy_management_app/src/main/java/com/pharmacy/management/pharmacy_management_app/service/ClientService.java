package com.pharmacy.management.pharmacy_management_app.service;

import com.pharmacy.management.pharmacy_management_app.models.Client;
import com.pharmacy.management.pharmacy_management_app.models.Product;
import com.pharmacy.management.pharmacy_management_app.repository.ClientRepository;
import com.pharmacy.management.pharmacy_management_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    public ClientRepository clientRepository;

    public Client addClient(Client client){
        return clientRepository.save(client);
    }
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }
}
