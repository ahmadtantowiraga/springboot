package com.enigma.enigma_shop;

import com.enigma.enigma_shop.entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Map;

@SpringBootApplication
@RestController
public class EnigmaShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnigmaShopApplication.class, args);
	}
	@GetMapping(path = "/hello-word", produces = MediaType.APPLICATION_JSON_VALUE)
	public String hello(){
		return "<h1> Hello Word </h1>";
	}

	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getProduct(){
		return Map.of(
				"id", "1",
				"name", "Apel",
				"price", 3000
		);
	}

	//localhost:8080/menus?name=...&maxPrice=...
	@GetMapping(path="/menus", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getMenusFilter(
			@RequestParam(name ="name", required = false) String name,
			@RequestParam(name="maxPrice", required = false) Integer maxPrice){
		return name + " "+maxPrice;
	}

	@GetMapping(path="menus/{id}")
	public String getMenuById(@PathVariable(name="id") String menuId){
		return "Product "+menuId;
	}

	@PostMapping(
			path = "/products",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public Product createNewProduct(@RequestBody Product product){
		return product;
	}

}
