package com.example.CrudOperation.Controller;

import com.example.CrudOperation.Entity.Category;
import com.example.CrudOperation.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Category category) {
        categoryService.addCategory(category);
        return ResponseEntity.ok("Category added successfully!");
    }

    @GetMapping
    public List<Category> getAllCategories(@RequestParam(defaultValue = "0") int page) {
        return categoryService.getAllCategories(page);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<String> updateCategoryName(@PathVariable long id, @RequestBody String name) {
        boolean isUpdated = categoryService.updateCategory(id, name);
        if (isUpdated) {
            return ResponseEntity.ok("Category name updated successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!");
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable long id) {
        boolean contains=categoryService.deleteCategoryById(id);
        if(contains) {
            return "Category deleted successfully!";
        }
        else {
            return  "Category Not Found!";
        }
    }
}
