package com.pharmacy.management.pharmacy_management_app.service;

import com.pharmacy.management.pharmacy_management_app.models.SellingPoint;
import com.pharmacy.management.pharmacy_management_app.repository.SellingPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellingPointService {
    @Autowired
    private SellingPointRepository sellingPointRepository;

    //Here I add a new selling point
    public SellingPoint addSellingPoint(SellingPoint sellingPoint){

        return sellingPointRepository.save(sellingPoint);
    }

    //Here I implement service for the selection of names and addresses
    public Optional<SellingPoint> getSellingPointByNameAndAddress(String name, String address){
        return sellingPointRepository.findByNameAndAddress(name,address);
    }
    public List<SellingPoint> getAllSellingPoints(){
        return sellingPointRepository.findAll();
    }
}
