package com.enigma.enigma_shop.services;

import com.enigma.enigma_shop.dto.request.NewProductRequest;
import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    Product create(NewProductRequest request);
    Product getById(String id);
    Page<Product> getAll(SearchProductRequest request);
    Product update(Product product);
    void delete(String id);
    List<Product> findByName(String name);
    List<Product> findByStock(Integer stock);
    List<Product> findByNameOrStockOrPriceBetween(String name, Integer stock, Long minPrice, Long maxPrice);

}
