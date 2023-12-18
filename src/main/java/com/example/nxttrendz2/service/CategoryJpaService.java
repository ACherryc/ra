/*
 *
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * import java.util.List;
 * 
 */

// Write your code here
package com.example.nxttrendz2.service;

import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.model.Product;
import com.example.nxttrendz2.repository.CategoryJpaRepository;
import com.example.nxttrendz2.repository.CategoryRepository;
import com.example.nxttrendz2.repository.ProductJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class CategoryJpaService implements CategoryRepository {

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Override
    public List<Category> getCategories() {
        return categoryJpaRepository.findAll();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return categoryJpaRepository.findById(categoryId).get();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Category addCategory(Category category) {
        return categoryJpaRepository.save(category);

    }

    @Override
    public Category updateCategory(int categoryId, Category category) {
        try {
            Category newCategory = categoryJpaRepository.findById(categoryId).get();
            if (category.getName() != null) {
                newCategory.setName(category.getName());
            }
            if (category.getDescription() != null) {
                newCategory.setDescription(category.getDescription());
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return category;
    }

    @Override
    public void deleteCategory(int categoryId) {
        try {
            Category category = categoryJpaRepository.findById(categoryId).get();
            List<Product> products = productJpaRepository.findByCategory(category);
            for (Product product : products) {
                product.setCategory(null);
            }
            productJpaRepository.saveAll(products);
            categoryJpaRepository.deleteById(categoryId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public List<Product> getCategoryProducts(int categoryId) {
        try {
            Category category = categoryJpaRepository.findById(categoryId).get();
            return productJpaRepository.findByCategory(category);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
