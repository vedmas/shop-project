package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {

    Products findOneByNameProduct(String name);
    Products findOneById(Long id);
}
