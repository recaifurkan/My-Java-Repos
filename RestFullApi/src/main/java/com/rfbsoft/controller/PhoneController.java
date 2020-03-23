package com.rfbsoft.controller;


import com.rfbsoft.model.Category;
import com.rfbsoft.model.Neighborhood;
import com.rfbsoft.model.Phone;
import com.rfbsoft.repository.CategoryRepository;
import com.rfbsoft.repository.NeighborhoodRepository;
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

    @Autowired
    NeighborhoodRepository neighborhoodRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping
    public List<Phone> get() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {

        boolean exist = repo.existsById(id);
        if(!exist){
            return new ResponseEntity("Phone not found",HttpStatus.NOT_FOUND);
        }
        Phone phone = repo.findById(id).get();

        return ResponseEntity.ok(phone);
    }

    @PostMapping("/{neighborhoodId}/{categoryId}")
    public ResponseEntity add(@Valid @RequestBody Phone object , @PathVariable Long neighborhoodId,@PathVariable Long categoryId) {
        boolean existNeighborhood = neighborhoodRepository.existsById(neighborhoodId);
        if(!existNeighborhood){
            return new ResponseEntity("Neighborhood not found",HttpStatus.NOT_FOUND);
        }
        Neighborhood province = neighborhoodRepository.findById(neighborhoodId).get();

        boolean existCategory = categoryRepository.existsById(categoryId);
        if(!existNeighborhood){
            return new ResponseEntity("Category not found",HttpStatus.NOT_FOUND);
        }
        Category category = categoryRepository.findById(categoryId).get();

        object.setNeighborhood(province);
        object.setCategory(category);
        return new ResponseEntity(repo.save(object),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Phone phone) {
        return new ResponseEntity(repo.save(phone), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody Phone phone) {
        repo.delete(phone);
        return ResponseEntity.ok("deleted");
    }

}