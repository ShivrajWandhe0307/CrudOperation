package com.example.CrudOperation.Service;

import com.example.CrudOperation.Entity.Product;

import java.util.List;

public interface ProductService {

    public Product addProduct(Product product);

    public boolean deleteProductById(long id);
    public boolean updateProduct(long id, Product product);
    public Product getProductById(long id);

    public List<Product> getAllProducts(int page);
}
