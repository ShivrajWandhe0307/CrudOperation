package com.example.CrudOperation.Service;

import com.example.CrudOperation.Entity.Category;
import com.example.CrudOperation.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);

    }

    @Override
    public boolean deleteCategoryById(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCategory(long id, String name) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(name); // Update the name only
            categoryRepository.save(category);
            return true;
        }
        return false;
    }


    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories(int page) {
        return categoryRepository.findAll(PageRequest.of(page, 6)).getContent();
    }
}
