package com.rfbsoft.controller;

import com.rfbsoft.model.District;
import com.rfbsoft.model.Province;
import com.rfbsoft.repository.DistrictRepository;
import com.rfbsoft.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(DistrictController.CONTROLLER_PATH)
public class DistrictController {
    public static final String CONTROLLER_PATH = "api/v0/districts";

    @Autowired
    DistrictRepository repo;

    @Autowired
    ProvinceRepository provinceRepository;

    @GetMapping
    public List<District> get() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional optionalPhone = repo.findById(id);
        return ResponseEntity.ok(optionalPhone);
    }

    @PostMapping("/{provinceId}")
    public ResponseEntity add(@Valid @RequestBody District object , @PathVariable Long provinceId) {
        boolean existProvince = provinceRepository.existsById(provinceId);
        if(!existProvince){
            return new ResponseEntity("Province not found",HttpStatus.NOT_FOUND);
        }
        Province province = provinceRepository.findById(provinceId).get();

        object.setProvince(province);
        return new ResponseEntity(repo.save(object),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody District object) {
        return new ResponseEntity(repo.save(object), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody District object) {
        repo.delete(object);
        return ResponseEntity.ok("deleted");
    }

}
