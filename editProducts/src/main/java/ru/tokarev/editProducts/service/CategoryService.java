package ru.tokarev.editProducts.service;

import ru.tokarev.editProducts.service.repr.CategoryInfo;

import java.util.List;

public interface CategoryService {

    List<CategoryInfo> categoryAll();

    CategoryInfo getCategoryById(Long id);

    void deleteCategoryById(Long id);

    void saveCategory(CategoryInfo categoryInfo);

}
