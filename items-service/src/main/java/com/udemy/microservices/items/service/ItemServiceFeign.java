package com.udemy.microservices.items.service;

import com.udemy.microservices.items.model.Item;
import com.udemy.microservices.items.model.Product;
import com.udemy.microservices.items.rest.ProductsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class ItemServiceFeign implements ItemService {

    private final ProductsClient client;

    @Override
    public List<Item> findAll() {
        return client.getAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        return new Item(client.getById(id), quantity);
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product update(Product product, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
