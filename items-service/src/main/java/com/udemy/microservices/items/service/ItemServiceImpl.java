package com.udemy.microservices.items.service;

import com.udemy.microservices.items.model.Item;
import com.udemy.microservices.items.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Primary
public class ItemServiceImpl implements ItemService {

    private final RestTemplate restTemplate;

    @Value("${products-service.endpoint.url}")
    private String endpointUrl;

    @Override
    public List<Item> findAll() {
        List<Product> products = Arrays.asList(restTemplate.getForObject("http://products-service/products", Product[].class));
        return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantity) {
        Product product = restTemplate.getForObject("http://products-service/products/{id}", Product.class, id);
        return new Item(product, quantity);
    }

    @Override
    public Product save(Product product) {
        HttpEntity<Product> body = new HttpEntity<>(product);
        ResponseEntity<Product> response = restTemplate.exchange("http://products-service/products", HttpMethod.POST, body, Product.class);
        return response.getBody();
    }

    @Override
    public Product update(Product product, Long id) {
        HttpEntity<Product> body = new HttpEntity<>(product);
        ResponseEntity<Product> response = restTemplate.exchange("http://products-service/products/{id}", HttpMethod.PUT, body, Product.class, id);
        return response.getBody();
    }

    @Override
    public void deleteById(Long id) {
        restTemplate.delete("http://products-service/products/{id}",id);
    }
}
