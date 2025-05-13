package crud2.Crud2.tests.service_tests;

import crud2.Crud2.model.Product;
import crud2.Crud2.repository.ProductRepository;
import crud2.Crud2.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setProductname("Product Testing...");

       // when(ProductRepository.save(product)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        Product saved = productService.saveProduct(product);
        assertEquals("Product Testing...",saved.getProductName());
        verify(productRepository, times(1)).save(product);



    }


}
