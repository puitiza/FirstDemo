package com.udemy.microservices.products.controller;

import com.udemy.microservices.products.entity.Product;
import com.udemy.microservices.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
//    private final Environment env;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/products")
    public List<Product> getAll() {
        return service.getAll().stream().map(product -> {
//            product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
            //product.setPort(port);
            return product;
        }).collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable Long id) {
        Product product = service.getById(id);
//        product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        product.setPort(port);
        return product;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product add(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product edit(@RequestBody Product product, @PathVariable Long id) {
        Product productDb = service.getById(id);
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());

        return service.save(productDb);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

}
