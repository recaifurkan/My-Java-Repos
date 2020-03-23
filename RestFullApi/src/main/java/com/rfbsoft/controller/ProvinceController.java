package com.rfbsoft.controller;

import com.rfbsoft.model.Province;
import com.rfbsoft.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ProvinceController.CONTROLLER_PATH)
public class ProvinceController {
    public static final String CONTROLLER_PATH = "api/v0/provinces";

    @Autowired
    ProvinceRepository repo;

    @GetMapping
    public List<Province> get() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional optionalPhone = repo.findById(id);
        return ResponseEntity.ok(optionalPhone);
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody Province phone) {

        return new ResponseEntity(repo.save(phone), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Province phone) {
        return new ResponseEntity(repo.save(phone), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody Province phone) {
        repo.delete(phone);
        return ResponseEntity.ok("deleted");
    }
}
