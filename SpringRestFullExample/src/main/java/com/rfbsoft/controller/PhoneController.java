package com.rfbsoft.controller;


import com.rfbsoft.model.Phone;
import com.rfbsoft.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PhoneController.CONTROLLER_PATH)
public class PhoneController {
    public static final String CONTROLLER_PATH = "api/v0/phones";

    @Autowired
    PhoneRepository repo;

    @GetMapping
    public List<Phone> getPhones() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPhone(@PathVariable Long id){
        Optional<Phone> optionalPhone = repo.findById(id);
        return ResponseEntity.ok(optionalPhone);
    }

    @PostMapping
    public ResponseEntity addPhone(@Valid @RequestBody Phone phone) {

        return new ResponseEntity(repo.save(phone), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updatePhone(@Valid @RequestBody Phone phone){
        return new ResponseEntity(repo.save(phone),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePhone(@Valid @RequestBody Phone phone){
        repo.delete(phone);
        return ResponseEntity.ok("deleted");
    }

}