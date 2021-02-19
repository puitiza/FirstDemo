package com.udemy.microservices.items.service;

import com.udemy.microservices.items.model.Item;
import com.udemy.microservices.items.model.Product;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(Long id, Integer quantity);
    Product save(Product product);
    Product update(Product product, Long id);
    void deleteById(Long id);

    // TODO: Implement using WebClient (reactive)
}
