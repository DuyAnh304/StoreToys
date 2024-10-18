package com.toyshop.StoreToys_API.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.toyshop.StoreToys_API.DTOs.request.ProductRequest;
import com.toyshop.StoreToys_API.DTOs.respone.ProductRespone;
import com.toyshop.StoreToys_API.mapper.ProductMapper;
import com.toyshop.StoreToys_API.model.Brand;
import com.toyshop.StoreToys_API.model.Category;
import com.toyshop.StoreToys_API.model.Product;
import com.toyshop.StoreToys_API.repository.BrandRepository;
import com.toyshop.StoreToys_API.repository.CategoryRepository;
import com.toyshop.StoreToys_API.repository.ProductRepository;
import com.toyshop.StoreToys_API.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository pRep;
	
	@Autowired
	private CategoryRepository cRep;
	
	@Autowired
	private BrandRepository bRep;
	
	@Autowired
	private ProductMapper pMap;
	
	private final String folder = "uploads";

	@Override
	public List<ProductRespone> getAllProduct() {
		// TODO Auto-generated method stub
		return pMap.toListRespone(pRep.findAll());
	}

	@Override
	public ProductRespone getProduct(int id) {
		// TODO Auto-generated method stub
		return pMap.toRespone(this.getById(id));
	}

	@Override
	public ProductRespone addProduct(ProductRequest pReq) {
		// TODO Auto-generated method stub
		Category existingCategory = this.getCategoryById(pReq.getCategory_id());
		Brand existingBrand = this.getBrandById(pReq.getBrand_id());
		try {
			Product newProduct = Product.builder()
					.category(existingCategory)
					.brand(existingBrand)
					.productName(pReq.getProduct_name())
					.productQuantity(pReq.getProduct_quantity())
					.productImg(this.store(pReq.getProduct_img()))
					.productSex(pReq.getProduct_sex())
					.productPrice(pReq.getProduct_price())
					.build();
			return pMap.toRespone(pRep.save(newProduct));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public ProductRespone updateProduct(int id, ProductRequest pReq) {
		// TODO Auto-generated method stub
		Product product = this.getById(id);
		Category existingCategory = this.getCategoryById(pReq.getCategory_id());
		Brand existingBrand = this.getBrandById(pReq.getBrand_id());
		try {
			product.setProductName(pReq.getProduct_name());
			product.setCategory(existingCategory);
			product.setBrand(existingBrand);
			product.setProductQuantity(pReq.getProduct_quantity());
			product.setProductImg(this.store(pReq.getProduct_img()));
			product.setProductSex(pReq.getProduct_sex());
			product.setProductPrice(pReq.getProduct_price());
			return pMap.toRespone(pRep.saveAndFlush(product));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		Product p = this.getById(id);
		try {
			this.remove(p.getProductImg());
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.bRep.deleteById(id);
	}

	@Override
	public List<ProductRespone> getByCategoryName(String name) {
		return pMap.toListRespone(pRep.findByCategoryName(name));
	}

	@Override
	@Cacheable(value = "product", key = "#name", unless = "#result == null")
	public List<ProductRespone> getByBrandName(String name) {
		List<Product> list = pRep.findByBrandName(name).orElseThrow();
		return pMap.toListRespone(list);
	}

	@Override
	public List<ProductRespone> getBySex(String sex) {
		return pMap.toListRespone(pRep.findBySex(sex).orElseThrow());
	}

	private Product getById(int id) {
		return pRep.findById(id).orElseThrow();
	}

	private Category getCategoryById(int id) {
		return cRep.findById(id).orElseThrow();
	}
	
	private Brand getBrandById(int id) {
		return bRep.findById(id).orElseThrow();
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
}
