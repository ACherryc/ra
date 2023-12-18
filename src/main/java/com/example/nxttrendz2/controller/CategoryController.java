/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.nxttrendz2.controller;

import com.example.nxttrendz2.model.Category;

import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.service.CategoryJpaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    public CategoryJpaService categoryJpaService;

    @GetMapping("/categories")
    public List<Category> getCategorys() {
        return categoryJpaService.getCategories();
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategoryById(@PathVariable("categoryId") int categoryId) {
        return categoryJpaService.getCategoryById(categoryId);
    }

    @PostMapping("/cotegories")
    public Category addCategory(@RequestBody Category category) {
        return categoryJpaService.addCategory(category);

    }

    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category) {
        return categoryJpaService.updateCategory(categoryId, category);
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") int categoryId) {
        categoryJpaService.deleteCategory(categoryId);
    }

    @GetMapping("/categories/{categoryId}/products")
    public List<Product> getCategoryProducts(@PathVariable("categoryId") int categoryId) {
        return categoryJpaService.getCategoryProducts(categoryId);
    }

}
