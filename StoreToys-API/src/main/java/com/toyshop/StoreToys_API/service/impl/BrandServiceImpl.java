package com.toyshop.StoreToys_API.service.impl;

import com.toyshop.StoreToys_API.DTOs.request.BrandRequest;
import com.toyshop.StoreToys_API.DTOs.respone.BrandRespone;
import com.toyshop.StoreToys_API.mapper.BrandMapper;
import com.toyshop.StoreToys_API.model.Brand;
import com.toyshop.StoreToys_API.repository.BrandRepository;
import com.toyshop.StoreToys_API.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository bRepo;

    @Autowired
    private BrandMapper bMap;

    private final String folder = "uploads";

    @Override
    public List<BrandRespone> getAllBrands() {
        return bMap.toListRespone(bRepo.findAll());
    }

    @Override
    public BrandRespone getBrand(int id) {
        return bMap.toRespone(this.findById(id));
    }

    @Override
    public BrandRespone addBrand(BrandRequest bReq) {
        try {
            Brand b = Brand.builder()
                    .brandName(bReq.getBrand_name())
                    .brandImg(this.store(bReq.getBrand_image()))
                    .build();
            return bMap.toRespone(bRepo.save(b));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BrandRespone updateBrand(int id, BrandRequest bReq) {
        Brand b = this.findById(id);
        b.setBrandName(bReq.getBrand_name());
        try {
            b.setBrandImg(this.store(bReq.getBrand_image()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bMap.toRespone(bRepo.saveAndFlush(b));
    }

    @Override
    public void deleteBrand(int id) {
        Brand b = this.findById(id);
        try {
            this.remove(b.getBrandImg());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.bRepo.deleteById(id);
    }

    @Override
    public List<BrandRespone> getByBrandName(String brandName) {
        return bMap.toListRespone(bRepo.findByBrandName(brandName).orElseThrow());
    }

    private Path getUploadDirLocation() throws IOException {
        Path folderPath = Paths.get(folder).toAbsolutePath().normalize();
        if(!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        return folderPath;
    }

    private String store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadDir = getUploadDirLocation().resolve(fileName);
        Files.copy(file.getInputStream(), uploadDir, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }


    private void remove(String imgName) throws IOException {
        Path removeDir = this.getUploadDirLocation().resolve(imgName);
        if (Files.exists(removeDir)) {
            Files.delete(removeDir);
        }
    }

    private Brand findById(int id) {
        return bRepo.findById(id).get();
    }
}
