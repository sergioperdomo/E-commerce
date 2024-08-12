package com.sergio.ecom.controller.admin;


import com.sergio.ecom.dto.CategoryDto;
import com.sergio.ecom.entity.Category;
import com.sergio.ecom.services.admin.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity<Category> createCategory (@RequestBody CategoryDto categoryDto){
        Category category = categoryService.createCategory(categoryDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories (){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

}
