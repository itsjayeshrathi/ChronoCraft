package com.chronocraft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronocraft.dto.CategoryDTO;
import com.chronocraft.entities.Category;
import com.chronocraft.repositories.CategoryRepository;
import com.chronocraft.services.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("add")
	public ResponseEntity add(@RequestBody Category category) {
		
		System.out.println("request came for add category");
		
		Category c = categoryRepository.save(category);
		
		if(c != null) {
			System.out.println("response sent");
			return new ResponseEntity( c ,HttpStatus.OK);
		}
		
		else {
			System.out.println("response sent");
			return new ResponseEntity("Failed to add category!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

    @GetMapping("all")
	public ResponseEntity<List<Category>> getAllCategories() {
		
        System.out.println("request came for getting all categories");
		
		List<Category> categories = this.categoryRepository.findAll();
		
		ResponseEntity<List<Category>> response = new ResponseEntity<>(categories, HttpStatus.OK);
		
		System.out.println("response sent");
		
		return response;
		
	}
}
