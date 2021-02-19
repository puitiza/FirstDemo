package com.udemy.microservices.products.repository;

import com.udemy.microservices.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // <- Not actually needed since JpaRepository has @NoRepositoryBean
public interface ProductRepository extends JpaRepository<Product, Long> {
}
