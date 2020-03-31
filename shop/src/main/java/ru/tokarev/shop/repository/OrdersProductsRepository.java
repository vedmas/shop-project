package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.OrdersProducts;
import ru.tokarev.shop.repository.entity.OrdersProductsKey;

public interface OrdersProductsRepository extends JpaRepository<OrdersProducts, OrdersProductsKey> {

}
