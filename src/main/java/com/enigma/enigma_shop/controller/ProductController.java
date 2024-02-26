package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.constant.APIUrl;
import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.PRODUCT_API)
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public Product createNewProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable(name = "id") String id) {
        return productService.getById(id);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAll();
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        productService.delete(id);
        return "OK";
    }

    @GetMapping("/names/{name}")
    public List<Product> findByName(@PathVariable String name){
        return productService.findByName(name);
    }

    @GetMapping("/stock/{stock}")
    public List<Product> findByStock(@PathVariable Integer stock){
        return productService.findByStock(stock);
    }

    @GetMapping(path="/filter")
    public List<Product> findByNameOrStockOrPriceBetween(@RequestParam(name="name", required = false) String name,
                                                         @RequestParam(name="stock", required = false) Integer stock,
                                                         @RequestParam(name="minPrice", required = false) Long minPrice,
                                                         @RequestParam(name="maxPrice", required = false) Long maxPrice){
        return productService.findByNameOrStockOrPriceBetween(name, stock, minPrice, maxPrice);
    }

}
