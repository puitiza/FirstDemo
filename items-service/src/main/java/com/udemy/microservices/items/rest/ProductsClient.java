package com.udemy.microservices.items.rest;

import com.udemy.microservices.items.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductsClient {

    @GetMapping("products")
    List<Product> getAll();

    @GetMapping("products/{id}")
    Product getById(@PathVariable Long id);

}
