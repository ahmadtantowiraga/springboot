package com.enigma.enigma_shop.repository;

import com.enigma.enigma_shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByName(String name);
    List<Product> findAllByStock(Integer stock);
    List<Product> findByNameOrStockOrPriceBetween(String name, Integer stock, Long minPrice, Long maxPrice);

}
