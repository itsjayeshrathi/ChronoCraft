package com.chronocraft.chronocraft.service.admin;

import com.chronocraft.chronocraft.dto.CategoryDTO;
import com.chronocraft.chronocraft.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    CategoryEntity createCategory(CategoryDTO categoryDTO);
    List<CategoryEntity> getAllCategories();
    CategoryEntity updateCategory(Long id, CategoryDTO categoryDTO);
    void deleteCategory(Long id);
}
