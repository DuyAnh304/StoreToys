package com.toyshop.StoreToys_API.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toyshop.StoreToys_API.DTOs.request.BrandRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.DTOs.respone.BrandRespone;
import com.toyshop.StoreToys_API.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService bSer;

    @GetMapping(value = {"/", ""})
    public APIRespone<?> getAllBrands() {
        return new APIRespone<>(HttpStatus.OK.value(), "Request Successfully", bSer.getAllBrands());
    }

    @GetMapping("/{id}")
    public APIRespone<?> getBrandById(@PathVariable int id) {
        return new APIRespone<>(HttpStatus.OK.value(), "Request successfully", bSer.getBrand(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public APIRespone<?> addBand(@ModelAttribute BrandRequest bReq){
        return new APIRespone<>(HttpStatus.CREATED.value(), "Brand created successfully",
                bSer.addBrand(bReq));
    }

    @PutMapping(consumes = {"multipart/form-data"}, value = {"/{id}"})
    public APIRespone<?> updateBrand(@PathVariable int id, @ModelAttribute BrandRequest bReq){
        return new APIRespone<>(HttpStatus.ACCEPTED.value(), "Brand updated successfully",
                bSer.updateBrand(id,bReq));
    }

    @DeleteMapping("/{id}")
    public APIRespone<?> deleteBrand(@PathVariable int id) {
        bSer.deleteBrand(id);
        return new APIRespone<>(HttpStatus.NO_CONTENT.value(), "Brand deleted successfully");
    }
}
