package com.enigma.enigma_shop.services.impl;

import com.enigma.enigma_shop.dto.request.NewProductRequest;
import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.repository.ProductRepository;
import com.enigma.enigma_shop.services.ProductService;
import com.enigma.enigma_shop.spesification.ProductSpecification;
import com.enigma.enigma_shop.util.ValidationUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.HTMLDocument;
import java.util.List;
import java.util.Optional;


// lombok RequiredArgumentConstructor -> constructor injection
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final EntityManager entityManager;
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Product create(NewProductRequest request) {
        validationUtil.validate(request);
        Product product=Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product getById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        return productRepository.findById(id).get();
    }

    @Override
    public Page<Product> getAll(SearchProductRequest request) {
        if (request.getPage() <= 0) request.setPage(1);
        Sort sort=Sort.by(Sort.Direction.fromString(request.getDirection()), request.getSortBy());

        Pageable pageable= PageRequest.of(request.getPage()-1, request.getSize(), sort);

        Specification<Product> specification= ProductSpecification.getSpecification(request);
        return productRepository.findAll(specification, pageable);
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
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> findByStock(Integer stock) {
        return productRepository.findAllByStock(stock);
    }

    @Override
    public List<Product> findByNameOrStockOrPriceBetween(String name, Integer stock, Long minPrice, Long maxPrice) {
        return productRepository.findByNameOrStockOrPriceBetween(name, stock, minPrice, maxPrice);
    }

}

