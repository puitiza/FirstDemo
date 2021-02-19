package com.udemy.microservices.products.service;

import com.udemy.microservices.products.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product getById(Long id);
    Product save(Product product);
    void deleteById(Long id);

}
