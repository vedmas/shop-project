package ru.tokarev.shop.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.tokarev.shop.controller.repr.CategoryRepr;
import ru.tokarev.shop.repository.entity.Category;
import ru.tokarev.shop.service.category.CategoryService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> allCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findCategoryId(@PathVariable("id") Long id) {
        Category category = categoryService.get(id);
        log.info("id: {}, name: {}", category.getId(), category.getNameCategory());
        return category;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        log.info("Category by id {} delete", id);
    }

    @PutMapping("/save")
    public void saveCategory(@RequestBody CategoryRepr categoryRepr) {
        log.info("categoryRepr id: {}, name: {}", categoryRepr.getId(), categoryRepr.getNameCategory());
        categoryService.saveCategory(categoryRepr);

    }
}
