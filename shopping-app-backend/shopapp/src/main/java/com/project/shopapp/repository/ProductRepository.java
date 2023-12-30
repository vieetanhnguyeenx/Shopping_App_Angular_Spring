package com.project.shopapp.repository;

import com.project.shopapp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

    @Override
    Page<Product> findAll(Pageable pageable);

    @Override
    Optional<Product> findById(Long aLong);

    boolean existsById(Long aLong);
}
