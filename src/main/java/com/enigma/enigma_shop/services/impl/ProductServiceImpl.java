package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.repository.ProductRepository;
import com.enigma.enigma_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// lombok RequiredArgumentConstructor -> constructor injection
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product getById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new RuntimeException("product not found");
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        getById(product.getId());
        return productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        Product currentProduct = getById(id);
        productRepository.delete(currentProduct);
    }

    @Override
    public List<Product> findByName(String name) {
        if(productRepository.findAllByName(name).isEmpty())return getAll();
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> findByStock(Integer stock) {
        if(productRepository.findAllByStock(stock).isEmpty())return getAll();
        return productRepository.findAllByStock(stock);
    }

    @Override
    public List<Product> findByNameOrStockOrPriceBetween(String name, Integer stock, Long minPrice, Long maxPrice) {
        if (productRepository.findByNameOrStockOrPriceBetween(name, stock, minPrice, maxPrice).isEmpty()){
            getAll();
        }
        return productRepository.findByNameOrStockOrPriceBetween(name, stock, minPrice, maxPrice);
    }

}

