package crud2.Crud2.service;

import java.util.List;
import java.util.Optional;

import crud2.Crud2.exception.ResourceNotFoundException;
import crud2.Crud2.model.Brand;
import crud2.Crud2.model.Product;
import crud2.Crud2.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Create Product
    public Product saveProduct(Product product) {
        logger.info("Saving product: {}", product.getProductName());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        logger.debug("Retrieving all products");
        return productRepository.findAll();
    }
    

    public Optional<Product> getProductById(Long id) {
        logger.debug("Looking up product with ID: {}", id);
        Optional<Product>product = Optional.ofNullable(productRepository.findById(id).orElseThrow(() ->
        {
         return new ResourceNotFoundException("Product with ID "+id+" not found");         ///this method exception need to add
        }));
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        logger.warn("Deleting product with ID: {} ", id);
        if (!productRepository.existsById(id)) {
            logger.error("Product with ID {} not found for deletion", id);
            throw new ResourceNotFoundException("Product with ID "+id +"  not found");
        }
        productRepository.deleteById(id);
    }
    public Product updateProduct(Long id, Product product) {
        logger.info("Updating product with ID: {}", id);
        Optional<Product> findbyId = Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Does Not Exist")));

        Product product1 = findbyId.get();
        product1.setProductname(product.getProductName());
        logger.info("Product with ID {} successfully updated", id);
        return  productRepository.save(product1);




/*        return productRepository.findById(id)
               .map(product -> {
                   product.setProductname(updateProduct(id, product));
                   return productRepository.save(product);
               })
               .orElseThrow(() -> new RuntimeException("Product not found"));*/
   }
}

