package ru.tokarev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tokarev.shop.repository.entity.StatusPay;

public interface StatusPayRepository extends JpaRepository<StatusPay, Integer> {
    StatusPay findOneByNameStatus(String nameStatus);
}
