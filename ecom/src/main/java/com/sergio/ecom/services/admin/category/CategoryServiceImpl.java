package com.sergio.ecom.services.admin.category;


import com.sergio.ecom.dto.CategoryDto;
import com.sergio.ecom.entity.Category;
import com.sergio.ecom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // Método para crear una categoria
    public Category createCategory (CategoryDto categoryDto){

        Category category = new Category();
        category.setName(category.getName());
        category.setDescription(category.getDescription());

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
