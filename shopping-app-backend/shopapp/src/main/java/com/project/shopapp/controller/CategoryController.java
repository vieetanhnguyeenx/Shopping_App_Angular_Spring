package com.project.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    //Get all categories
    @GetMapping
    public ResponseEntity<String> getAllCategories() {
        return ResponseEntity.ok("Hello world!");
    }

    @PostMapping
    public ResponseEntity<String> insertCategory() {
        return ResponseEntity.ok("Insert");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id) {
        return ResponseEntity.ok("Updated " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok("Deleted " + id);
    }

}
