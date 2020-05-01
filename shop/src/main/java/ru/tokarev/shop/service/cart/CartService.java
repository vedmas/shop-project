package ru.tokarev.shop.service.cart;

import ru.tokarev.shop.service.repr.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    List<ProductInfo> findAll();

    void addProductInfo(ProductInfo productInfo);

    void delete(Long id);

    void deleteAll();

    BigDecimal subTotal();

    Integer sizeCart();
}
