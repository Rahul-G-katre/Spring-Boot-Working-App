package crud2.Crud2.tests.controller_test;

import crud2.Crud2.controller.CategoryController;
import crud2.Crud2.model.Category;
import crud2.Crud2.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveCategory() throws Exception {
        Category category = new Category(1L, "Electronics");
        when(categoryService.saveCategory(any(Category.class))).thenReturn("Data saved successfully");

        mockMvc.perform(post("/category/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(content().string("Data saved successfully"));
    }

    @Test
    void testGetAllCategories() throws Exception {
        Category category = new Category(1L, "Electronics");
        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category));

        mockMvc.perform(get("/category/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryName").value("Electronics"));
    }
////////////////////////////////////////////////   aslist means not delete not add - as it is
    @Test
    void testGetCategoryById() throws Exception {
        Category category = new Category(1L, "Electronics");
        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category));

        mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value("Electronics"));
    }

    @Test
    void testDeleteCategory() throws Exception {
        Mockito.doNothing().when(categoryService).deleteCategory(1L);

        mockMvc.perform(delete("/category/delete/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCategory() throws Exception {
        Category category = new Category(1L, "Electronics Updated");
        when(categoryService.updateCategory(any(Long.class), any(Category.class))).thenReturn(category);

        mockMvc.perform(put("/category/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName").value("Electronics Updated"));
    }

    @Test
    void testSearchCategories() throws Exception {
        Category category = new Category(1L, "Electronics");
        when(categoryService.searchCategoriesByName("elec")).thenReturn(Arrays.asList(category));

        mockMvc.perform(get("/category/search?keyword=elec"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryName").value("Electronics"));
    }
}
