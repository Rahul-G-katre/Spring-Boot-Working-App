package crud2.Crud2.controller;

import java.util.List;
import java.util.Optional;

import crud2.Crud2.model.Brand;
import crud2.Crud2.model.Product;
import crud2.Crud2.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")

@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add product")
    @Operation(summary = "Create product", description = "Creates a new product")
    public Product createProduct(@RequestBody Product product) {
        logger.info("Creating new product: {}", product.getProductName());
        return productService.saveProduct(product);
    }


    @GetMapping("/getProducts")
    @Operation(summary = "Get all products", description = "Returns a list of all products")

    public List<Product> getAllProducts() {
        logger.debug("Retrieving all products");
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a product", description = "Returns a  product")

    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.debug("Fetching product with ID: {}", id);
        Optional<Product> product = productService.getProductById(id);
     return     product.map(ResponseEntity::ok).orElseGet(() ->             // this exception need to add
         {
             logger.warn("Product not found for ID:{}",id);
             return  ResponseEntity.notFound().build();
         });
    }


    @PutMapping("/products/{id}")
    @Operation(summary = "Update product", description = "Updates a new product")

    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        logger.info("Updating product with ID: {} to new values: {}", id, product.getProductName());
        return productService.updateProduct(id, product);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Deletes a  product")

    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.warn("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    public ProductService getProductService() {
        return productService;
    }
}
