package ru.tokarev.shop.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tokarev.shop.controller.repr.CategoryRepr;
import ru.tokarev.shop.repository.CategoryRepository;
import ru.tokarev.shop.repository.entity.Category;

import java.io.Serializable;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService, Serializable {

    private static final long serialVersionUID = -8884935989028553790L;

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id).orElse(new Category());
    }

    @Override
    public void saveCategory(CategoryRepr categoryRepr) {
        Category category = new Category();
        category.setId(categoryRepr.getId());
        category.setNameCategory(categoryRepr.getNameCategory());
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category findOneByNameCategory(String name) {
        return categoryRepository.findOneByNameCategory(name);
    }
}
