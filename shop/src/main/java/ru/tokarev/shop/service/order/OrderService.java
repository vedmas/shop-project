package ru.tokarev.shop.service.order;

import ru.tokarev.shop.controller.repr.OrderRepr;
import ru.tokarev.shop.repository.entity.Orders;

import java.util.List;

public interface OrderService {

    List<Orders> findAll();

    Orders get(Long id);

    Orders saveOrder(OrderRepr orderRepr);

    void deleteById(Long id);

    void delete(Orders order);
}
