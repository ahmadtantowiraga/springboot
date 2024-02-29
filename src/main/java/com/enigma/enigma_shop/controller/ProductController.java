package com.enigma.enigma_shop.controller;

import com.enigma.enigma_shop.constant.APIUrl;
import com.enigma.enigma_shop.dto.request.NewProductRequest;
import com.enigma.enigma_shop.dto.request.SearchProductRequest;
import com.enigma.enigma_shop.dto.response.CommonResponse;
import com.enigma.enigma_shop.dto.response.PagingResponse;
import com.enigma.enigma_shop.entity.Product;
import com.enigma.enigma_shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.PRODUCT_API)
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<CommonResponse<Product>> createNewProduct(@RequestBody NewProductRequest request) {
        Product product1=productService.create(request);

        CommonResponse<Product> response=CommonResponse.<Product>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully create customer")
                .data(product1)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") String id) {
        Product product=productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Product>>> getAllProduct(
            @RequestParam(name="page", defaultValue = "1") Integer page,
            @RequestParam(name="size", defaultValue = "10") Integer size,
            @RequestParam(name="sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name="direction", defaultValue = "asc") String direction,
            @RequestParam(name="name", required = false) String name
    ) {
        SearchProductRequest request=SearchProductRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();
        Page<Product> products=productService.getAll(request);

        PagingResponse pagingResponse=PagingResponse.builder()
                .totalPages(products.getTotalPages())
                .totalElement(products.getTotalElements())
                .page(products.getPageable().getPageNumber())
                .size(products.getPageable().getPageSize())
                .hasNext(products.hasNext())
                .hasPrevious(products.hasPrevious())
                .build();

        CommonResponse<List<Product>> response=CommonResponse.<List<Product>>builder()
                .statusCode((HttpStatus.OK.value()))
                .message("success get all product")
                .data(products.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
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
