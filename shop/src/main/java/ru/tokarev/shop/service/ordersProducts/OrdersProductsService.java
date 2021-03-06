package ru.tokarev.shop.service.ordersProducts;

import ru.tokarev.shop.repository.entity.Orders;
import ru.tokarev.shop.repository.entity.OrdersProducts;
import ru.tokarev.shop.service.repr.ProductInfo;

import java.util.List;

public interface OrdersProductsService {

    List<OrdersProducts> findAll();

    void saveOrderProduct(Orders orders, ProductInfo productInfo);
}
