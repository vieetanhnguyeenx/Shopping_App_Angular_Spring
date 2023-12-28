package com.project.shopapp.service.impl;

import com.project.shopapp.dto.request.CategoryDTORequest;
import com.project.shopapp.dto.response.CategoryDTOResponse;
import com.project.shopapp.entity.Category;
import com.project.shopapp.exception.ApiRequestException;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.service.ICategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {

    CategoryRepository categoryRepository;
    ModelMapper mapper;


    @Override
    public CategoryDTOResponse createCategory(CategoryDTORequest categoryDTORequest) {
        Category category = mapper.map(categoryDTORequest, Category.class);
        category = categoryRepository.save(category);
        return mapper.map(category, CategoryDTOResponse.class);
    }

    @Override
    public CategoryDTOResponse getCategoryById(long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> ApiRequestException
                        .notFound(List.of("Category dose not exist")));

        return mapper.map(category, CategoryDTOResponse.class);
    }

    @Override
    public List<CategoryDTOResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> mapper.map(category, CategoryDTOResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTOResponse updateCategory(long id, CategoryDTORequest categoryDTORequest) {
        if (!categoryRepository.existsById(id))
            throw new ApiRequestException()
                    .notFound(List.of("Category dose not exist"));

        Category category = mapper.map(categoryDTORequest, Category.class);
        category.setId(id);

        return mapper.map(categoryRepository.save(category), CategoryDTOResponse.class);
    }

    @Override
    public CategoryDTOResponse deleteCategory(long id) {
        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> ApiRequestException
                        .notFound(List.of("Category dose not exist")));
        categoryRepository.deleteById(id);

        return mapper.map(category, CategoryDTOResponse.class);

    }
}
