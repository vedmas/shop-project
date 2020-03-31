package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
