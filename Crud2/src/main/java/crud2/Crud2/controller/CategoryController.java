package crud2.Crud2.controller;

import crud2.Crud2.model.Category;
import crud2.Crud2.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Id;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Tag(name = "Category", description = "Category management APIs")
public class CategoryController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    @Operation(summary = "Create Category", description = "Creates a new category")

    public String createCategory(@RequestBody Category category) {
        logger.info("Received request to create category: {}", category.getCategoryName());       //logger
        return categoryService.saveCategory(category);
    }

    @GetMapping("/getCategories")
    @Operation(summary = "Get all categories", description = "Returns a list of all categories")

    public List<Category> getAllCategories() {
        logger.debug("Fetching all categories");                                                //logger
        return categoryService.getAllCategories();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a category", description = "Returns a category")

    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        logger.debug("Fetch category with ID : {}", id);
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() ->
        {
            logger.warn("Category not found for ID: {}", id);
            return ResponseEntity.notFound().build();
        });
    }

    @PutMapping("/putCategory/{id}")
    @Operation(summary = "Update category", description = "Update a new category")

    public Category updateCategory(@RequestBody Category category, @PathVariable   Long id)
    {
        logger.info("Updating category ID: {} with name: {}", id, category.getCategoryName());
        return categoryService.updateCategory( id, category);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Delete a category")

    public ResponseEntity<Void> deleteBrand(@PathVariable  Long id) {
        logger.warn("Deleting category with ID: {}", id);                                    //logger
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("user")
    public  String getLoggedInUser(Principal principal)
    {
        logger.debug("Fetching logged-in user");                                        // logger
        return principal.getName();
    }
}


//// http://localhost:8080/swagger-ui.html      - url for swagger
