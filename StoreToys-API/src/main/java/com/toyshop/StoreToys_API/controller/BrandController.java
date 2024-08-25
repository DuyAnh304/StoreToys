package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.DTOs.request.BrandRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService bSer;

    @GetMapping(value = {"/", ""})
    public ResponseEntity<?> getAllBrands() {
        return ResponseEntity.ok(new APIRespone<>(bSer.getAllBrands(), "Request Successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable int id) {
        return ResponseEntity.status(200).body(new APIRespone<>(bSer.getBrand(id), "Request successfully"));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> addBand(@ModelAttribute BrandRequest bReq){
        return ResponseEntity.status(201)
        		.body(new APIRespone<>(bSer.addBrand(bReq), "Brand created successfully"));
    }

    @PutMapping(consumes = {"multipart/form-data"}, value = {"/{id}"})
    public ResponseEntity<?> updateBrand(@PathVariable int id, @ModelAttribute BrandRequest bReq){
        return ResponseEntity.status(202)
        		.body(new APIRespone<>(bSer.updateBrand(id,bReq), "Brand updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable int id) {
        bSer.deleteBrand(id);
        return ResponseEntity.status(204)
        		.body(new APIRespone<>(HttpStatus.NO_CONTENT.value(), "Brand deleted successfully"));
    }
}
