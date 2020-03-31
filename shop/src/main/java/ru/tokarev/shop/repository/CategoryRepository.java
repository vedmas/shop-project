package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findOneByNameCategory(String nameCategory);
}
