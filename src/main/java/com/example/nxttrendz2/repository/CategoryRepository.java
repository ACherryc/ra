/*
 *
 * You can use the following import statements
 * 
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.nxttrendz2.repository;

import com.example.nxttrendz2.model.Category;
import com.example.nxttrendz2.model.Product;

import java.util.List;

public interface CategoryRepository {
    List<Category> getCategories();

    Category getCategoryById(int categoryId);

    Category addCategory(Category category);

    Category updateCategory(int categoryId, Category category);

    void deleteCategory(int categoryId);

    List<Product> getCategoryProducts(int categoryId);
}
