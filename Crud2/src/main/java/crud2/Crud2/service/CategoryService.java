package crud2.Crud2.service;

import java.util.List;
import java.util.Optional;

import crud2.Crud2.exception.ResourceNotFoundException;
import crud2.Crud2.model.Category;
import crud2.Crud2.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    //logging
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    // Create Product
    public String saveCategory(Category category) {
//        categoryRepository.save(category);
//        return "Data saved successfully";                                // old file updated as per logging

        logger.info("Saving category: {}", category.getCategoryName());
        categoryRepository.save(category);
        return "Data saved successfully";
    }

    public List<Category> getAllCategoryList() {
        logger.debug("Fetching all categories");                        // added logger
        return categoryRepository.findAll();
    }


    public Optional<Category> getCategoryById(Long id) {
        logger.debug("Fetching category by ID: {}", id);
        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(id)
                .orElseThrow(() -> {
                    return  new ResourceNotFoundException("Category with ID " + id + " not found.");
                }));
        return category;
    }

    public void deleteCategory(Long id) {
        logger.warn("Deleting category with ID: {}", id);
        categoryRepository.deleteById(id);

    }

    public List<Category> getAllCategories() {
        logger.debug("Fetching all categories (alternate method)");
        return categoryRepository.findAll();
    }

    public Category updateCategory(Long id, Category category) {
        logger.info("Updating category with ID: {} and Name: {}", id, category.getCategoryName());
        return categoryRepository.save(category);
    }
    ///           juit5  test cases

    public Optional<Category> findByCategoryName(String categoryName) {
        logger.debug("Finding category by name: {}", categoryName);
        return categoryRepository.findByCategoryName(categoryName);
    }

    public List<Category> searchCategoriesByName(String keyword) {
        logger.debug("Searching categories with keyword: {}", keyword);
        return categoryRepository.findByCategoryNameContainingIgnoreCase(keyword);
    }

    public Optional<Category> findByIdAndCategoryName(Long id, String categoryName) {
        logger.debug("Finding category by ID: {} and Name: {}", id, categoryName);
        return categoryRepository.findByIdAndCategoryName(id, categoryName);
    }

    public boolean categoryExistsByName(String categoryName) {
        logger.debug("Checking if category exists by name: {}", categoryName);
        return categoryRepository.existsByCategoryName(categoryName);
    }
}

