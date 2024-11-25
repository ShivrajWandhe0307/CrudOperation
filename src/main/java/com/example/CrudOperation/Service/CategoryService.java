package com.example.CrudOperation.Service;

import com.example.CrudOperation.Entity.Category;

import java.util.List;

public interface CategoryService {

    public Category addCategory(Category category);

    public boolean deleteCategoryById(long id);

    public boolean updateCategory(long id, String name);

    public Category getCategoryById(long id);

    public List<Category> getAllCategories(int page);
}
