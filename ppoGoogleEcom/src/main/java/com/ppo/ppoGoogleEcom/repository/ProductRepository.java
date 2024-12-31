package com.ppo.ppoGoogleEcom.repository;

import com.ppo.ppoGoogleEcom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT p from Product p where "+"LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +"LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
    + "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<Product> searchProducts(String keyword);
}
