package crud2.Crud2.tests.model_test;

import crud2.Crud2.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {


    @Test

    void testProductConstructorAndGetters()
    {
        Product product = new Product();
        product.setId(33L);
        product.setProductname("W120");

        assertEquals(33l,product.getId());
        assertEquals("W120",product.getProductName());

    }
    @Test
    void  testSetters()
    {
        Product product = new Product();
        product.setId(89L);
        product.setProductname("W420");

        assertEquals(89L, product.getId());
        assertEquals("W420",product.getProductName());

    }

}
