package ru.tokarev.shop.service.category;

import ru.tokarev.shop.controller.repr.CategoryRepr;
import ru.tokarev.shop.repository.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category get(Long id);

    void saveCategory(CategoryRepr categoryRepr);

    void deleteById(Long id);

    void delete(Category category);

    Category findOneByNameCategory(String name);
}
