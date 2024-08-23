package com.chronocraft.chronocraft.controller.admin;

import com.chronocraft.chronocraft.dto.CategoryDTO;
import com.chronocraft.chronocraft.entity.CategoryEntity;
import com.chronocraft.chronocraft.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")

public class AdminCategoryController {
    private final CategoryService categoryService;
    @Autowired
    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryEntity);
    }

    @GetMapping("/category")
    public ResponseEntity<List<CategoryEntity>>getAllCategories() {
        List<CategoryEntity> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryService.updateCategory(id, categoryDTO);
        if (categoryEntity != null) {
            return ResponseEntity.status(HttpStatus.OK).body(categoryEntity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
