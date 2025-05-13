package crud2.Crud2.tests.model_test;

import crud2.Crud2.model.Brand;
import crud2.Crud2.model.Category;
import crud2.Crud2.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrandTest {

 @Test
 void  testBrandConstructorAndGetters()
 {
  //Brand brand = new Brand(12L, "Wipro",null,null);
  Brand brand = new Brand();
  brand.setId(12L);
  brand.setBrandname("Wipro");

  assertEquals(12L,brand.getId());
  assertEquals("Wipro",brand.getBrandname());
  assertNull(brand.getCategory());
  assertNull(brand.getProducts());
 }

 @Test
 void testSetters()
 {
  Brand brand = new Brand();
  brand.setId(44L);
  brand.setBrandname("Jio");

  assertEquals(44L,brand.getId());
  assertEquals("Jio",brand.getBrandname());
 }

 @Test

 void testCategoryAssociation()
 {
  Category category = new Category(5L,"Electronics");
  Brand brand = new Brand();
  brand.setCategory(category);

  assertEquals("Electronics", brand.getCategory().getCategoryName());
 }

 @Test
 void testProductAssociation()
 {
  Product p1 = new Product();
  Product p2 = new Product();
  List<Product> products = Arrays.asList(p1,p2);

  Brand brand = new Brand();
  brand.setProducts(products);

  assertEquals(2,brand.getProducts().size());
  assertSame(p1,brand.getProducts().get(0));


 }


}
