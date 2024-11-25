package com.example.CrudOperation.Service;


import com.example.CrudOperation.Entity.Product;
import com.example.CrudOperation.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public boolean deleteProductById(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(long id, Product product) {
        Optional<Product> existingProductOpt = productRepository.findById(id);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            // Update the name and price
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());


            productRepository.save(existingProduct);

            return true;
        }
        return false;
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts(int page) {
        return productRepository.findAll(PageRequest.of(page, 6)).getContent();
    }
}
