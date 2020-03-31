package ru.tokarev.shop.service.product;

import ru.tokarev.shop.controller.repr.ProductRepr;
import ru.tokarev.shop.repository.entity.Products;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Products> findAll();

    ProductRepr get(Long id);

    void saveProduct(ProductRepr productRepr) throws IOException;

    void deleteById(Long id);

    void delete(Products product);

    Products findOneByNameProduct(String name);

    Products findOneById(Long id);
}
