package com.project.shopapp.controller;

import com.project.shopapp.dto.CategoryDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
//@Validated
public class CategoryController {
    //Get all categories
    @GetMapping
    public ResponseEntity<String> getCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ) {
        return ResponseEntity.ok("List of category: " + page + " " + limit);
    }

    //Get all categories
    @GetMapping("/{id}")
    public ResponseEntity<String> getCategoryById(@PathVariable("id") int id) {
        return ResponseEntity.ok("Category " + id);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .toList();
            // TODO: throw exception to global
            return ResponseEntity.badRequest().body(errorMsg);

        }
        return ResponseEntity.ok("Inserted " + categoryDTO.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id) {
        return ResponseEntity.ok("Updated " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        return ResponseEntity.ok("Deleted " + id);
    }

}
