package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
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
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(@RequestParam Optional<Integer> whiskyYear, Optional<Distillery> distillery, Optional<Integer> age, Optional<String> region){
        if(whiskyYear.isPresent()){
            return new ResponseEntity<>(whiskyRepository.findByYear(whiskyYear.get()), HttpStatus.OK);
        } else if (distillery.isPresent() && age.isPresent()) {
            return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(distillery.get(), age.get()), HttpStatus.OK);
        } else if (region.isPresent()){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }
}
