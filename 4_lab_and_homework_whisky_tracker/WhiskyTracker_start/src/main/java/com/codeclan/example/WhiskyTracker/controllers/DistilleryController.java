package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(@RequestParam Optional<String> distilleryRegion, Optional<Integer> age){
        if(distilleryRegion.isPresent()){
            return new ResponseEntity<>(distilleryRepository.findByRegion(distilleryRegion.get()), HttpStatus.OK);
        } else if(age.isPresent()){
            return new ResponseEntity<>(distilleryRepository.findByWhiskiesAge(age.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }
}
