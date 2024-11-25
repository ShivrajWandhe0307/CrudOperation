package com.example.CrudOperation.Controller;

import com.example.CrudOperation.Entity.Product;
import com.example.CrudOperation.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok("Product added successfully!");
    }

    // Get all products with pagination
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
        return productService.getAllProducts(page);
    }

    // Get a product by its ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable long id, @RequestBody Product product) {
        boolean isUpdate=productService.updateProduct(id,product);  // assuming adding product updates if ID exists
        if (isUpdate) {
            return ResponseEntity.ok("Product data updated successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable long id) {
        boolean contains=productService.deleteProductById(id);
        if(contains) {
            return "Product deleted successfully!";
        }
        else {

            return "Product Not Found!";
        }
    }
}
