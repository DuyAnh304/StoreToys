package com.toyshop.StoreToys_API.service;

import com.toyshop.StoreToys_API.DTOs.request.BrandRequest;
import com.toyshop.StoreToys_API.DTOs.respone.BrandRespone;
import com.toyshop.StoreToys_API.model.Brand;

import java.util.List;

public interface BrandService {

    List<BrandRespone> getAllBrands();

    BrandRespone getBrand(int id);

    BrandRespone addBrand(BrandRequest bReq);

    BrandRespone updateBrand(int id, BrandRequest bReq);

    void deleteBrand(int id);

}
