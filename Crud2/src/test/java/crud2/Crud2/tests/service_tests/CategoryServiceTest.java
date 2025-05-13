package crud2.Crud2.tests.service_tests;

import crud2.Crud2.model.Category;
import crud2.Crud2.repository.CategoryRepository;
import crud2.Crud2.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category(1L, "Electronics");
    }

    @Test
    void testSaveCategory() {
        when(categoryRepository.save(category)).thenReturn(category);

        String result = categoryService.saveCategory(category);

        assertEquals("Data saved successfully", result);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testGetAllCategoryList() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));

        List<Category> categories = categoryService.getAllCategoryList();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("Electronics", categories.get(0).getCategoryName());
    }

    @Test
    void testGetCategoryById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.getCategoryById(1L);

        assertTrue(result.isPresent());
        assertEquals("Electronics", result.get().getCategoryName());
    }

    @Test
    void testDeleteCategory() {
        Long id = 1L;
        doNothing().when(categoryRepository).deleteById(id);

        categoryService.deleteCategory(id);

        verify(categoryRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdateCategory() {
        when(categoryRepository.save(category)).thenReturn(category);

        Category updated = categoryService.updateCategory(1L, category);

        assertNotNull(updated);
        assertEquals("Electronics", updated.getCategoryName());
    }
}
