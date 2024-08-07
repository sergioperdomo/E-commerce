package com.sergio.ecom.services.admin.category;

import com.sergio.ecom.dto.CategoryDto;
import com.sergio.ecom.entity.Category;

public interface CategoryService {

    // Método para crear una categoria
    Category createCategory (CategoryDto categoryDto);

}
