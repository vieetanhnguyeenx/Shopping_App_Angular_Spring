package com.project.shopapp.service;

import com.project.shopapp.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(Category category);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    Category updateCategory(long id, Category category);

    Category deleteCategory(long id);


}
