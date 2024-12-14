package com.pharmacy.management.pharmacy_management_app.controller;

import com.pharmacy.management.pharmacy_management_app.models.SellingPoint;
import com.pharmacy.management.pharmacy_management_app.service.SellingPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/selling-point")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class SellingPointController {
    @Autowired
    private SellingPointService sellingPointService;

    //This is the end point to add a new selling point
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SellingPoint> addSellingPoint(@RequestBody SellingPoint sellingPoint){
        SellingPoint savedSellingPoint = sellingPointService.addSellingPoint(sellingPoint);
        return new  ResponseEntity<>(savedSellingPoint,HttpStatus.CREATED);

    }

    // Endpoint to get all selling points
    @GetMapping("/all")
    public ResponseEntity<List<SellingPoint>> getAllSellingPoints() {
        List<SellingPoint> sellingPoints = sellingPointService.getAllSellingPoints();
        return new ResponseEntity<>(sellingPoints, HttpStatus.OK);
    }

//    // Endpoint to find selling point by name and address
//    @GetMapping("/find")
//    public ResponseEntity<Optional<SellingPoint>> findByNameAndAddress(
//            @RequestParam String name,
//            @RequestParam String address
//    ) {
//        Optional<SellingPoint> sellingPoint = sellingPointService.getSellingPointByNameAndAddress(name, address);
//        return sellingPoint.isPresent()
//                ? ResponseEntity.ok(sellingPoint)
//                : ResponseEntity.notFound().build();
//    }
}
