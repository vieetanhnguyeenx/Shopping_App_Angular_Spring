package com.project.shopapp.service;

import com.project.shopapp.dto.request.CategoryDTORequest;
import com.project.shopapp.dto.response.CategoryDTOResponse;

import java.util.List;

public interface ICategoryService {
    CategoryDTOResponse createCategory(CategoryDTORequest categoryDTORequest);

    CategoryDTOResponse getCategoryById(long id);

    List<CategoryDTOResponse> getAllCategories();

    CategoryDTOResponse updateCategory(long id, CategoryDTORequest categoryDTORequest);

    CategoryDTOResponse deleteCategory(long id);


}
