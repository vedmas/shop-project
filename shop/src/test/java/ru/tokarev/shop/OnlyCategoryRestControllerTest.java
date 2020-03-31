package ru.tokarev.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.tokarev.shop.repository.entity.Category;
import ru.tokarev.shop.service.category.CategoryService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OnlyCategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void allCategoriesTest() throws Exception {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1L, "Category1"));
        categoryList.add(new Category(2L, "Category2"));
        categoryList.add(new Category(3L, "Category3"));

        given(categoryService.findAll()).willReturn(categoryList);

        mockMvc.perform(get("/api/v1/category/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nameCategory", is(categoryList.get(0).getNameCategory())));
    }
}
