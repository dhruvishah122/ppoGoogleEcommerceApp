package com.ppo.ppoGoogleEcom.service;

import com.ppo.ppoGoogleEcom.model.Product;
import com.ppo.ppoGoogleEcom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    ProductRepository repo;

    public List<Product> getAllProducts() {
        System.out.println(repo.findById(1));
        return repo.findAll();
    }
    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
        } else {
            throw new IllegalArgumentException("Uploaded file is invalid");
        }
       System.out.println(product.getId());

        return repo.save(product);
    }

    public Product getProduct(int id) {
        return repo.findById(id).get();
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageType(imageFile.getContentType());
        product.setImageName(imageFile.getOriginalFilename());
        return repo.save(product);
    }

    public void deleteProduct(Product product) {
        repo.delete(product);
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
