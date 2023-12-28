package com.project.shopapp.controller;

import com.project.shopapp.dto.request.CategoryDTORequest;
import com.project.shopapp.dto.response.CategoryDTOResponse;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.service.ICategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Validated
public class CategoryController {
    ICategoryService categoryService;

    //Get all categories
    @GetMapping
    public List<CategoryDTOResponse> getCategories() {
        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}")
    public CategoryDTOResponse getCategoryById(@PathVariable("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public CategoryDTOResponse createCategory(
            @Valid @RequestBody CategoryDTORequest categoryDTORequest,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            // TODO: throw exception to global
            throw ApiRequestException.badRequest(errorMsg);
        }
        return categoryService.createCategory(categoryDTORequest);
    }

    @PutMapping("/{id}")
    public CategoryDTOResponse updateCategory(
            @Valid @NotNull @PathVariable("id") Long id,
            @RequestBody @Valid CategoryDTORequest categoryDTORequest,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            List<String> errorMsg = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            // TODO: throw exception to global
            throw ApiRequestException.badRequest(errorMsg);
        }

        return categoryService.updateCategory(id, categoryDTORequest);
    }

    @DeleteMapping("/{id}")
    public CategoryDTOResponse deleteCategory(@PathVariable @NotNull long id) {
        return categoryService.deleteCategory(id);
    }

}
