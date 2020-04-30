package ru.tokarev.shop.service.statusPayService;

import ru.tokarev.shop.repository.entity.StatusPay;

import java.util.List;

public interface StatusPayService {

    List<StatusPay> findAll();

    StatusPay get(Integer id);

    void deleteById(Integer id);

    StatusPay findOneByNameStatus(String nameStatus);
}
