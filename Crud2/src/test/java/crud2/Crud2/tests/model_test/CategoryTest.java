package crud2.Crud2.tests.model_test;

import crud2.Crud2.model.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testCategoryConstructorAndGetters() {
        Category category = new Category(10L, "Electronics");
        assertEquals(10L, category.getId());
        assertEquals("Electronics", category.getCategoryName());
    }

    @Test
    void testSetters() {
        Category category = new Category(22L, "Books");
        category.setId(22L);
        category.setCategoryName("Books");

        assertEquals(22L, category.getId());
        assertEquals("Books", category.getCategoryName());
    }
}
