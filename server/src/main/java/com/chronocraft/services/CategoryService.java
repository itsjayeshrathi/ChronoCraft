package com.chronocraft.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronocraft.dto.CategoryDTO;
@Service
public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(int id, CategoryDTO categoryDTO);
    void deleteCategory(int id);
    CategoryDTO getCategoryById(int id);
    List<CategoryDTO> getAllCategories();
}
